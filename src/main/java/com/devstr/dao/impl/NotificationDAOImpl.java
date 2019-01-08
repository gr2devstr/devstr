package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.NotificationDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Notification;
import com.devstr.model.impl.NotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class NotificationDAOImpl implements NotificationDAO {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(NotificationDAOImpl.class.getName());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BigInteger createNotification(String message, BigInteger receiverId) {
        try {
            jdbcTemplate.update(INSERT_NOTIFICATION, message, receiverId.longValue());
            return jdbcTemplate.queryForObject("SELECT MAX(NOTIFICATION_ID) FROM NOTIFICATION", BigInteger.class);
        } catch (DataAccessException exc) {
            String excMessage = "Failed to create notification, message: " + message
                    + ", receiver ID: " + receiverId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public Notification readNotificationById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(READ_NOTIFICATION_BY_ID, new NotificationMapper(), id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read notification by ID: " + id;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public List<Notification> readNotificationsByRecId(BigInteger receiverId) {
        try {
            return jdbcTemplate.query(READ_NOTIFICATION_BY_REC_ID, new Object[]{receiverId}, new NotificationMapper());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read notification by receiver ID: " + receiverId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public void deleteNotificationById(BigInteger id) {
        try {
            jdbcTemplate.update(DELETE_NOTIFICATION, id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to delete notification by ID: " + id;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
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
