package com.devstr.dao;

import com.devstr.model.*;

import java.math.BigInteger;
import java.util.List;

public interface ReviewDAO {
    String INSERT_USER_REVIEW = "INSERT INTO OBJECTS()";
    String INSERT_UR_ATTRIBUTES = "";

    /**
     * Method that writes new user review to the database
     *
     * @param authorId id of the review author
     * @param receiverId id of receiver
     * @param projectId id of project where review where made
     * @param comment text of the review
     * @param marks 3 element size array with marks to the user in order of metrics
     */
    void createUserReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks);

    /**
     * Method that writes new project review to the database
     *
     * @param authorId id of the review author
     * @param receiverId id of receiving project
     * @param comment text of the review
     * @param marks 4 element size array with marks from 1 to 10 to the project in order of metrics
     */
    void createProjectReview(BigInteger authorId, BigInteger receiverId, String comment, int[] marks);

    /**
     * Method gets the review by review's id
     *
     * @param id id of review
     * @return review with such id in database, null if there is no review with such id
     */
    Review readReviewById(BigInteger id);

    /**
     * Gets all reviews of user with specified id
     *
     * @param id id of user that received the reviews
     * @return list with the reviews
     */
    List<Review> readReviewsByRecId(BigInteger id);

    /**
     * Gets all the reviews written by certain author
     *
     * @param id author id in table OBJECTS
     * @return list with the reviews
     */
    List<Review> readReviewsByAuthorId(BigInteger id);

    /**
     * Gets all the reviews of a project(Project reviews only!)
     *
     * @param id id of the project
     * @return list with reviews of the project
     */
    List<ProjectReview> readReviewsByProjectId(BigInteger id);

    /**
     * Gets all user reviews left during certain project
     *
     * @param id id of the project
     * @return list with all user reviews written during project
     */
    List<UserReview> readUserReviewsByProjectId(BigInteger id);
}
