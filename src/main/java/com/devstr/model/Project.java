package com.devstr.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Project {

    private int projectId;
    private String projectName;
    private int projectManagerId;
    private int technicalManagerId;
    private Set<Integer> developersId;
    private Set<Review> reviews;
    private Set<Integer> issuesId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean status;
    private String repoName;
    private String gitLogin;
    private String gitPassword;
    private String jiraLogin;
    private String jiraPassword;

    private Project(){
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getProjectManagerId() {
        return projectManagerId;
    }

    public int getTechnicalManagerId() {
        return technicalManagerId;
    }

    public Set<Integer> getDevelopersId() {
        return developersId;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Integer> getIssuesId() {
        return issuesId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getGitLogin() {
        return gitLogin;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public String getJiraLogin() {
        return jiraLogin;
    }

    public String getJiraPassword() {
        return jiraPassword;
    }

    public static ProjectBuilder builder() {
        return new Project().new ProjectBuilder();
    }

    public class ProjectBuilder {

        private ProjectBuilder() {
        }

        public ProjectBuilder setProjectId(int id){
            Project.this.projectId = id;
            return this;
        }

        public ProjectBuilder setProjectName(String name){
            Project.this.projectName = name;
            return this;
        }

        public ProjectBuilder setProjectManagerId(int id){
            Project.this.projectManagerId = id;
            return this;
        }

        public ProjectBuilder setTechnicalManagerId(int id){
            Project.this.technicalManagerId = id;
            return this;
        }

        public ProjectBuilder setDeveloperId(int id){
            if (developersId == null) {
                developersId = new HashSet<>();
            }

            Project.this.projectManagerId = id;
            return this;
        }

    }

}
