package com.devstr.services;

public interface ServiceFactory {
    StatisticService getStatisticService();

    GitService getGitService();

    ReviewService getReviewService();

    TokenService getTokenService();

    JiraService getJiraService();
}
