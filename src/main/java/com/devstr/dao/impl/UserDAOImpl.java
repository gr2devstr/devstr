package com.devstr.dao.impl;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;
import com.devstr.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(String login,
                           String firstName,
                           String lastName,
                           String email,
                           UserRole userRole,
                           String password) {
        jdbcTemplate.update(CREATE_USER, new Object[]{
                login,
                firstName,
                lastName,
                email,
                userRole.getId().longValue(),
                password
        });
    }

    @Override
    public User readUserById(BigInteger id) {
        RowMapper<User> mapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(READ_USER_BY_ID, mapper, new Object[]{
                id.longValue(),
                id.longValue(),
                id.longValue()
        });
    }

    @Override
    public User readUserByLogin(String login) {
        RowMapper<User> mapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(READ_USER_BY_ID, mapper, new Object[]{
                login,
                login,
                login
        });
    }

    @Override
    public void updateUserPassword(BigInteger id, String password) {
        jdbcTemplate.update(UPDATE_USER_PASSWORD, id.longValue(), password);
    }

    @Override
    public void updateUserFirstName(BigInteger id, String firstName) {
        jdbcTemplate.update(UPDATE_USER_FIRST_NAME, id.longValue(), firstName);
    }

    @Override
    public void updateUserLastName(BigInteger id, String lastName) {
        jdbcTemplate.update(UPDATE_USER_FIRST_NAME, id.longValue(), lastName);
    }

    @Override
    public void updateUserRole(BigInteger id, UserRole role) {
        jdbcTemplate.update(UPDATE_USER_ROLE, id.longValue(), role.getId().longValue());
    }

    @Override
    public void inactivateUser(BigInteger id) {
        jdbcTemplate.update(INACTIVE_USER, id.longValue());
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new UserImpl
                    .Builder(resultSet.getString(2),
                             resultSet.getString(3),
                             resultSet.getString(4),
                             resultSet.getString(5),
                             resultSet.getString(6),
                             UserRole.valueOf(resultSet.getString(7)),
                             Status.valueOf(resultSet.getString(9)))
                    .setUserId(BigInteger.valueOf(resultSet.getLong(1)))
                    .setHireDate(resultSet.getDate(8))
                    .setProjectId(BigInteger.valueOf(resultSet.getLong(10)))
                    .build();
        }
    }

}
