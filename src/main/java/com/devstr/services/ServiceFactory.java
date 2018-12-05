package com.devstr.services;

public interface ServiceFactory {
    StatisticService getStatisticService();

    GitService getGitService(String repositoryName, String token);

    ReviewService getReviewService();

    TokenService getTokenService();
}
