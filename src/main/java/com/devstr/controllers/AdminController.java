package com.devstr.controllers;

import com.devstr.dao.UserDAO;
import com.devstr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    UserDAO userDAO;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        if(user!=null){
            userDAO.createUser(user.getLogin(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getRole());
            return userDAO.readBasicUserById(userDAO.readUserIdByLogin(user.getLogin()));
        }
        return null;
    }

    @GetMapping("/{login}")
    public User getUserByLogin(@PathVariable String login){
        return userDAO.readBasicUserById(userDAO.readUserIdByLogin(login));
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable BigInteger id){
        if (id != null && id.longValue() > 0L) {
            User user = userDAO.readBasicUserById(id);
            if (user!=null) return user;
        }
        return null;
    }

    @PatchMapping("/update")
    public User updateUser(@RequestBody User user){

        return user;
    }

    @PatchMapping("/{id}/inactivate")
    public User inactivateUser(@PathVariable BigInteger id){
        userDAO.inactivateUser(id);
        return userDAO.readFullUserById(id);
    }

    @GetMapping("users")
    public List<User> getAll(){
        List<User> result = new ArrayList<>();

        return result;
    }
}
