package com.devstr.dao;


import com.devstr.model.Project;

import java.util.Set;

public interface ProjectDAO {


    void createProject(String name, int managerId, int techId, Set<Integer> devs, String repoName, String gitLogin, String gitPass, String jiraLogin, String jiraPass);

    Project readProjectById(int projectID);

    Project readProjectByName(String projectName);

    void updateProject(Project project);

}
