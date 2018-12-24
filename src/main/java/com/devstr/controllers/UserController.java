package com.devstr.controllers;

import com.devstr.controllers.validation.UserReviewValidator;
import com.devstr.dao.ReviewDAO;
import com.devstr.dao.UserDAO;
import com.devstr.dao.UtilDAO;
import com.devstr.exception.DaoException;
import com.devstr.model.User;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private UtilDAO utilDAO;

    @PreAuthorize("hasAnyAuthority('ADMIN','PROJECT_MANAGER','TECHNICAL_MANAGER','GROUP_MANAGER','DEVELOPER')")
    @GetMapping("/basic/id/{id}")
    public ResponseEntity<User> getBasicUserById(@PathVariable("id") long id) {
        try {
            BigInteger bid = BigInteger.valueOf(id);
            if (utilDAO.checkObjectType(ObjectType.USER.getId(), bid)) {
                User user = userDAO.readBasicUserById(bid);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                String message = "User with ID: " + id + " not found or ID incorrect";
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PROJECT_MANAGER','TECHNICAL_MANAGER','GROUP_MANAGER','DEVELOPER')")
    @GetMapping("/basic/login/{login}")
    public ResponseEntity<User> getBasicUserByLogin(@PathVariable("login") String login) {
        try {
            User user = userDAO.readBasicUserByLogin(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PROJECT_MANAGER','TECHNICAL_MANAGER','GROUP_MANAGER','DEVELOPER')")
    @GetMapping("/full/id/{id}")
    public ResponseEntity<User> getFullUserById(@PathVariable("id") long id) {
        try {
            BigInteger bid = BigInteger.valueOf(id);
            if (utilDAO.checkObjectType(ObjectType.USER.getId(), bid)) {
                User user = userDAO.readFullUserById(bid);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                String message = "User with ID: " + id + " not found or ID incorrect";
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PROJECT_MANAGER','TECHNICAL_MANAGER','GROUP_MANAGER','DEVELOPER')")
    @GetMapping("/full/login/{login}")
    public ResponseEntity<User> getFullUserByLogin(@PathVariable("login") String login) {
        try {
            User user = userDAO.readFullUserByLogin(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PROJECT_MANAGER','TECHNICAL_MANAGER','GROUP_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getAllUsers() {
        try {
            Collection<User> users = userDAO.readAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('DEVELOPER','TECHNICAL_MANAGER')")
    @GetMapping("/review/create")
    public ResponseEntity<UserReview> createUserReview(@RequestBody UserReview review) {
        if (!UserReviewValidator.isReviewValid(review)) {
            return new ResponseEntity<>(review, getErrorMsg("Review is not valid"), HttpStatus.BAD_REQUEST);
        }
        try {
            BigInteger reviewId = reviewDAO.createUserReview(
                    review.getAuthorId(),
                    review.getReceiverId(),
                    review.getProjectId(),
                    review.getComment(),
                    review.getAllMarksAsArray());
            UserReview createdReview = reviewDAO.readUserReviewById(reviewId);
            return new ResponseEntity<>(createdReview, HttpStatus.OK);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('PROJECT_MANAGER','TECHNICAL_MANAGER')")
    @GetMapping("/review/{review}/user/{user}")
    public ResponseEntity<UserReview> readUserReviewById(@PathVariable("review") long reviewId,
                                                         @PathVariable("user") long userId) {
        try {
            if (!utilDAO.checkObjectType(ObjectType.USER_REVIEW.getId(), BigInteger.valueOf(reviewId))) {
                return new ResponseEntity<>(null, getErrorMsg("Review not found"), HttpStatus.NOT_FOUND);
            }
            UserReview review = reviewDAO.readUserReviewById(BigInteger.valueOf(reviewId));
            if (UserReviewValidator.isAcceptableToView(review.getReceiverId(), BigInteger.valueOf(userId))) {
                return new ResponseEntity<>(review, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, getErrorMsg("Not permitted to view"), HttpStatus.BAD_REQUEST);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('PROJECT_MANAGER','TECHNICAL_MANAGER')")
    @GetMapping("/review/reciever/{id}")
    public ResponseEntity<List<UserReview>> readReviewsByRecieverId(@PathVariable("id") long id) {
        try {
            if (!utilDAO.checkObjectType(ObjectType.USER.getId(), BigInteger.valueOf(id))) {
                return new ResponseEntity<>(null, getErrorMsg("User not found"), HttpStatus.NOT_FOUND);
            }
            List<UserReview> reviews = reviewDAO.readReviewsByRecId(BigInteger.valueOf(id));
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (DaoException exc) {
            return new ResponseEntity<>(null, getErrorMsg(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MultiValueMap<String, String> getErrorMsg(String message) {
        MultiValueMap<String, String> errorMessage = new LinkedMultiValueMap<>();
        List<String> errorBody = Collections.singletonList(message);
        errorMessage.put("Error", errorBody);
        return errorMessage;
    }

}
