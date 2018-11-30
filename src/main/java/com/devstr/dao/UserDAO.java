package com.devstr.dao;

import com.devstr.model.User;
import com.devstr.model.enumerations.UserRole;
import java.math.BigInteger;

public interface UserDAO {

    /**
     * Creates User in the DB
     *
     * @param login user's login(stores in db as object.name)
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email
     * @param password user's password(encrypted)
     * @param userRole user's role
     * @throws IllegalArgumentException if one of the params is null
     */
    void createUser(String login, String firstName, String lastName, String email, UserRole userRole, String password);

    /**
     * Gets user by id from the DB
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     * @return User with certain id
     *
     */
    User readUserById(BigInteger id);

    /**
     * Gets user by login from the DB
     *
     * @param login user's login in the system(objects.name attribute in the DB)
     * @return User with certain login
     *
     */
    User readUserByLogin(String login);

    /**
     * Updates user password in the DB by user id
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     * @param password new password for user
     *
     */
    void updateUserPassword(BigInteger id, String password);

    /**
     * Updates user first name in the DB by user id
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     * @param firstName new first name for user
     */
    void updateUserFirstName(BigInteger id, String firstName);

    /**
     * Updates user last name in the DB by user id
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     * @param lastName new last name for user
     */
    void updateUserLastName(BigInteger id, String lastName);

    /**
     * Updates user role in the DB by user id
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     * @param role new role for user
     *
     */
    void updateUserRole(BigInteger id, UserRole role);

    /**
     * Sets user into inactive mode
     *
     * @param id user's id in the system(objects.object_id attribute in the DB)
     *
     */
    void inactivateUser(BigInteger id);

