package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.logger.DevstrLogger;
import com.devstr.dao.ReviewDAO;
import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewDAOImpl.class.getName());

    @Override
    public void createUserReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks) {

    }

    @Override
    public void createProjectReview(BigInteger authorId, BigInteger receiverId, String comment, int[] marks) {

    }

    @Override
    public Review readReviewById(BigInteger id) {
        return null;
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        return null;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        return null;
    }

    @Override
    public List<ProjectReview> readReviewsByProjectId(BigInteger id) {
        return null;
    }

    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        return null;
    }
}
