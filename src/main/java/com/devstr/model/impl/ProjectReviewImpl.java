package com.devstr.model.impl;

import com.devstr.model.ProjectReview;
import com.devstr.model.Review;

import java.beans.Transient;
import java.math.BigInteger;
import java.util.Date;

public class ProjectReviewImpl extends ReviewImpl implements ProjectReview {

    private int experienceQuality;
    private int organisationLevel;
    private int timeManagement;
    private int teamSpirit;

    @Override
    public int getExperienceQuality() {
        return experienceQuality;
    }

    @Override
    public int getOrganisationLevel() {
        return organisationLevel;
    }

    @Override
    public int getTimeManagement() {
        return timeManagement;
    }

    @Override
    public int getTeamSpirit() {
        return teamSpirit;
    }

    @Override
    @Transient
    public int[] getAllMarksAsArray() {
        int[] result = new int[4];
        result[0] = experienceQuality;
        result[1] = teamSpirit;
        result[2] = organisationLevel;
        result[3] = timeManagement;
        return result;
    }

    public static class Builder {

        private BigInteger reviewId;
        private BigInteger authorId;
        private BigInteger receiverId;
        private String authorFullName;
        private String comment;
        private Date creationDate;
        private int experienceQuality;
        private int organisationLevel;
        private int timeManagement;
        private int teamSpirit;

        public Builder(BigInteger authorId, BigInteger receiverId, String comment,
                       int experienceQuality, int organisationLevel, int timeManagement, int teamSpirit) {
            this.authorId = authorId;
            this.receiverId = receiverId;
            this.comment = comment;
            this.experienceQuality = experienceQuality;
            this.organisationLevel = organisationLevel;
            this.timeManagement = timeManagement;
            this.teamSpirit = teamSpirit;
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
        public ProjectReview build() {
            return new ProjectReviewImpl(this);
        }

    }

    private ProjectReviewImpl(Builder builder) {
        this.reviewId = builder.reviewId;
        this.authorId = builder.authorId;
        this.receiverId = builder.receiverId;
        this.authorFullName = builder.authorFullName;
        this.comment = builder.comment;
        this.creationDate = builder.creationDate;
        this.experienceQuality = builder.experienceQuality;
        this.organisationLevel = builder.organisationLevel;
        this.timeManagement = builder.timeManagement;
        this.teamSpirit = builder.teamSpirit;
    }

}
