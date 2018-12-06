package com.devstr.dao.impl;

import com.devstr.dao.*;

public class DAOFactoryImpl implements DAOFactory {
    @Override
    public ReviewDAO getReviewDAO() {
        return new ReviewDAOImpl();
    }

    @Override
    public IssueDAO getIssueDAO() {
        return new IssueDAOImpl();
    }

    @Override
    public ProjectDAO getProjectDAO() {
        return new ProjectDAOImpl();
    }

    @Override
    public TokenDAO getTokenDAO() {
        return new TokenDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
