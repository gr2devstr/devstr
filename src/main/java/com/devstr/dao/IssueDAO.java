package com.devstr.dao;

import com.devstr.model.Issue;

import java.math.BigInteger;
import java.util.List;

public interface IssueDAO {

    /**
     * Method writes Issue to database
     * @param issue issue object
     */
    void createIssue(Issue issue);

    /**
     * Get project's issues
     * @param projectId project's id
     * @return list of issues
     */
    List<Issue> readIssuesByProject(BigInteger projectId);

    /**
     * Get user's issues
     * @param userId user's id
     * @return list of issues
     */
    List<Issue> readIssuesByUser(BigInteger userId);

    /**
     * Get issue by id
     * @param id issue's id
     * @return issue by id
     */
    Issue readIssueById(BigInteger id);

    /**
     * Update issue
     * @param issue edited issue
     */
    void updateIssue(Issue issue);


}
