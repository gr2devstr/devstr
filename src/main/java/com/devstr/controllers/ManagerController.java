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
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ManagerController.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/tm/{tmId}/assignToProject/{projId}")
    public ResponseEntity<User> assignTechnicalManager(@PathVariable("tmId") long tmId,
                                                       @PathVariable("projId") long projId) {
        BigInteger techManId = BigInteger.valueOf(tmId);
        projectDAO.addDevOnProject(BigInteger.valueOf(projId), techManId);
        User tm = userDAO.readFullUserById(techManId);
        return new ResponseEntity<>(tm, HttpStatus.OK);
    }

    public ResponseEntity<Notification> sendNotification(String message, BigInteger receiverId) {
        BigInteger id = notificationDAO.createNotification(message, receiverId);
        return new ResponseEntity<>(new NotificationImpl.Builder(message, receiverId)
                .setNotificationId(id).build(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("/assign/decline")
    public ResponseEntity declineUser() {
        //TODO logic
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GROUP_MANAGER')")
    @GetMapping("/assign/confirm")
    public ResponseEntity assignDeveloper() {
        //TODO logic
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @GetMapping("/assign/remove")
    public ResponseEntity removeUserFromProject() {
        //TODO logic
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
