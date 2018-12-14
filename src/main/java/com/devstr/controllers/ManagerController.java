package com.devstr.controllers;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.ProjectDAO;
import com.devstr.dao.UserDAO;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Project;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("api/user/manager")
public class ManagerController {
    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ManagerController.class.getName());
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/assign/technical")
    public ResponseEntity<User> assignTechnicalManager(@RequestBody User technicalManager, BigInteger projectId) {
        try {
            projectDAO.addDevOnProject(projectId, technicalManager.getUserId());
            return new ResponseEntity<>(technicalManager, HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Could not set user on project", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    public ResponseEntity<> sendNotitfication() {
//
//    }
//
//    public ResponseEntity<User> requestUser(User developer) {
//
//    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("assign/confirm")
    public ResponseEntity<User> declineUser() {
        //sendNotification
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("assign/confirm")
    public ResponseEntity<User> assignDeveloper(@RequestBody User developer, BigInteger projectId) {
        try {
            projectDAO.addDevOnProject(projectId, developer.getUserId());
            return new ResponseEntity<>(developer, HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Could not set user on project", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/assign/remove")
    public ResponseEntity<User> removeUserFromProject(@RequestBody User user) {
        try {
            projectDAO.deactivateUserOnProject(user.getUserId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException e) {
            LOGGER.error("Could not remove user from project: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
