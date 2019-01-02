package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.UserDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.User;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;
import com.devstr.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(UserDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(String login, String firstName, String lastName, String email, BigInteger managerId, String password, UserRole userRole) {
        try {
            BigInteger userId = createObjectWithParent(ObjectType.USER.getId(), managerId, login);
            createAttributeValue(AttributeID.FIRST_NAME.getId(), userId, firstName);
            createAttributeValue(AttributeID.LAST_NAME.getId(), userId, lastName);
            createAttributeValue(AttributeID.FIRST_NAME.getId(), userId, firstName);
            createAttributeValue(AttributeID.EMAIL.getId(), userId, email);
            createAttributeValue(AttributeID.PASSWORD.getId(), userId, password);
            createAttributeDateValue(AttributeID.CREATION_DATE.getId(), userId, new Date());
            createAttributeListValue(AttributeID.ROLE.getId(), userId, userRole.getId());
            createAttributeListValue(AttributeID.STATUS.getId(), userId, Status.ACTIVE.getId());
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.createUser(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public User readBasicUserById(BigInteger id) {
        try {
            return new UserImpl.Builder(
                    readObjectNameById(id),
                    readAttributeValue(AttributeID.PASSWORD.getId(), id),
                    readAttributeValue(AttributeID.EMAIL.getId(), id),
                    readParentId(id),
                    readAttributeValue(AttributeID.FIRST_NAME.getId(), id),
                    readAttributeValue(AttributeID.LAST_NAME.getId(), id),
                    UserRole.valueOf(readAttributeListValue(AttributeID.ROLE.getId(), id)),
                    Status.valueOf(readAttributeListValue(AttributeID.STATUS.getId(), id)))
                    .setUserId(id)
                    .build();
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readBasicUserById(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public User readFullUserById(BigInteger id) {
        try {
            return new UserImpl.Builder(
                    readObjectNameById(id),
                    readAttributeValue(AttributeID.PASSWORD.getId(), id),
                    readAttributeValue(AttributeID.EMAIL.getId(), id),
                    readParentId(id),
                    readAttributeValue(AttributeID.FIRST_NAME.getId(), id),
                    readAttributeValue(AttributeID.LAST_NAME.getId(), id),
                    UserRole.valueOf(readAttributeListValue(AttributeID.ROLE.getId(), id)),
                    Status.valueOf(readAttributeListValue(AttributeID.STATUS.getId(), id)))
                    .setUserId(id)
                    .setHireDate(readAttributeDateValue(AttributeID.CREATION_DATE.getId(), id))
                    .setProjectId(BigInteger.valueOf(readObjectReferences(AttributeID.USER_PROJECT.getId(), id).iterator().next().longValue()))
                    .build();
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readFullUserById(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public User readBasicUserByLogin(String login) {
        BigInteger id = readUserIdByLogin(login);
        try {
            return readBasicUserById(id);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readBasicUserByLogin(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public User readFullUserByLogin(String login) {
        BigInteger id = readUserIdByLogin(login);
        try {
            return readFullUserById(id);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readFullUserByLogin(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public Collection<User> readAllUsers() {
        try {
            List<BigInteger> ids = jdbcTemplate.queryForList(READ_ALL_ID, BigInteger.class);
            Collection<User> users = new ArrayList<>();
            for (BigInteger id : ids) {
                users.add(readBasicUserById(id));
            }
            return users;
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readAllUsers()";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public BigInteger readUserIdByLogin(String login) {
        try {
            return readObjectIdByName(ObjectType.USER.getId(), login);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readUserIdByLogin(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        } catch (NullPointerException exc) {
            String message = "Login not found or incorrect";
            LOGGER.warn(message, exc);
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public BigInteger readUserIdByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(READ_ID_BY_EMAIL, new Object[]{email}, BigInteger.class);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.readUserIdByEmail(...)";
            LOGGER.error(message, exc);
            throw new DaoException("Email not found or incorrect");
        }
    }

    @Override
    public void updateUserPassword(BigInteger id, String password) {
        try {
            updateAttributeValue(AttributeID.PASSWORD.getId(), id, password);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserPassword(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void updateUserEmail(BigInteger id, String email) {
        try {
            updateAttributeValue(AttributeID.EMAIL.getId(), id, email);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserEmail(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void updateUserFirstName(BigInteger id, String firstName) {
        try {
            updateAttributeValue(AttributeID.FIRST_NAME.getId(), id, firstName);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserFirstName(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void updateUserLastName(BigInteger id, String lastName) {
        try {
            updateAttributeValue(AttributeID.LAST_NAME.getId(), id, lastName);
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserLastName(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void updateUserRole(BigInteger id, UserRole role) {
        try {
            updateAttributeListValue(AttributeID.ROLE.getId(), id, role.getId());
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserRole(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void updateUserProjectId(BigInteger id, BigInteger projectId) {
        try {
            updateAttributeValue(AttributeID.USER_PROJECT.getId(), id, projectId.toString());
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.updateUserProjectId(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

    @Override
    public void inactivateUser(BigInteger id) {
        try {
            updateAttributeListValue(AttributeID.STATUS.getId(), id, Status.INACTIVE.getId());
        } catch (DataAccessException exc) {
            String message = "UserDAOImpl.inactivateUser(...)";
            LOGGER.error(message, exc);
            throw new DaoException();
        }
    }

}
