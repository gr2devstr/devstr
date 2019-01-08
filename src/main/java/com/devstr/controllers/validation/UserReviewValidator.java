package com.devstr.controllers.validation;

import com.devstr.dao.UtilDAO;
import com.devstr.model.UserReview;
import com.devstr.model.enumerations.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigInteger;

@Component
public class UserReviewValidator {

    @Autowired
    private UtilDAO utilDAO;

    public boolean isReviewValid(UserReview review) {
        if (review == null) {
            return false;
        }
        if (!utilDAO.checkObjectType(ObjectType.USER.getId(), review.getAuthorId())) {
            return false;
        }
        if (!utilDAO.checkObjectType(ObjectType.USER.getId(), review.getReceiverId())) {
            return false;
        }
        if (!utilDAO.checkObjectType(ObjectType.PROJECT.getId(), review.getProjectId())) {
            return false;
        }
        String comment = review.getComment();
        if (comment == null || comment.trim().equals("")) {
            return false;
        }
        int[] marks = review.getAllMarksAsArray();
        return (marks != null && marks.length == 3);
    }

    public boolean isReviewIdValid(BigInteger id) {
        return utilDAO.checkObjectType(ObjectType.USER_REVIEW.getId(), id);
    }

    public boolean isAcceptableToView(BigInteger recieverId, BigInteger userId) {
        return  recieverId.equals(userId);
    }

}
