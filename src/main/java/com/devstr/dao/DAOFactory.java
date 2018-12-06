package com.devstr.dao;

public interface DAOFactory {
    ReviewDAO getReviewDAO();

    IssueDAO getIssueDAO();

    ProjectDAO getProjectDAO();

    TokenDAO getTokenDAO();

    UserDAO getUserDAO();

}
