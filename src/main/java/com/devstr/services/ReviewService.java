package com.devstr.services;

import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;

import java.math.BigInteger;
import java.util.List;

public interface ReviewService {

    /**
     * Method that creates new review
     *
     * @param authorId id of the review author
     * @param receiverId id of receiverId
     * @param projectId id of project where review where made(equal with receiverId if it is project review)
     * @param comment text of the review
     * @param marks 3 or 4 element size array with marks to the project
     * @param objType object type(USER_REVIEW or PROJECT_REVIEW)
     */
    void createReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks,
                      ObjectType objType);

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
     * Gets all user reviews left during certain project
     *
     * @param id id of the project
     * @return list with all user reviews written during project
     */
    List<UserReview> readUserReviewsByProjectId(BigInteger id);
}
