package com.devstr.model;

import com.devstr.model.Review;

public interface ProjectReview extends Review {
    int getExperienceQuality();
    int getOrganisationLevel();
    int getTimeManagement();
    int getTeamSpirit();
    float getAverageMark();
}
