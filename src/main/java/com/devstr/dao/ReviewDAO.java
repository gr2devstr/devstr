package com.devstr.dao;

import com.devstr.model.*;

import java.math.BigInteger;
import java.util.List;

public interface ReviewDAO {
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

    String INSERT_USER_REVIEW = "BEGIN INSERT INTO OBJECTS(OBJECT_TYPE_ID, NAME) VALUES (4, 'U');" +
            "INSERT ALL INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE) VALUES(5, OBJ_ID.CURRVAL, SYSDATE)"+
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(14, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(15, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(16, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(17, OBJ_ID.CURRVAL, ?) " +
            "INTO OBJREFERENCE(ATTRN_ID, REFERENCE, OBJECT_ID)VALUES(25, OBJ_ID.CURRVAL, ?) " +
            "INTO OBJREFERENCE(ATTRN_ID, OBJECT_ID, REFERENCE) VALUES(27, OBJ_ID.CURRVAL, ?) " +
            "INTO OBJREFERENCE(ATTRN_ID, REFERENCE, OBJECT_ID) VALUES(28, ?, OBJ_ID.CURRVAL) " +
            "SELECT * FROM DUAL; END;";
    String INSERT_PROJECT_REVIEW = "BEGIN INSERT INTO OBJECTS(OBJECT_TYPE_ID, NAME) VALUES (5, 'P'); " +
            "INSERT ALL INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE) VALUES(5, OBJ_ID.CURRVAL, SYSDATE) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(14, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(18, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(19, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(20, OBJ_ID.CURRVAL, ?) " +
            "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE) VALUES(21, OBJ_ID.CURRVAL, ?) " +
            "INTO OBJREFERENCE(ATTRN_ID, REFERENCE, OBJECT_ID) VALUES(25, OBJ_ID.CURRVAL, ?) " +
            "INTO OBJREFERENCE(ATTRN_ID, OBJECT_ID, REFERENCE) VALUES(27, OBJ_ID.CURRVAL, ?) " +
            "SELECT * FROM DUAL; END;";
    String SELECT_USER_REVIEW_BY_ID = "SELECT r.OBJECT_ID AS REVIEW_ID, t.VALUE AS TEXT, u.OBJECT_ID AS AUTHOR, rec.OBJECT_ID AS RECEIVER, p.OBJECT_ID AS PROJECT, q.VALUE AS QUALITY, a.VALUE AS AMOUNT, c.VALUE AS COMMUNICATION " +
            "FROM OBJECTS u, OBJECTS r, OBJECTS rec, OBJECTS p, ATTRIBUTES t, ATTRIBUTES q, ATTRIBUTES a, ATTRIBUTES c, OBJREFERENCE aut_ref, OBJREFERENCE rec_ref, OBJREFERENCE pr_ref " +
            "WHERE r.OBJECT_TYPE_ID = 4 AND r.OBJECT_ID = ? AND rec.OBJECT_TYPE_ID = 1 AND u.OBJECT_TYPE_ID = 1 AND p.OBJECT_TYPE_ID = 2 " +
            " AND u.OBJECT_TYPE_ID = 1 AND t.ATTRN_ID = 14 AND t.OBJECT_ID = r.OBJECT_ID AND t.OBJECT_ID = r.OBJECT_ID " +
            " AND q.ATTRN_ID = 15 AND q.OBJECT_ID = r.OBJECT_ID AND a.ATTRN_ID = 16 AND a.OBJECT_ID = r.OBJECT_ID " +
            " AND c.ATTRN_ID = 17 AND c.OBJECT_ID = r.OBJECT_ID AND rec_ref.ATTRN_ID = 25 AND rec_ref.OBJECT_ID = rec.OBJECT_ID" +
            " AND rec_ref.REFERENCE = r.OBJECT_ID AND aut_ref.ATTRN_ID = 27 AND aut_ref.OBJECT_ID = r.OBJECT_ID " +
            " AND aut_ref.REFERENCE = u.OBJECT_ID AND pr_ref.ATTRN_ID = 28 AND pr_ref.OBJECT_ID = r.OBJECT_ID AND pr_ref.REFERENCE = p.OBJECT_ID";
    String SELECT_USER_REVIEWS_BY_PROJECT_ID = "SELECT r.OBJECT_ID AS REVIEW_ID, t.VALUE AS TEXT, u.OBJECT_ID AS AUTHOR, rec.OBJECT_ID AS RECEIVER, p.OBJECT_ID AS PROJECT, q.VALUE AS QUALITY, a.VALUE AS AMOUNT, c.VALUE AS COMMUNICATION " +
            "FROM OBJECTS u, OBJECTS r, OBJECTS rec, OBJECTS p, ATTRIBUTES t, ATTRIBUTES q, ATTRIBUTES a, ATTRIBUTES c, OBJREFERENCE aut_ref, OBJREFERENCE rec_ref, OBJREFERENCE pr_ref " +
            "WHERE r.OBJECT_TYPE_ID = 4 AND p.OBJECT_ID = ? AND rec.OBJECT_TYPE_ID = 1 AND u.OBJECT_TYPE_ID = 1 AND p.OBJECT_TYPE_ID = 2 " +
            " AND u.OBJECT_TYPE_ID = 1 AND t.ATTRN_ID = 14 AND t.OBJECT_ID = r.OBJECT_ID AND t.OBJECT_ID = r.OBJECT_ID " +
            " AND q.ATTRN_ID = 15 AND q.OBJECT_ID = r.OBJECT_ID AND a.ATTRN_ID = 16 AND a.OBJECT_ID = r.OBJECT_ID " +
            " AND c.ATTRN_ID = 17 AND c.OBJECT_ID = r.OBJECT_ID AND rec_ref.ATTRN_ID = 25 AND rec_ref.OBJECT_ID = rec.OBJECT_ID" +
            " AND rec_ref.REFERENCE = r.OBJECT_ID AND aut_ref.ATTRN_ID = 27 AND aut_ref.OBJECT_ID = r.OBJECT_ID " +
            " AND aut_ref.REFERENCE = u.OBJECT_ID AND pr_ref.ATTRN_ID = 28 AND pr_ref.OBJECT_ID = r.OBJECT_ID AND pr_ref.REFERENCE = p.OBJECT_ID";
    String SELECT_PROJECT_REVIEW_BY_ID = "";

    String SELECT_REVIEWS_ID_BY_AUTHOR = "";
    String SELECT_PROJECT_REVIEWS_BY_PROJECT_ID ="";
    String SELECT_USER_REVIEWS_BY_REC = "";
    String SELECT_PROJECT_REVIEWS_BY_REC = "";
    String SELECT_OBJECT_TYPE_BY_OBJECT_ID = " SELECT t.NAME FROM OBJTYPE t, OBJECTS o WHERE o.OBJECT_ID = ? AND t.OBJEC_TYPE_ID = o.OBJECT_TYPE_ID";
}
