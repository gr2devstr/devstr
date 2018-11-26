package com.devstr.dao;


import com.devstr.model.Project;

import java.util.Set;

public interface ProjectDAO {


    /**
     * The method that creates the new project
     *
     * @param name      project name
     * @param managerId project manager id
     * @param techId    tech manager id
     * @param devs      state of the devs on the project
     * @param repoName  GitHub repository name
     * @param gitLogin  login for GitHub
     * @param gitPass   password for GitHub
     * @param jiraLogin login for Jira
     * @param jiraPass  password for Jira
     */
    void createProject(String name, int managerId, int techId, Set<Integer> devs, String repoName, String gitLogin, String gitPass, String jiraLogin, String jiraPass);

    /**
     * Method to get the project by ID
     *
     * @param projectID  project id for find
     * @return found project
     */
    Project readProjectById(int projectID);

    /**
     *
     * @param projectName project name for find
     * @return found project
     */
    Project readProjectByName(String projectName);

    /**
     * Method for update project fields
     *
     * @param project project for update
     */
    void updateProject(Project project);

}
