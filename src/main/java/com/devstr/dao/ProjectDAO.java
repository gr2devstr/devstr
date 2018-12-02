package com.devstr.dao;


import com.devstr.model.Project;

import java.math.BigInteger;

public interface ProjectDAO {


    /**
     * The method that creates the new project
     *
     * @param name project name
     * @param managerId
     *
     */
    BigInteger createProject(String name, BigInteger managerId);

    /**
     *
     * @param projectId
     * @param repoName
     */
    void updateProjectRepositoryName(BigInteger projectId, String repoName);

    /**
     *
     * @param projectId
     * @param gitLogin
     */
    void updateProjectGitLogin(BigInteger projectId, String gitLogin);

    /**
     *
     * @param projectId
     * @param gitPassword
     */
    void updateProjectGitPassword(BigInteger projectId, String gitPassword);

    /**
     *
     * @param projectId
     * @param jiraLogin
     */
    void updateProjectJiraLogin(BigInteger projectId, String jiraLogin);

    /**
     *
     * @param projectId
     * @param jiraPassword
     */
    void updateProjectJiraPassword(BigInteger projectId, String jiraPassword);

    /**
     * Add developer on project
     *
     * @param developerId
     */
    void addDevOnProject(BigInteger projectId, BigInteger developerId);


    /**
     *
     * @param developerId
     */
    void deactivateUserOnProject(BigInteger developerId);

    /**
     * Add issue on project
     *
     * @param issueId
     */
    void addIssueOnProject(BigInteger projectId, BigInteger issueId);

    /**
     * Method to get the project by ID
     *
     * @param projectID project id for find
     * @return found project
     */
    Project readProjectById(BigInteger projectID);

    /**
     * @param projectName project name for find
     * @return found project
     */
    Project readProjectByName(String projectName);

    String UPDATE_PROJECT_ISSUE = "update ISSUES set PROJECT_ID = ? where ISSUE_ID = ?";
    String GET_USER_ID_BY_ROLE = "SELECT u.OBJECT_ID FROM OBJECTS u, ATTRIBUTES a, OBJREFERENCE user_to_proj " +
            "WHERE user_to_proj.OBJECT_ID = ? " +
            "AND a.OBJECT_ID = user_to_proj.REFERENCE " +
            "AND a.LIST_VALUE_ID = ? " +
            "AND u.OBJECT_ID = user_to_proj.REFERENCE";
}
