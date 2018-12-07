package com.devstr.services.impl;

import com.devstr.services.*;

public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public StatisticService getStatisticService() {
        return new StatisticServiceImpl();
    }
    @Override
    public GitService getGitService() {
        return new GitServiceImpl();
    }

    @Override
    public ReviewService getReviewService() {
        return new ReviewServiceImpl();
    }

    @Override
    public TokenService getTokenService() {
        return new TokenServiceImp();
    }

    @Override
    public JiraService getJiraService() {
        return new JiraServiceImpl();
    }
}
