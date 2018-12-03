package com.devstr.dao.impl;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;
import com.devstr.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.Date;

@Transactional
@Repository
public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(String login, String firstName, String lastName, String email, String password, UserRole userRole) {
        BigInteger userId = createObject(ObjectType.USER.getId(), login);
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
                readAttributeValue(AttributeID.FIRST_NAME.getId(), id),
                readAttributeValue(AttributeID.LAST_NAME.getId(), id),
                UserRole.valueOf(readAttributeListValue(AttributeID.ROLE.getId(), id)),
                Status.valueOf(readAttributeListValue(AttributeID.STATUS.getId(), id)))
                .setUserId(id)
                .setHireDate(readAttributeDateValue(AttributeID.CREATION_DATE.getId(), id))
                .setProjectId(BigInteger.valueOf(Long.parseLong(readAttributeValue(AttributeID.PROJECT.getId(), id))))
                .build();
    }

    @Override
    public BigInteger readUserIdByLogin(String login) {
        return readObjectIdByName(ObjectType.USER.getId(), login);
    }

    @Override
    public BigInteger readUserIdByEmail(String email) {
        return jdbcTemplate.queryForObject(READ_ID_BY_EMAIL, new Object[]{email}, BigInteger.class);
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
        updateAttributeValue(AttributeID.PROJECT.getId(), id, projectId.toString());
    }

    @Override
    public void inactivateUser(BigInteger id) {
        updateAttributeListValue(AttributeID.STATUS.getId(), id, Status.INACTIVE.getId());
    }

}
