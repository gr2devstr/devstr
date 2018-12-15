package com.devstr.controllers;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.NotificationDAO;
import com.devstr.dao.ProjectDAO;
import com.devstr.dao.UserDAO;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Notification;
import com.devstr.model.User;
import com.devstr.model.impl.NotificationImpl;
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

    @Autowired
    private NotificationDAO notificationDAO;

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/assign/technical")
    public ResponseEntity<User> assignTechnicalManager(@RequestBody User technicalManager, BigInteger projectId) {
        projectDAO.addDevOnProject(projectId, technicalManager.getUserId());
        return new ResponseEntity<>(technicalManager, HttpStatus.OK);
    }

    public ResponseEntity<Notification> sendNotification(String message, BigInteger receiverId) {
        BigInteger id = notificationDAO.createNotification(message, receiverId);
        return new ResponseEntity<>(new NotificationImpl.Builder(message, receiverId)
                .setNotificationId(id).build(), HttpStatus.OK);
    }

//    public ResponseEntity<User> requestUser(User developer) {
//
//    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("assign/confirm")
    public ResponseEntity declineUser() {
        //sendNotification
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("assign/confirm")
    public ResponseEntity<User> assignDeveloper(@RequestBody User developer, BigInteger projectId) {
        projectDAO.addDevOnProject(projectId, developer.getUserId());
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/assign/remove")
    public ResponseEntity<User> removeUserFromProject(@RequestBody User user) {
        projectDAO.deactivateUserOnProject(user.getUserId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
