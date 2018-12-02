package com.devstr.dao;

import com.devstr.model.User;
import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;

public interface UserDAO {

    /**
     * Creates new USER object in the OBJECTS table from the DB
     *
     * @param login     user's object name in the DB (objects.name)
     * @param firstName user's first name attribute in the DB (attributes.value)
     * @param lastName  user's last name attribute in the DB (attributes.value)
     * @param email     user's email attribute in the DB (attributes.value)
     * @param password  user's password(encrypted) attribute in the DB (attributes.value)
     * @param userRole  user's role list value ID attribute in the DB (attributes.list_value_id)
     */
    void createUser(String login, String firstName, String lastName, String email, String password, UserRole userRole);

    /**
     * Reads basic user by ID from the DB
     *
     * @param id user's ID in the DB (objects.object_id)
     * @return basic User with: id, login, password, email, first name, last name, role & status fields.
     */
    User readBasicUserById(BigInteger id);

    /**
     * Reads full user by ID from the DB
     *
     * @param id user's ID in the DB (objects.object_id)
     * @return basic User with all fields.
     */
    User readFullUserById(BigInteger id);

    /**
     * Reads user ID by login from the DB
     *
     * @param login user's login in the DB (objects.name)
     * @return user's ID if exists
     */
    BigInteger readUserIdByLogin(String login);

    /**
     * Updates user password in the DB by user ID
     *
     * @param id       user's ID in the DB (objects.object_id)
     * @param password new password for user
     */
    void updateUserPassword(BigInteger id, String password);

    /**
     * Updates user email in the DB by user ID
     *
     * @param id    user's ID in the DB (objects.object_id)
     * @param email new email for user
     */
    void updateUserEmail(BigInteger id, String email);

    /**
     * Updates user first name in the DB by user ID
     *
     * @param id        user's ID in the DB (objects.object_id)
     * @param firstName new first name for user
     */
    void updateUserFirstName(BigInteger id, String firstName);

    /**
     * Updates user last name in the DB by user ID
     *
     * @param id       user's ID in the DB (objects.object_id)
     * @param lastName new last name for user
     */
    void updateUserLastName(BigInteger id, String lastName);

    /**
     * Updates user role in the DB by user ID
     *
     * @param id   user's ID in the DB (objects.object_id)
     * @param role new role for user
     */
    void updateUserRole(BigInteger id, UserRole role);

    /**
     * Updates user current project in the DB by user ID
     *
     * @param id        user's ID in the DB (objects.object_id)
     * @param projectId new project ID for user
     */
    void updateUserProjectId(BigInteger id, BigInteger projectId);

    /**
     * Updates user into inactive mode
     *
     * @param id user's ID in the DB (objects.object_id)
     */
    void inactivateUser(BigInteger id);

}
