package com.devstr.controllers;

import com.devstr.DevstrFactoryManager;
import com.devstr.controllers.validation.UserValidator;
import com.devstr.dao.UserDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class AdminController {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(AdminController.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (!userValidator.isLoginValid(user.getLogin())) {
            String message = "User with this login already exists";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        if (!userValidator.isEmailValid(user.getEmail())) {
            String message = "User with this email already exists";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (userValidator.isUserValid(user)) {
                userDAO.createUser(
                        user.getLogin(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getManagerId(),
                        user.getPassword(),
                        user.getRole()
                );
                User responseUser = userDAO.readFullUserByLogin(user.getLogin());
                return new ResponseEntity<>(responseUser, HttpStatus.OK);
            } else {
                String message = "User data is not valid";
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User oldUser = userDAO.readFullUserByLogin(user.getLogin());
            if (userValidator.isUserValid(user)) {
                if (!user.getFirstName().equals(oldUser.getFirstName())) {
                    userDAO.updateUserFirstName(user.getUserId(), user.getFirstName());
                }
                if (!user.getLastName().equals(oldUser.getLastName())) {
                    userDAO.updateUserLastName(user.getUserId(), user.getLastName());
                }
                if (!user.getPassword().equals(oldUser.getPassword())) {
                    userDAO.updateUserPassword(user.getUserId(), user.getPassword());
                }
                if (!user.getRole().equals(oldUser.getRole())) {
                    userDAO.updateUserRole(user.getUserId(), user.getRole());
                }
                if (user.getProjectId() != null && !user.getProjectId().equals(oldUser.getProjectId())) {
                    userDAO.updateUserProjectId(user.getUserId(), user.getProjectId());
                }
                if (user.getManagerId() != null && !user.getManagerId().equals(oldUser.getManagerId())) {
                    userDAO.updateUserManagerId(user.getUserId(), user.getManagerId());
                }
                User updatedUser = userDAO.readFullUserByLogin(user.getLogin());
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                String message = "User data is not valid";
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/inactivate/{id}")
    public ResponseEntity<User> inactivateUser(@PathVariable("id") BigInteger id) {
        try {
            if (userValidator.isUserIdValid(id)) {
                userDAO.inactivateUser(id);
                User user = userDAO.readFullUserById(id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                String message = "User doesn't exist, ID: " + id;
                LOGGER.warn(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    private MultiValueMap<String, String> getErrorMsg(String message) {
        MultiValueMap<String, String> errorMessage = new LinkedMultiValueMap<>();
        List<String> errorBody = Collections.singletonList(message);
        errorMessage.put("Error", errorBody);
        return errorMessage;
    }

}
