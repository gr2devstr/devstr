package com.devstr.dao;

import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;

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
     * Update issue type
     * @param id issue id
     * @param type new issue type
     */
    void updateIssueType(BigInteger id, IssueType type);

    /**
     * Update issue status
     * @param id issue id
     * @param status new issue status
     */
    void updateIssueStatus(BigInteger id, IssueStatus status);

    /**
     * Update issue priority
     * @param id issue id
     * @param priority new issue priority
     */
    void updateIssuePriority(BigInteger id, IssuePriority priority);

    /**
     * Update issue user
     * @param id issue id
     * @param userId user id
     */
    void updateIssueUser(BigInteger id, BigInteger userId);

    /**
     *
     * @param id issue id
     */
    void deleteIssueById(BigInteger id);

    /**
     * The method returns the sha of the last commit from the database
     *
     * @return sha
     */
    String getShaLastCommitOnProject();

    String GET_COMMIT_SHA = "SELECT c.SHA FROM COMMITS c where c.COMMIT_ID = (select max(c.COMMIT_ID) from COMMITS c)";

    String CREATE_ISSUE = "INSERT " +
            "INTO ISSUES(ISSUE_KEY,PROJECT_ID,TYPE_ID,STATUS_ID,PRIORITY_ID,START_DATE,DUE_DATE,USER_ID,REPORTER_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";

    String READ_ISSUES_BY_PROJECT = "SELECT issue.ISSUE_ID, issue.ISSUE_KEY, issue.PROJECT_ID, " +
            "type.VALUE, status.VALUE, priority.VALUE, issue.START_DATE, issue.DUE_DATE, " +
            "issue.USER_ID, issue.REPORTER_ID " +
            "FROM ISSUES issue, JIRATYPES type, JIRASTATUSES status, JIRAPRIORITIES priority " +
            "WHERE PROJECT_ID = ? " +
            "AND type.JIRATYPE_ID = issue.TYPE_ID " +
            "AND status.JIRASTATUS_ID = issue.STATUS_ID " +
            "AND priority.JIRAPRIORITY_ID = issue.PRIORITY_ID";

    String READ_ISSUES_BY_USER = "SELECT issue.ISSUE_ID, issue.ISSUE_KEY, issue.PROJECT_ID, " +
            "type.VALUE, status.VALUE, priority.VALUE, issue.START_DATE, issue.DUE_DATE, " +
            "issue.USER_ID, issue.REPORTER_ID " +
            "FROM ISSUES issue, JIRATYPES type, JIRASTATUSES status, JIRAPRIORITIES priority " +
            "WHERE USER_ID = ? " +
            "AND type.JIRATYPE_ID = issue.TYPE_ID " +
            "AND status.JIRASTATUS_ID = issue.STATUS_ID " +
            "AND priority.JIRAPRIORITY_ID = issue.PRIORITY_ID";

    String READ_ISSUE_BY_ID = "SELECT issue.ISSUE_ID, issue.ISSUE_KEY, issue.PROJECT_ID, " +
            "type.VALUE, status.VALUE, priority.VALUE, issue.START_DATE, issue.DUE_DATE, " +
            "issue.USER_ID, issue.REPORTER_ID " +
            "FROM ISSUES issue, JIRATYPES type, JIRASTATUSES status, JIRAPRIORITIES priority " +
            "WHERE ISSUE_ID = ? " +
            "AND type.JIRATYPE_ID = issue.TYPE_ID " +
            "AND status.JIRASTATUS_ID = issue.STATUS_ID " +
            "AND priority.JIRAPRIORITY_ID = issue.PRIORITY_ID";

    String GET_COMMITS_BY_ISSUE_ID = "SELECT * FROM COMMITS WHERE ISSUE_ID = ?";

    String UPDATE_ISSUE_TYPE = "UPDATE ISSUES SET TYPE_ID = ? WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_PRIORITY = "UPDATE ISSUES SET PRIORITY_ID = ? WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_STATUS = "UPDATE ISSUES SET STATUS_ID = ? WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_USER = "UPDATE ISSUES SET USER_ID = ? WHERE ISSUE_ID = ?";

    String DELETE_ISSUE = "DELETE FROM ISSUES WHERE ISSUE_ID = ?";

}
