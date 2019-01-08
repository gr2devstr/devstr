package com.devstr.controllers.validation;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.ProjectDAO;
import com.devstr.dao.UserDAO;
import com.devstr.dao.UtilDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.User;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.enumerations.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigInteger;

@Component
public class ProjectValidator {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(ProjectValidator.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UtilDAO utilDAO;

    public boolean isNameValid(String name) {
        if (isTextNotValid(name)){
            String message = "Name: " + name + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return false;
        }
        try {
            projectDAO.readProjectByName(name);
            String message = "Name: " + name + " - isn't valid, already exists";
            LOGGER.info(message);
            return false;
        } catch (DaoException exc) {
            return true;
        }
    }

    public boolean isPmIdValid(BigInteger id) {
        if (utilDAO.checkObjectType(ObjectType.USER.getId(), id)) {
            User pm = userDAO.readBasicUserById(id);
            return pm.getRole().equals(UserRole.PROJECT_MANAGER);
        } else {
            return false;
        }
    }

    public boolean isProjectIdValid(BigInteger id) {
        return utilDAO.checkObjectType(ObjectType.PROJECT.getId(), id);
    }

    public boolean isTextNotValid(String text) {
        return ((text == null) || (text.trim().isEmpty()));
    }

}
