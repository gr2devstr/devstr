package com.devstr.controllers;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/admin/user")
public class AdminController {

    @Autowired
    UserDAO userDAO;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userDAO.createUser(user.getLogin(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRole());
        User responseUser = userDAO.readFullUserByLogin(user.getLogin());
        return new ResponseEntity<>(responseUser, HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){

        User oldUser =  userDAO.readFullUserById(user.getUserId());
        if(!user.getEmail().equals(oldUser.getEmail()))
            userDAO.updateUserEmail(user.getUserId(),user.getEmail());
        if(!user.getFirstName().equals(oldUser.getFirstName()))
            userDAO.updateUserFirstName(user.getUserId(),user.getFirstName());
        if(!user.getLastName().equals(oldUser.getLastName()))
            userDAO.updateUserLastName(user.getUserId(),user.getLastName());
        if(!user.getProjectId().equals(oldUser.getProjectId()))
            userDAO.updateUserProjectId(user.getUserId(),user.getProjectId());
        if(!user.getRole().equals(oldUser.getRole()))
            userDAO.updateUserRole(user.getUserId(),user.getRole());
        if(!user.getPassword().equals(oldUser.getPassword()))
            userDAO.updateUserPassword(user.getUserId(),user.getPassword());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<User> inactivateUser(@PathVariable BigInteger id){
        userDAO.inactivateUser(id);
        User user = userDAO.readFullUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
