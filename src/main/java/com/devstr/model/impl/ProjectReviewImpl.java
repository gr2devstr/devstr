package com.devstr.model.impl;

import com.devstr.model.Review;
import java.math.BigInteger;

public class ProjectReviewImpl extends ReviewImpl {

    private int experienceQuality;
    private int organisationLevel;
    private int timeManagement;
    private int teamSpirit;

    public int getExperienceQuality() {
        return experienceQuality;
    }

    public int getOrganisationLevel() {
        return organisationLevel;
    }

    public int getTimeManagement() {
        return timeManagement;
    }

    public int getTeamSpirit() {
        return teamSpirit;
    }

    public static class Builder extends com.devstr.model.impl.ReviewImpl.Builder{

        private int experienceQuality;
        private int organisationLevel;
        private int timeManagement;
        private int teamSpirit;

        public Builder(BigInteger authorId, String comment,
                       int experienceQuality, int organisationLevel, int timeManagement, int teamSpirit){
            super(authorId, comment);
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
