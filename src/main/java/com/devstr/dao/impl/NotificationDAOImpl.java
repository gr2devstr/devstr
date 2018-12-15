package com.devstr.dao.impl;

import com.devstr.dao.NotificationDAO;
import com.devstr.model.Notification;
import com.devstr.model.impl.NotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BigInteger createNotification(String message, BigInteger receiverId) {
        jdbcTemplate.update(INSERT_NOTIFICATION, message, receiverId.longValue());
        return jdbcTemplate.queryForObject("SELECT MAX(NOTIFICATION_ID) FROM NOTIFICATION", BigInteger.class);
    }

    @Override
    public Notification readNotificationById(BigInteger id) {
        return jdbcTemplate.queryForObject(READ_NOTIFICATION_BY_ID, new NotificationMapper(), id.longValue());
    }

    @Override
    public List<Notification> readNotificationsByRecId(BigInteger receiverId) {
        return jdbcTemplate.query(READ_NOTIFICATION_BY_REC_ID, new Object[] {receiverId}, new NotificationMapper());
    }

    @Override
    public void deleteNotificationById(BigInteger id) {
        jdbcTemplate.update(DELETE_NOTIFICATION, id.longValue());
    }
    class NotificationMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
            return new NotificationImpl.Builder(resultSet.getString(2),
                    BigInteger.valueOf(resultSet.getLong(3)))
                    .setNotificationId(BigInteger.valueOf(resultSet.getLong(1))).build();
        }
    }
}
