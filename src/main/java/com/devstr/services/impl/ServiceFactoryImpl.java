package com.devstr.services.impl;

import com.devstr.services.*;

public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public StatisticService getStatisticService() {
        return new StatisticServiceImpl();
    }
    @Override
    public GitService getGitService(String repositoryName, String token) {
        GitService service = new GitServiceImpl();
        service.setRepositoryName(repositoryName);
        service.setToken(token);
        return service;
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
    public JiraService getJiraService(String login, String password, String domain) {
        JiraService service = new JiraServiceImpl();
        service.setLogin(login);
        service.setPassword(password);
        service.setDomain(domain);
        return service;
    }
}
