package com.devstr.controllers;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.Collection;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PreAuthorize("hasAuthority('DEVELOPER') " +
            "OR hasAuthority('TECHNICAL_MANAGER') " +
            "OR hasAuthority('PROJECT_MANAGER') " +
            "OR hasAuthority('GROUP_MANAGER')")
    @GetMapping("/basic/id/{id}")
    public ResponseEntity<User> getBasicUserById(@PathVariable("id") long id) {
        try {
            User user = userDAO.readBasicUserById(BigInteger.valueOf(id));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException exc) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DEVELOPER') " +
            "OR hasAuthority('TECHNICAL_MANAGER') " +
            "OR hasAuthority('PROJECT_MANAGER') " +
            "OR hasAuthority('GROUP_MANAGER')")
    @GetMapping("/basic/login/{login}")
    public ResponseEntity<User> getBasicUserByLogin(@PathVariable("login") String login) {
        try {
            User user = userDAO.readBasicUserByLogin(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException exc) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DEVELOPER') " +
            "OR hasAuthority('TECHNICAL_MANAGER') " +
            "OR hasAuthority('PROJECT_MANAGER') " +
            "OR hasAuthority('GROUP_MANAGER')")
    @GetMapping("/full/id/{id}")
    public ResponseEntity<User> getFullUserById(@PathVariable("id") long id) {
        try {
            User user = userDAO.readFullUserById(BigInteger.valueOf(id));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException exc) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DEVELOPER') " +
            "OR hasAuthority('TECHNICAL_MANAGER') " +
            "OR hasAuthority('PROJECT_MANAGER') " +
            "OR hasAuthority('GROUP_MANAGER')")
    @GetMapping("/full/login/{login}")
    public ResponseEntity<User> getFullUserByLogin(@PathVariable("login") String login) {
        try {
            User user = userDAO.readFullUserByLogin(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException exc) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('DEVELOPER') " +
            "OR hasAuthority('TECHNICAL_MANAGER') " +
            "OR hasAuthority('PROJECT_MANAGER') " +
            "OR hasAuthority('GROUP_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getAllUsers() {
        try {
            Collection<User> users = userDAO.readAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (RuntimeException exc) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO: implement create and read methods for Reviews. Add error body for NOT_FOUND response.

}
