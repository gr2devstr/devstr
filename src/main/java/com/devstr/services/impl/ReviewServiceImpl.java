package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.logger.DevstrLogger;
import com.devstr.dao.ReviewDAO;
import com.devstr.dao.impl.ReviewDAOImpl;
import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.services.interfaces.ReviewService;

import java.math.BigInteger;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewServiceImpl.class.getName());
    private ReviewDAO reviewDAO = new ReviewDAOImpl();

    @Override
    public void createReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks, ObjectType objType) {
        if (authorId == null) {
            LOGGER.warn("authorId is null");
            return;
        } if (receiverId == null) {
            LOGGER.warn("authorId is null");
            return;
        } if (objType.equals(ObjectType.USER_REVIEW)) {
            if (projectId == null){
                LOGGER.warn("projectId is null. There are no user reviews without project");
                return;
            }
            reviewDAO.createUserReview(authorId, receiverId, projectId, comment, marks);
        } if (objType.equals(ObjectType.PROJECT_REVIEW)) {
            reviewDAO.createProjectReview(authorId, receiverId, comment, marks);
        }
        LOGGER.warn("Wrong object type");
    }

    @Override
    public Review readReviewById(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewById(id);
        LOGGER.warn("Id is null");
        return null;
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewsByRecId(id);
        LOGGER.warn("Id is null");
        return null;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewsByAuthorId(id);
        LOGGER.warn("Id is null");
        return null;
    }

    @Override
    public List<ProjectReview> readReviewsByProjectId(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewsByProjectId(id);
        LOGGER.warn("Id is null");
        return null;
    }

    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        if (id != null) {
            return reviewDAO.readUserReviewsByProjectId(id);
        }
        LOGGER.warn("Id is null");
        return null;
    }
}
