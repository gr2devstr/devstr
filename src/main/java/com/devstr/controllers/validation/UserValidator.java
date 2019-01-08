package com.devstr.controllers.validation;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.UserDAO;
import com.devstr.dao.UtilDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.User;
import com.devstr.model.enumerations.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigInteger;

@Component
public class UserValidator {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(UserValidator.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UtilDAO utilDAO;

    public boolean isLoginValid(String login) {
        if (isTextNotValid(login)){
            String message = "Login: " + login + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return false;
        }
        try {
            userDAO.readUserIdByLogin(login);
            String message = "Login: " + login + " - isn't valid, already exists";
            LOGGER.info(message);
            return false;
        } catch (DaoException exc) {
            return true;
        }
    }

    public boolean isEmailValid(String email) {
        if (isTextNotValid(email)){
            String message = "Email: " + email + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return false;
        }
        try {
            userDAO.readUserIdByEmail(email);
            String message = "Email: " + email + " - isn't valid, already exists";
            LOGGER.info(message);
            return false;
        } catch (DaoException exc) {
            return true;
        }
    }

    public boolean isUserValid(User user) {
        if (isTextNotValid(user.getFirstName())) {
            return false;
        }
        if (isTextNotValid(user.getLastName())) {
            return false;
        }
        if (isTextNotValid(user.getPassword())) {
            return false;
        }
        if (user.getRole() == null) {
            return  false;
        }
        return utilDAO.checkObjectType(ObjectType.USER.getId(), user.getManagerId());
    }

    public boolean isUserIdValid(BigInteger id) {
        return utilDAO.checkObjectType(ObjectType.USER.getId(), id);
    }

    private boolean isTextNotValid(String text) {
        return ((text == null) || (text.trim().isEmpty()));
    }

}
