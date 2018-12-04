package com.devstr.services;

public interface ServiceFactory {
    GitService getGitService(String repositoryName, String token);

    ReviewService getReviewService();

    TokenService getTokenService();
}
