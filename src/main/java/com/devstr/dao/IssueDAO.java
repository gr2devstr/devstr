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

    /**
     * The method returns the sha of the last commit from the database
     *
     * @return sha
     */
    String getShaLastCommitOnProject();

    String GET_COMMIT_SHA = "SELECT c.SHA FROM COMMITS c where c.COMMIT_ID = (select max(c.COMMIT_ID) from COMMITS c)";

    String CREATE_ISSUE = "INSERT " +
            "INTO ISSUES(ISSUE_KEY,PROJECT_ID,TYPE_ID,STATUS_ID,PRIORITY_ID,START_DATE,DUE_DATE,USER_ID,REPORTER_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?);";

    String READ_ISSUES_BY_PROJECT = "SELECT * FROM ISSUES WHERE PROJECT_ID = ?;";
    String READ_ISSUES_BY_USER = "SELECT * FROM ISSUES WHERE USER_ID = ?;";
    String READ_ISSUE_BY_ID = "SELECT * FROM ISSUES WHERE ISSUE_ID = ?;";
}
