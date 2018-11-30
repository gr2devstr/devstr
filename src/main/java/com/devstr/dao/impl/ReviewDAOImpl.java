package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.logger.DevstrLogger;
import com.devstr.dao.ReviewDAO;
import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.impl.ProjectReviewImpl;
import com.devstr.model.impl.UserReviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ReviewDAOImpl implements ReviewDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewDAOImpl.class.getName());

    @Override
    public void createUserReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks) {
        jdbcTemplate.update(INSERT_USER_REVIEW, comment, marks[0], marks[1], marks[2], receiverId, authorId, projectId);
    }

    @Override
    public void createProjectReview(BigInteger authorId, BigInteger receiverId, String comment, int[] marks) {
        jdbcTemplate.update(INSERT_PROJECT_REVIEW, comment, marks[0], marks[1], marks[2], marks[3], receiverId, authorId);
    }

    @Override
    public Review readReviewById(BigInteger id) {
        ObjectType type = getObjectTypeById(id);
        if (type.equals(ObjectType.USER_REVIEW)) {
            return readUserReviewById(id);
        }
        if (type.equals(ObjectType.PROJECT_REVIEW)) {
            return readProjectReviewById(id);
        }
        return null;
    }

    private UserReview readUserReviewById(BigInteger id) {
        return jdbcTemplate.queryForObject(SELECT_USER_REVIEW_BY_ID, new UserReviewRowMapper(), id);
    }

    private ProjectReview readProjectReviewById(BigInteger id) {
        return jdbcTemplate.queryForObject(SELECT_PROJECT_REVIEW_BY_ID, new ProjectReviewRowMapper(), id);
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        List<Review> reviews = new ArrayList<>();
        ObjectType type = getObjectTypeById(id);
        if (type.equals(ObjectType.USER_REVIEW)) {
            reviews.addAll(jdbcTemplate.query(SELECT_USER_REVIEWS_BY_REC, new Object[] {}, new ProjectReviewRowMapper()));
        }
        if (type.equals(ObjectType.PROJECT_REVIEW)) {
            reviews.addAll(jdbcTemplate.query(SELECT_PROJECT_REVIEWS_BY_REC, new Object[] {}, new ProjectReviewRowMapper()));
        }
        return reviews;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        List<BigInteger> reviewIds = jdbcTemplate.queryForList(SELECT_REVIEWS_ID_BY_AUTHOR, new Object[] {id}, BigInteger.class);
        List<Review> result = new ArrayList<>();
        for (BigInteger reviewId: reviewIds) {
            result.add(readReviewById(reviewId));
        }
        return result;
    }

    @Override
    public List<ProjectReview> readReviewsByProjectId(BigInteger id) {
        return jdbcTemplate.query(SELECT_PROJECT_REVIEWS_BY_PROJECT_ID, new Object[] {}, new ProjectReviewRowMapper());
    }

    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        return jdbcTemplate.query(SELECT_USER_REVIEWS_BY_PROJECT_ID ,new Object[] {}, new UserReviewRowMapper());
    }

    class UserReviewRowMapper implements RowMapper<UserReview> {

        @Override
        public UserReview mapRow(ResultSet resultSet, int i) throws SQLException {
            String text = resultSet.getString("COMMENT");
            BigInteger id = BigInteger.valueOf(resultSet.getLong("REVIEW_ID"));
            BigInteger authorId = BigInteger.valueOf(resultSet.getLong("AUTHOR"));
            BigInteger receiverId = BigInteger.valueOf(resultSet.getLong("RECEIVER"));
            BigInteger projectId = BigInteger.valueOf(resultSet.getLong("PROJECT"));
            int quality = resultSet.getInt("QUALITY");
            int amount = resultSet.getInt("AMOUNT");
            int communication = resultSet.getInt("COMMUNICATION");
            return new UserReviewImpl.Builder(authorId, receiverId, text, quality, amount, communication, projectId).build();
        }
    }
    class ProjectReviewRowMapper implements RowMapper<ProjectReview> {

        @Override
        public ProjectReview mapRow(ResultSet resultSet, int i) throws SQLException {
            String text = resultSet.getString("COMMENT");
            BigInteger id = BigInteger.valueOf(resultSet.getLong("REVIEW_ID"));
            BigInteger authorId = BigInteger.valueOf(resultSet.getLong("AUTHOR"));
            BigInteger receiverId = BigInteger.valueOf(resultSet.getLong("RECEIVER"));
            int xp = resultSet.getInt("XP");
            int organization = resultSet.getInt("ORGANIZATION");
            int time_mg = resultSet.getInt("TIME_MG");
            int team_spirit = resultSet.getInt("SPIRIT");
            return new ProjectReviewImpl.Builder(authorId, receiverId, text, xp, organization, time_mg, team_spirit).build();
        }
    }
    private ObjectType getObjectTypeById(BigInteger id) {
        return ObjectType.valueOf(jdbcTemplate.queryForObject(SELECT_OBJECT_TYPE_BY_OBJECT_ID, new Object[]{id}, String.class));
    }
}
