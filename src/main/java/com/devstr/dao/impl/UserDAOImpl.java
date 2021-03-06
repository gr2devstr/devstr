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

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(UserDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(String login, String firstName, String lastName, String email, BigInteger managerId, String password, UserRole userRole) {
        BigInteger userId = createObjectWithParent(ObjectType.USER.getId(), managerId, login);
        createAttributeValue(AttributeID.FIRST_NAME.getId(), userId, firstName);
        createAttributeValue(AttributeID.LAST_NAME.getId(), userId, lastName);
        createAttributeValue(AttributeID.FIRST_NAME.getId(), userId, firstName);
        createAttributeValue(AttributeID.EMAIL.getId(), userId, email);
        createAttributeValue(AttributeID.PASSWORD.getId(), userId, password);
        createAttributeDateValue(AttributeID.CREATION_DATE.getId(), userId, new Date());
        createAttributeListValue(AttributeID.ROLE.getId(), userId, userRole.getId());
        createAttributeListValue(AttributeID.STATUS.getId(), userId, Status.ACTIVE.getId());
    }

    @Override
    public User readBasicUserById(BigInteger id) {
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

    }

    @Override
    public User readFullUserById(BigInteger id) {
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
    }

    @Override
    public User readBasicUserByLogin(String login) {
        BigInteger id = readUserIdByLogin(login);
        return readBasicUserById(id);
    }

    @Override
    public User readFullUserByLogin(String login) {
        BigInteger id = readUserIdByLogin(login);
        return readFullUserById(id);
    }

    @Override
    public Collection<User> readAllUsers() {
        List<BigInteger> ids;
        try {
            ids = jdbcTemplate.queryForList(READ_ALL_ID, BigInteger.class);
        } catch (DataAccessException exc) {
            String message = "Failed to read all users";
            LOGGER.error(message, exc);
            throw new DaoException(message, exc);
        }
        Collection<User> users = new ArrayList<>();
        for (BigInteger id : ids) {
            users.add(readBasicUserById(id));
        }
        return users;
    }

    @Override
    public BigInteger readUserIdByLogin(String login) {
        return readObjectIdByName(ObjectType.USER.getId(), login);
    }

    @Override
    public BigInteger readUserIdByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(READ_ID_BY_EMAIL, new Object[]{email}, BigInteger.class);
        } catch (DataAccessException exc) {
            String message = "Failed to read user ID by email: " + email;
            LOGGER.error(message, exc);
            throw new DaoException(message, exc);
        }
    }

    @Override
    public void updateUserPassword(BigInteger id, String password) {
        updateAttributeValue(AttributeID.PASSWORD.getId(), id, password);
    }

    @Override
    public void updateUserEmail(BigInteger id, String email) {
        updateAttributeValue(AttributeID.EMAIL.getId(), id, email);
    }

    @Override
    public void updateUserFirstName(BigInteger id, String firstName) {
        updateAttributeValue(AttributeID.FIRST_NAME.getId(), id, firstName);
    }

    @Override
    public void updateUserLastName(BigInteger id, String lastName) {
        updateAttributeValue(AttributeID.LAST_NAME.getId(), id, lastName);
    }

    @Override
    public void updateUserRole(BigInteger id, UserRole role) {
        updateAttributeListValue(AttributeID.ROLE.getId(), id, role.getId());
    }

    @Override
    public void updateUserProjectId(BigInteger id, BigInteger projectId) {
        updateAttributeValue(AttributeID.USER_PROJECT.getId(), id, projectId.toString());
    }

    @Override
    public void updateUserManagerId(BigInteger id, BigInteger managerId) {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_ID, managerId, id);
        } catch (DataAccessException exc) {
            String message = "Failed to update manager ID: " + managerId;
            LOGGER.error(message, exc);
            throw new DaoException(message, exc);
        }
    }

    @Override
    public void inactivateUser(BigInteger id) {
        updateAttributeListValue(AttributeID.STATUS.getId(), id, Status.INACTIVE.getId());
    }

}
