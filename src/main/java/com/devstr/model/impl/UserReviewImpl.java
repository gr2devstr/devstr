package com.devstr.model.impl;

import com.devstr.model.UserReview;

import java.beans.Transient;
import java.math.BigInteger;
import java.util.Date;

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
    @Transient
    public int[] getAllMarksAsArray() {
        int[] result = new int[3];
        result[0] = codeQuality;
        result[1] = codeAmount;
        result[2] = communication;
        return result;
    }

    public static class Builder {

        private BigInteger reviewId;
        private BigInteger authorId;
        private BigInteger receiverId;
        private String authorFullName;
        private String comment;
        private Date creationDate;
        private int codeQuality;
        private int codeAmount;
        private int communication;
        private BigInteger projectId;

        public Builder(BigInteger authorId, BigInteger receiverId, String comment,
                       int codeQuality, int codeAmount, int communication, BigInteger projectId) {
            this.authorId = authorId;
            this.receiverId = receiverId;
            this.comment = comment;
            this.codeQuality = codeQuality;
            this.codeAmount = codeAmount;
            this.communication = communication;
            this.projectId = projectId;
        }

        public Builder setReviewId(BigInteger reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setAuthorFullName(String authorFullName) {
            this.authorFullName = authorFullName;
            return this;
        }

        public Builder setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public UserReview build() {
            return new UserReviewImpl(this);
        }

    }

    private UserReviewImpl(Builder builder) {
        this.reviewId = builder.reviewId;
        this.authorId = builder.authorId;
        this.receiverId = builder.receiverId;
        this.authorFullName = builder.authorFullName;
        this.comment = builder.comment;
        this.creationDate = builder.creationDate;
        this.codeQuality = builder.codeQuality;
        this.codeAmount = builder.codeAmount;
        this.communication = builder.communication;
        this.projectId = builder.projectId;
    }

}
