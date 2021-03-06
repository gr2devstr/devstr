package com.devstr.dao;

import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueType;
import org.kohsuke.github.GHCommit;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IssueDAO {

    /**
     * Method writes Issue to database
     * @param issue issue object
     */
    void createIssue(Issue issue);

    void createIssues(ArrayList<Issue> issues);

    String getLastIssueKey();

    /**
     * Get project's issues
     * @param projectId project id
     * @return list of issues
     */
    List<Issue> readIssuesByProject(BigInteger projectId);

    /**
     * Get user's issues
     * @param userId user id
     * @return list of issues
     */
    List<Issue> readIssuesByUser(BigInteger userId);

    /**
     * Get issue by id
     * @param id issue id
     * @return issue by id
     */
    Issue readIssueById(BigInteger id);

    BigInteger readIdIssueByKey(String key);

    Map<String, BigInteger> readAllIssuesKey();

    /**
     *  Get commits by issue id
     * @param issueId issue id
     * @return  list of commits
     */
    List<Commit> readCommitsByIssue(BigInteger issueId);

    /**
     * Update issue type
     * @param id issue id
     * @param type new issue type
     */
    void updateIssueType(BigInteger id, IssueType type);

    /**
     * Update issue status
     *
     * @param issues issues list
     */
    void updateIssuesStatus(List<Issue> issues);

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
     * Write commits to db
     * @param commits
     */
    void createCommits(List<Commit> commits);

    /**
     * Write commitClass to db
     * @param commitClasses
     */
    void createCommitClasses(List<GHCommit.File> commitClasses, BigInteger commitId);

    BigInteger readCommitIdBySha(String sha);


    /**
     * The method returns the sha of the last commit from the database
     *
     * @return sha
     */
    Date getDateLastCommitOnProject();

    String GET_COMMIT_ID_BY_SHA = "SELECT c.COMMIT_ID FROM COMMITS C where c.SHA=?";
    String GET_LAST_ISSUE_KEY = "select i.ISSUE_KEY from ISSUES i where ROWNUM = 1 ORDER BY i.ISSUE_ID desc";

    String GET_COUNT = "SELECT COUNT(*) FROM COMMITS WHERE ROWNUM = 1";
    String GET_ISSUES_COUNT = "SELECT COUNT(*) FROM ISSUES WHERE ROWNUM = 1";

    String GET_COMMIT_DATE = "SELECT c.PUBLICATION_DATE FROM COMMITS c where c.COMMIT_ID = (select max(c.COMMIT_ID) from COMMITS c)";

    String CREATE_ISSUE = "INSERT " +
            "INTO ISSUES(ISSUE_KEY,PROJECT_ID,TYPE_ID,STATUS_ID,PRIORITY_ID,START_DATE,DUE_DATE,USER_ID,REPORTER_ID,IS_OVERDATED)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)";

    String CREATE_COMMIT = "INSERT INTO COMMITS(AUTHOR_ID,SHA,PUBLICATION_DATE,STATUS_OF_BUILD,ISSUE_ID)" +
            "VALUES(?,?,?,?,?)";

    String CREATE_COMMITCLASS = "INSERT INTO COMMITCLASSES(NAME,NUMBER_OF_LINES_ADDED,NUMBER_OF_LINES_CHANGED," +
            "NUMBER_OF_LINES_DELETED,COMMIT_ID)VALUES(?,?,?,?,?)";

    String READ_ALL_ISSUES_KEY = "SELECT i.ISSUE_KEY, i.ISSUE_ID FROM ISSUES i";

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

    String READ_ISSUE_ID_BY_KEY = "SELECT i.ISSUE_ID FROM ISSUES i where i.ISSUE_KEY = ?";


    String GET_COMMITS_BY_ISSUE_ID = "SELECT * FROM COMMITS WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_TYPE = "UPDATE ISSUES SET TYPE_ID = ? WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_PRIORITY = "UPDATE ISSUES SET PRIORITY_ID = ? WHERE ISSUE_ID = ?";
    String UPDATE_ISSUE_STATUS = "UPDATE ISSUES SET STATUS_ID = ? WHERE ISSUE_KEY = ?";
    String UPDATE_ISSUE_USER = "UPDATE ISSUES SET USER_ID = ? WHERE ISSUE_ID = ?";
    String DELETE_ISSUE = "DELETE FROM ISSUES WHERE ISSUE_ID = ?";


}
