package com.devstr.model.impl;

import com.devstr.model.ProjectReview;
import com.devstr.model.Review;

import java.math.BigInteger;

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
    public float getAverageMark() {
        return ((float) getExperienceQuality() + getOrganisationLevel() + getTeamSpirit() + getTimeManagement()) / 4;
    }

    public static class Builder extends com.devstr.model.impl.ReviewImpl.Builder {

        private int experienceQuality;
        private int organisationLevel;
        private int timeManagement;
        private int teamSpirit;

        public Builder(BigInteger authorId, BigInteger receiverId, String comment,
                       int experienceQuality, int organisationLevel, int timeManagement, int teamSpirit) {
            super(authorId, receiverId, comment);
            this.experienceQuality = experienceQuality;
            this.organisationLevel = organisationLevel;
            this.timeManagement = timeManagement;
            this.teamSpirit = teamSpirit;
        }

        public Review build() {
            return new ProjectReviewImpl(this);
        }

    }

    private ProjectReviewImpl(Builder builder) {
        super(builder);
        this.experienceQuality = builder.experienceQuality;
        this.organisationLevel = builder.organisationLevel;
        this.timeManagement = builder.timeManagement;
        this.teamSpirit = builder.teamSpirit;
    }

}
