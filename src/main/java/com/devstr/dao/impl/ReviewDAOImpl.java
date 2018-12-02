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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class ReviewDAOImpl extends AbstractDAOImpl implements ReviewDAO {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(ReviewDAOImpl.class.getName());

    @Override
    @Transactional
    public void createUserReview(BigInteger authorId, BigInteger receiverId, BigInteger projectId, String comment, int[] marks) {
        BigInteger id = createObject(BigInteger.valueOf(32L), "UR" + authorId + receiverId);
        createAttributeDateValue(BigInteger.valueOf(5L), id, new Date(1));
        createAttributeValue(BigInteger.valueOf(14L), id, comment);
        createAttributeValue(BigInteger.valueOf(15L), id, String.valueOf(marks[0]));
        createAttributeValue(BigInteger.valueOf(16L), id, String.valueOf(marks[1]));
        createAttributeValue(BigInteger.valueOf(17L), id, String.valueOf(marks[2]));
        createObjectReference(BigInteger.valueOf(25L), receiverId, id);
        createObjectReference(BigInteger.valueOf(27L), id, authorId);
        createObjectReference(BigInteger.valueOf(28L), id, projectId);
    }

    @Override
    @Transactional
    public void createProjectReview(BigInteger authorId, BigInteger receiverId, String comment, int[] marks) {
        BigInteger id = createObject(BigInteger.valueOf(33L), "P" + authorId + receiverId);
        createAttributeDateValue(BigInteger.valueOf(5L), id, new Date(2));
        createAttributeValue(BigInteger.valueOf(14L), id, comment);
        createAttributeValue(BigInteger.valueOf(18L), id, String.valueOf(marks[0]));
        createAttributeValue(BigInteger.valueOf(19L), id, String.valueOf(marks[1]));
        createAttributeValue(BigInteger.valueOf(20L), id, String.valueOf(marks[2]));
        createAttributeValue(BigInteger.valueOf(21L), id, String.valueOf(marks[3]));
        createObjectReference(BigInteger.valueOf(25L), receiverId, id);
        createObjectReference(BigInteger.valueOf(27L), id, authorId);
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
        Iterator<BigInteger> author = readObjectReferences(BigInteger.valueOf(27L), id).iterator();
        Iterator<BigInteger> receiver = readObjectByReference(BigInteger.valueOf(25L), id).iterator();
        Iterator<BigInteger> project = readObjectReferences(BigInteger.valueOf(28L), id).iterator();
        return new UserReviewImpl.Builder(author.next(), receiver.next(), readAttributeValue(BigInteger.valueOf(14L), id),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(15L), id)),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(16L), id)),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(17L), id)), project.next()).build();
    }

    private ProjectReview readProjectReviewById(BigInteger id) {
        Iterator<BigInteger> author = readObjectReferences(BigInteger.valueOf(27L), id).iterator();
        Iterator<BigInteger> receiver = readObjectByReference(BigInteger.valueOf(25L), id).iterator();
        return new ProjectReviewImpl.Builder(author.next(), receiver.next(), readAttributeValue(BigInteger.valueOf(14L), id),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(18L), id)),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(19L), id)),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(20L), id)),
                Integer.valueOf(readAttributeValue(BigInteger.valueOf(21L), id))).build();
    }

    @Override
    public List<Review> readReviewsByRecId(BigInteger id) {
        List<Review> reviews = new ArrayList<>();
        Collection<BigInteger> reviewIds = readObjectByReference(BigInteger.valueOf(25L), id);
        for (BigInteger reviewId : reviewIds) {
            reviews.add(readReviewById(reviewId));
        }
        return reviews;
    }

    @Override
    public List<Review> readReviewsByAuthorId(BigInteger id) {
        List<Review> result = new ArrayList<>();
        for (BigInteger reviewId : readObjectReferences(BigInteger.valueOf(27L), id)) {
            result.add(readReviewById(reviewId));
        }
        return result;
    }


    @Override
    public List<UserReview> readUserReviewsByProjectId(BigInteger id) {
        List<UserReview> result = new ArrayList<>();
        for (BigInteger reviewId : readObjectReferences(BigInteger.valueOf(28L), id)) {
            result.add(readUserReviewById(reviewId));
        }
        return result;
    }

    private ObjectType getObjectTypeById(BigInteger id) {
        return ObjectType.valueOf(jdbcTemplate.queryForObject(SELECT_OBJECT_TYPE_BY_OBJECT_ID, new Object[]{id}, String.class));
    }
}
