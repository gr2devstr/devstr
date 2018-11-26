package com.devstr.dao.impl;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;

public class UserDAOImpl implements UserDAO {

    @Override
    public void createUser(String login, String firstName, String lastName, String email, String password, UserRole userRole) {

    }

    @Override
    public User readUserById(BigInteger id) {
        return null;
    }

    @Override
    public User readUserByLogin(String login) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void inactivateUser(User user) {

    }

}
