package com.devstr.model.impl;

import com.devstr.model.Review;
import java.math.BigInteger;

public class UserReviewImpl extends ReviewImpl {

    private int codeQuality;
    private int codeAmount;
    private int communication;
    private BigInteger projectId;

    public int getCodeQuality() {
        return codeQuality;
    }

    public int getCodeAmount() {
        return codeAmount;
    }

    public int getCommunication() {
        return communication;
    }

    public BigInteger getProjectId() {
        return projectId;
    }

    public static class Builder extends com.devstr.model.impl.ReviewImpl.Builder{

        private int codeQuality;
        private int codeAmount;
        private int communication;
        private BigInteger projectId;

        public Builder(BigInteger authorId, String comment,
                       int codeQuality, int codeAmount, int communication, BigInteger projectId){
            super(authorId, comment);
            this.codeQuality = codeQuality;
            this.codeAmount = codeAmount;
            this.communication = communication;
            this.projectId = projectId;
        }

        public Review build() {
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
