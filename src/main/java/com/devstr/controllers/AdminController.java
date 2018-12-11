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
        try{
            userDAO.createUser(user.getLogin(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRole());
            User responseUser = userDAO.readFullUserByLogin(user.getLogin());
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<User> inactivateUser(@PathVariable BigInteger id){
        try {
            userDAO.inactivateUser(id);
            User user = userDAO.readFullUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
