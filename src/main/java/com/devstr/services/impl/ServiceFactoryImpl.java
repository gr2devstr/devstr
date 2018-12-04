package com.devstr.services.impl;

import com.devstr.services.GitService;
import com.devstr.services.ReviewService;
import com.devstr.services.ServiceFactory;
import com.devstr.services.TokenService;

public class ServiceFactoryImpl implements ServiceFactory {
    @Override
    public GitService getGitService(String repositoryName, String token) {
        return new GitServiceImpl(repositoryName, token);
    }

    @Override
    public ReviewService getReviewService() {
        return new ReviewServiceImpl();
    }

    @Override
    public TokenService getTokenService() {
        return new TokenServiceImp();
    }
}
