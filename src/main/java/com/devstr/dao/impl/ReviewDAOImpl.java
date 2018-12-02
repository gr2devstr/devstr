package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.logger.DevstrLogger;
import com.devstr.dao.ReviewDAO;
import com.devstr.model.ProjectReview;
import com.devstr.model.Review;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.impl.ProjectReviewImpl;
import com.devstr.model.impl.UserReviewImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

@Repository
@Transactional
public class ReviewDAOImpl extends AbstractDAOImpl implements ReviewDAO {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewDAOImpl.class.getName());

    @Override
    @Transactional
    public void createUserReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks) {
        BigInteger id = createObject(ObjectType.USER_REVIEW.getId(), "UR" + authorId + receiverId);
        createAttributeDateValue(AttributeID.CREATION_DATE.getId(), id, new Date(Calendar.getInstance().getTime().getTime()));
        createAttributeValue(AttributeID.REVIEW_TEXT.getId(), id, comment);
        createAttributeValue(AttributeID.JOB_QUALITY.getId(), id, String.valueOf(marks[0]));
        createAttributeValue(AttributeID.JOB_AMOUNT.getId(), id, String.valueOf(marks[1]));
        createAttributeValue(AttributeID.COMMUNICATION.getId(), id, String.valueOf(marks[2]));
        createObjectReference(AttributeID.REVIEWS.getId(), receiverId, id);
        createObjectReference(AttributeID.REVIEW_AUTHOR.getId(), id, authorId);
        createObjectReference(AttributeID.PROJECT.getId(), id, projectId);
    }

    @Override
    @Transactional
    public void createProjectReview(BigInteger authorId, BigInteger receiverId, String comment, int[] marks) {
        BigInteger id = createObject(ObjectType.PROJECT_REVIEW.getId(), "P" + authorId + receiverId);
        createAttributeDateValue(AttributeID.CREATION_DATE.getId(), id, new Date(Calendar.getInstance().getTime().getTime()));
        createAttributeValue(AttributeID.REVIEW_TEXT.getId(), id, comment);
        createAttributeValue(AttributeID.XP_QUALITY.getId(), id, String.valueOf(marks[0]));
        createAttributeValue(AttributeID.TEAM_SPIRIT.getId(), id, String.valueOf(marks[1]));
        createAttributeValue(AttributeID.ORGANIZATION.getId(), id, String.valueOf(marks[2]));
        createAttributeValue(AttributeID.TIME_MANAGMENT.getId(), id, String.valueOf(marks[3]));
        createObjectReference(AttributeID.REVIEWS.getId(), receiverId, id);
        createObjectReference(AttributeID.REVIEW_AUTHOR.getId(), id, authorId);
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
        LOGGER.warn("Incompatible object type: required Review, got " + type.toString());
        return null;
    }

    private UserReview readUserReviewById(BigInteger id) {
        Iterator<BigInteger> author = readObjectReferences(AttributeID.REVIEW_AUTHOR.getId(), id).iterator();
        Iterator<BigInteger> receiver = readObjectByReference(AttributeID.REVIEWS.getId(), id).iterator();
        Iterator<BigInteger> project = readObjectReferences(AttributeID.PROJECT.getId(), id).iterator();
        return new UserReviewImpl.Builder(author.next(), receiver.next(), readAttributeValue(BigInteger.valueOf(14L), id),
                Integer.valueOf(readAttributeValue(AttributeID.JOB_QUALITY.getId(), id)),
                Integer.valueOf(readAttributeValue(AttributeID.JOB_AMOUNT.getId(), id)),
                Integer.valueOf(readAttributeValue(AttributeID.COMMUNICATION.getId(), id)), project.next()).build();
    }

    private ProjectReview readProjectReviewById(BigInteger id) {
        Iterator<BigInteger> author = readObjectReferences(AttributeID.REVIEW_AUTHOR.getId(), id).iterator();
        Iterator<BigInteger> receiver = readObjectByReference(AttributeID.REVIEWS.getId(), id).iterator();
        return new ProjectReviewImpl.Builder(author.next(), receiver.next(), readAttributeValue(AttributeID.REVIEW_TEXT.getId(), id),
                Integer.valueOf(readAttributeValue(AttributeID.XP_QUALITY.getId(), id)),
                Integer.valueOf(readAttributeValue(AttributeID.TEAM_SPIRIT.getId(), id)),
                Integer.valueOf(readAttributeValue(AttributeID.ORGANIZATION.getId(), id)),
                Integer.valueOf(readAttributeValue(AttributeID.TIME_MANAGMENT.getId(), id))).build();
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        List<Review> reviews = new ArrayList<>();
        Collection<BigInteger> reviewIds = readObjectByReference(AttributeID.REVIEWS.getId(), id);
        for (BigInteger reviewId : reviewIds) {
            reviews.add(readReviewById(reviewId));
        }
        return reviews;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        List<Review> result = new ArrayList<>();
        for (BigInteger reviewId : readObjectReferences(AttributeID.REVIEW_AUTHOR.getId(), id)) {
            result.add(readReviewById(reviewId));
        }
        return result;
    }


    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        List<UserReview> result = new ArrayList<>();
        for (BigInteger reviewId : readObjectReferences(AttributeID.PROJECT.getId(), id)) {
            result.add(readUserReviewById(reviewId));
        }
        return result;
    }

    private ObjectType getObjectTypeById(BigInteger id) {
        return ObjectType.valueOf(jdbcTemplate.queryForObject(SELECT_OBJECT_TYPE_BY_OBJECT_ID, new Object[]{id}, String.class));
    }
}
