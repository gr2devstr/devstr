package com.devstr.model.impl;

import com.devstr.model.Review;
import com.devstr.model.UserReview;

import java.math.BigInteger;

public class UserReviewImpl extends ReviewImpl implements UserReview {

    private int codeQuality;
    private int codeAmount;
    private int communication;
    private BigInteger projectId;

    @Override
    public int getCodeQuality() {
        return codeQuality;
    }

    @Override
    public int getCodeAmount() {
        return codeAmount;
    }

    @Override
    public int getCommunication() {
        return communication;
    }

    @Override
    public BigInteger getProjectId() {
        return projectId;
    }

    @Override
    public int[] getAllMarksAsArray() {
        int[] result = new int[3];
        result[0] = codeQuality;
        result[1] = codeAmount;
        result[2] = communication;
        return result;
    }

    public static class Builder extends com.devstr.model.impl.ReviewImpl.Builder {

        private int codeQuality;
        private int codeAmount;
        private int communication;
        private BigInteger projectId;

        public Builder(BigInteger authorId, BigInteger receiverId, String comment,
                       int codeQuality, int codeAmount, int communication, BigInteger projectId) {
            super(authorId, receiverId, comment);
            this.codeQuality = codeQuality;
            this.codeAmount = codeAmount;
            this.communication = communication;
            this.projectId = projectId;
        }

        public UserReview build() {
            return new UserReviewImpl(this);
        }

    }

    private UserReviewImpl(Builder builder) {
        super(builder);
        this.codeQuality = builder.codeQuality;
        this.codeAmount = builder.codeAmount;
        this.communication = builder.communication;
        this.projectId = builder.projectId;
    }

}
