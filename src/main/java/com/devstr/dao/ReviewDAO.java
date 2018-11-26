package com.devstr.dao;

import com.devstr.model.Review;
import com.devstr.model.enumerations.ObjectType;

import java.math.BigInteger;
import java.util.List;

public interface ReviewDAO {
    /**
     * Method that creates new review
     *
     * @param authorId id of the review author
     * @param recieverId id of recieverId
     * @param project_id id of project where review where made(equal with recieverId if it is project review)
     * @param comment text of the review
     * @param marks 3 or 4 element size array with marks to the project
     * @param objType object type(USER_REVIEW or PROJECT_REVIEW)
     */
    void createReview(BigInteger authorId, BigInteger recieverId, BigInteger project_id, String comment, int[] marks,
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
     * @param id id of user that recieved the reviews
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
     * @return
     */
    List<Review> readReviewsByProjectId(BigInteger id);
}
