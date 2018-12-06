package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.logger.DevstrLogger;
import com.devstr.dao.ReviewDAO;
import com.devstr.dao.impl.ReviewDAOImpl;
import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.services.ReviewService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewServiceImpl.class.getName());
    private ReviewDAO reviewDAO = new ReviewDAOImpl();

    @Override
    public void createReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks, ObjectType objType) {
        if (authorId == null) {
            LOGGER.warn("authorId is null");
            return;
        }
        if (receiverId == null) {
            LOGGER.warn("receiverId is null");
            return;
        }
        if (objType.equals(ObjectType.USER_REVIEW)) {
            if (projectId == null) {
                LOGGER.warn("projectId is null. There are no user reviews without a project");
                return;
            }
            reviewDAO.createUserReview(authorId, receiverId, projectId, comment, marks);
        }
        if (objType.equals(ObjectType.PROJECT_REVIEW)) {
            reviewDAO.createProjectReview(authorId, receiverId, comment, marks);
        }
        LOGGER.warn("Wrong object type");
    }

    @Override
    public Review readReviewById(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewById(id);
        LOGGER.warn("reviewId is null");
        return null;
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewsByRecId(id);
        LOGGER.warn("receiverId is null");
        return null;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        if (id != null)
            return reviewDAO.readReviewsByAuthorId(id);
        LOGGER.warn("authorId is null");
        return null;
    }

    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        if (id != null) {
            return reviewDAO.readUserReviewsByProjectId(id);
        }
        LOGGER.warn("projectId is null");
        return null;
    }

    @Override
    public double getAverageUserReviewMark(UserReview review) {
        return ((double) review.getCodeAmount() + review.getCodeQuality() + review.getCommunication()) / 3;
    }

    @Override
    public double getAverageProjectReviewMark(ProjectReview review) {
        return ((double) review.getExperienceQuality() + review.getOrganisationLevel() + review.getTeamSpirit() + review.getTimeManagement()) / 4;
    }

    @Override
    public double getAverageUserMarkOnProject(BigInteger projectId, BigInteger userId) {
        List<UserReview> reviews = reviewDAO.readUserReviewsByProjectId(projectId);
        double result = 0;
        List<UserReview> userReviews = new ArrayList<>();
        if (reviews == null) {
            LOGGER.warn("No reviews read by id " + projectId);
            return result;
        }
        for (UserReview review : reviews) {
            if (review.getReceiverId().equals(userId)) {
                result += getAverageUserReviewMark(review);
                userReviews.add(review);
            }
        }
        if (userReviews.size() != 0)
            result /= userReviews.size();
        else
            LOGGER.warn("there is no reviews for user " + userId + "on project with id " + projectId);
        return result;
    }

    @Override
    public double getAverageProjectMark(BigInteger projectId) {
        List<Review> projectReviews = reviewDAO.readReviewsByRecId(projectId);
        double result = 0;
        try {
            for (Review review : projectReviews) {
                result += getAverageProjectReviewMark((ProjectReview) review);
            }
        } catch (NullPointerException e){
            LOGGER.error("There is no reviews of project with id" + projectId, e);
        }catch (ClassCastException e) {
            LOGGER.error("Id:" + projectId + " is not a projectId", e);
        }
        if (projectReviews.size() != 0)
            result /= projectReviews.size();
        else
            LOGGER.warn("there is no reviews for project with id " + projectId);
        return result;
    }
}