    String CREATE_USER = "BEGIN " +
            "INSERT INTO OBJECTS(OBJECT_TYPE_ID, NAME) " +
            // 1 = USER
            "VALUES(1, ?); " +
            "INSERT ALL " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) " +
            // 1 =  FIRST_NAME
            "VALUES(1, OBJ_ID.CURRVAL , ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) " +
            // 2 = LAST_NAME
            "VALUES(2, OBJ_ID.CURRVAL , ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) " +
            // 3 = EMAIL
            "VALUES(3, OBJ_ID.CURRVAL , ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, LIST_VALUE_ID) " +
            // 4 = ROLE
            "VALUES(4, OBJ_ID.CURRVAL , ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE) " +
            // 5 = CREATION_DATE
            "VALUES(5, OBJ_ID.CURRVAL , SYSDATE) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) " +
            // 6 = PASSWORD
            "VALUES(6, OBJ_ID.CURRVAL , ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, LIST_VALUE_ID) " +
            // 7 = STATUS; 5 = ACTIVE
            "VALUES(7, OBJ_ID.CURRVAL , 5) " +
            "SELECT * FROM DUAL; " +
            "END;";

    String READ_USER_BY_ID = "SELECT " +
            "U.OBJECT_ID AS USER_ID, " +
            "U.NAME AS LOGIN, " +
            "PASS.VALUE AS PASSWORD, " +
            "EMAIL.VALUE AS EMAIL, " +
            "FIRST_NAME.VALUE AS FIRST_NAME, " +
            "LAST_NAME.VALUE AS LAST_NAME, " +
            "USER_ROLE.VALUE AS USER_ROLE, " +
            "HIRE_DATE.DATE_VALUE AS HIRE_DATE, " +
            "STATUS.VALUE AS STATUS, " +
            "CASE WHEN EXISTS (" +
            "SELECT U.OBJECT_ID" +
            "FROM OBJECTS U, " +
            "OBJREFERENCE PROJ_ID, " +
            "OBJECTS PROJ, " +
            "ATTRIBUTES PROJ_STATUS " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.OBJECT_ID = ? " +
            // 26 = PROJECT_USERS
            "AND PROJ_ID.ATTRN_ID = 26 " +
            "AND PROJ_ID.REFERENCE = U.OBJECT_ID " +
            // 2 = PROJECT
            "AND PROJ.OBJECT_TYPE_ID = 2 " +
            "AND PROJ.OBJECT_ID = PROJ_ID.OBJECT_ID " +
            // 7 = STATUS
            "AND PROJ_STATUS.ATTRN_ID = 7 " +
            "AND PROJ_STATUS.OBJECT_ID = PROJ.OBJECT_ID " +
            // 5 = ACTIVE
            "AND PROJ_STATUS.LIST_VALUE_ID = 5" +
            ") THEN (" +
            "SELECT PROJ.OBJECT_ID " +
            "FROM OBJECTS U, " +
            "OBJREFERENCE PROJ_ID, " +
            "OBJECTS PROJ, " +
            "ATTRIBUTES PROJ_STATUS " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.OBJECT_ID = ? " +
            // 26 = PROJECT_USERS
            "AND PROJ_ID.ATTRN_ID = 26 " +
            "AND PROJ_ID.REFERENCE = U.OBJECT_ID " +
            // 2 = PROJECT
            "AND PROJ.OBJECT_TYPE_ID = 2 " +
            "AND PROJ.OBJECT_ID = PROJ_ID.OBJECT_ID " +
            // 7 = STATUS
            "AND PROJ_STATUS.ATTRN_ID = 7 " +
            "AND PROJ_STATUS.OBJECT_ID = PROJ.OBJECT_ID " +
            // 5 = ACTIVE
            "AND PROJ_STATUS.LIST_VALUE_ID = 5 " +
            ") ELSE NULL " +
            "END AS PROJ_ID " +
            "FROM OBJECTS U, " +
            "ATTRIBUTES PASS, " +
            "ATTRIBUTES EMAIL, " +
            "ATTRIBUTES FIRST_NAME, " +
            "ATTRIBUTES LAST_NAME, " +
            "LISTS USER_ROLE, ATTRIBUTES USER_ROLE_ID, " +
            "LISTS STATUS, ATTRIBUTES STATUS_ID " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.OBJECT_ID = ? " +
            // 6 = PASSWORD
            "AND PASS.ATTRN_ID = 6 " +
            "AND PASS.OBJECT_ID = U.OBJECT_ID " +
            // 3 = EMAIL
            "AND EMAIL.ATTRN_ID = 3 " +
            "AND EMAIL.OBJECT_ID = U.OBJECT_ID " +
            // 1 = FIRST_NAME
            "AND FIRST_NAME.ATTRN_ID = 1 " +
            "AND FIRST_NAME.OBJECT_ID = U.OBJECT_ID " +
            // 2 = LAST_NAME
            "AND LAST_NAME.ATTRN_ID = 2 " +
            "AND LAST_NAME.OBJECT_ID = U.OBJECT_ID " +
            // 4 = ROLE
            "AND USER_ROLE_ID.ATTRN_ID = 4 " +
            "AND USER_ROLE_ID.OBJECT_ID = U.OBJECT_ID " +
            "AND USER_ROLE.LIST_VALUE_ID = USER_ROLE_ID.LIST_VALUE_ID " +
            // 5 = CREATION_DATE
            "AND HIRE_DATE.ATTRN_ID = 5 " +
            "AND HIRE_DATE.OBJECT_ID = U.OBJECT_ID " +
            // 7 = STATUS
            "AND STATUS_ID.ATTRN_ID = 7 " +
            "AND STATUS_ID.OBJECT_ID = U.OBJECT_ID " +
            "AND STATUS.LIST_VALUE_ID = STATUS_ID.LIST_VALUE_ID;";

    String READ_USER_BY_LOGIN = "SELECT " +
            "U.OBJECT_ID AS USER_ID, " +
            "U.NAME AS LOGIN, " +
            "PASS.VALUE AS PASSWORD, " +
            "EMAIL.VALUE AS EMAIL, " +
            "FIRST_NAME.VALUE AS FIRST_NAME, " +
            "LAST_NAME.VALUE AS LAST_NAME, " +
            "USER_ROLE.VALUE AS USER_ROLE, " +
            "HIRE_DATE.DATE_VALUE AS HIRE_DATE, " +
            "STATUS.VALUE AS STATUS, " +
            "CASE WHEN EXISTS (" +
            "SELECT U.OBJECT_ID" +
            "FROM OBJECTS U, " +
            "OBJREFERENCE PROJ_ID, " +
            "OBJECTS PROJ, " +
            "ATTRIBUTES PROJ_STATUS " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.NAME = ? " +
            // 26 = PROJECT_USERS
            "AND PROJ_ID.ATTRN_ID = 26 " +
            "AND PROJ_ID.REFERENCE = U.OBJECT_ID " +
            // 2 = PROJECT
            "AND PROJ.OBJECT_TYPE_ID = 2 " +
            "AND PROJ.OBJECT_ID = PROJ_ID.OBJECT_ID " +
            // 7 = STATUS
            "AND PROJ_STATUS.ATTRN_ID = 7 " +
            "AND PROJ_STATUS.OBJECT_ID = PROJ.OBJECT_ID " +
            // 5 = ACTIVE
            "AND PROJ_STATUS.LIST_VALUE_ID = 5" +
            ") THEN (" +
            "SELECT PROJ.OBJECT_ID " +
            "FROM OBJECTS U, " +
            "OBJREFERENCE PROJ_ID, " +
            "OBJECTS PROJ, " +
            "ATTRIBUTES PROJ_STATUS " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.NAME = ? " +
            // 26 = PROJECT_USERS
            "AND PROJ_ID.ATTRN_ID = 26 " +
            "AND PROJ_ID.REFERENCE = U.OBJECT_ID " +
            // 2 = PROJECT
            "AND PROJ.OBJECT_TYPE_ID = 2 " +
            "AND PROJ.OBJECT_ID = PROJ_ID.OBJECT_ID " +
            // 7 = STATUS
            "AND PROJ_STATUS.ATTRN_ID = 7 " +
            "AND PROJ_STATUS.OBJECT_ID = PROJ.OBJECT_ID " +
            // 5 = ACTIVE
            "AND PROJ_STATUS.LIST_VALUE_ID = 5 " +
            ") ELSE NULL " +
            "END AS PROJ_ID " +
            "FROM OBJECTS U, " +
            "ATTRIBUTES PASS, " +
            "ATTRIBUTES EMAIL, " +
            "ATTRIBUTES FIRST_NAME, " +
            "ATTRIBUTES LAST_NAME, " +
            "LISTS USER_ROLE, ATTRIBUTES USER_ROLE_ID, " +
            "LISTS STATUS, ATTRIBUTES STATUS_ID " +
            // 1 = USER
            "WHERE U.OBJECT_TYPE_ID = 1 " +
            "AND U.NAME = ? " +
            // 6 = PASSWORD
            "AND PASS.ATTRN_ID = 6 " +
            "AND PASS.OBJECT_ID = U.OBJECT_ID " +
            // 3 = EMAIL
            "AND EMAIL.ATTRN_ID = 3 " +
            "AND EMAIL.OBJECT_ID = U.OBJECT_ID " +
            // 1 = FIRST_NAME
            "AND FIRST_NAME.ATTRN_ID = 1 " +
            "AND FIRST_NAME.OBJECT_ID = U.OBJECT_ID " +
            // 2 = LAST_NAME
            "AND LAST_NAME.ATTRN_ID = 2 " +
            "AND LAST_NAME.OBJECT_ID = U.OBJECT_ID " +
            // 4 = ROLE
            "AND USER_ROLE_ID.ATTRN_ID = 4 " +
            "AND USER_ROLE_ID.OBJECT_ID = U.OBJECT_ID " +
            "AND USER_ROLE.LIST_VALUE_ID = USER_ROLE_ID.LIST_VALUE_ID " +
            // 5 = CREATION_DATE
            "AND HIRE_DATE.ATTRN_ID = 5 " +
            "AND HIRE_DATE.OBJECT_ID = U.OBJECT_ID " +
            // 7 = STATUS
            "AND STATUS_ID.ATTRN_ID = 7 " +
            "AND STATUS_ID.OBJECT_ID = U.OBJECT_ID " +
            "AND STATUS.LIST_VALUE_ID = STATUS_ID.LIST_VALUE_ID;";

    String UPDATE_USER_PASSWORD = "UPDATE " +
            "ATTRIBUTES SET VALUE = ? " +
            // 6 = PASSWORD
            "WHERE ATTRN_ID = 6 " +
            "AND OBJECT_ID = ?;";

    String UPDATE_USER_FIRST_NAME = "UPDATE " +
            "ATTRIBUTES SET VALUE = ? " +
            // 1 = FIRST_NAME
            "WHERE ATTRN_ID = 1 " +
            "AND OBJECT_ID = ?;";

    String UPDATE_USER_LAST_NAME = "UPDATE " +
            "ATTRIBUTES SET VALUE = ? " +
            // 2 = LAST_NAME
            "WHERE ATTRN_ID = 2 " +
            "AND OBJECT_ID = ?;";

    String UPDATE_USER_ROLE = "UPDATE " +
            "ATTRIBUTES SET LIST_VALUE_ID = ? " +
            // 4 = ROLE
            "WHERE ATTRN_ID = 4 " +
            "AND OBJECT_ID = ?;";

    String INACTIVE_USER = "UPDATE " +
            // 6 = INACTIVE
            "ATTRIBUTES SET LIST_VALUE_ID = 6 " +
            // 7 = STATUS
            "WHERE ATTRN_ID = 7 " +
            "AND OBJECT_ID = ?;";

}
