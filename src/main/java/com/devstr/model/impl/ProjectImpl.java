package com.devstr.model.impl;

import com.devstr.model.Project;
import com.devstr.model.enumerations.Status;
import java.math.BigInteger;
import java.util.*;

public class ProjectImpl implements Project {

    private BigInteger projectId;
    private String projectName;
    private BigInteger projectManagerId;
    private BigInteger technicalManagerId;
    private Collection<BigInteger> developersId;
    private Collection<BigInteger> issuesId;
    private String repoName;
    private String gitLogin;
    private String gitPassword;
    private String jiraLogin;
    private String jiraPassword;
    private Date fromDate;
    private Date toDate;
    private Status status;
    private List<BigInteger> reviewsId;

    @Override
    public BigInteger getProjectId() {
        return null;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public BigInteger getProjectManagerId() {
        return projectManagerId;
    }

    @Override
    public BigInteger getTechnicalManagerId() {
        return technicalManagerId;
    }

    @Override
    public Collection<BigInteger> getDevelopersId() {
        return developersId;
    }

    @Override
    public Collection<BigInteger> getIssuesId() {
        return issuesId;
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public String getGitLogin() {
        return gitLogin;
    }

    @Override
    public String getGitPassword() {
        return gitPassword;
    }

    @Override
    public String getJiraLogin() {
        return jiraLogin;
    }

    @Override
    public String getJiraPassword() {
        return jiraPassword;
    }

    @Override
    public Date getFromDate() {
        return fromDate;
    }

    @Override
    public Date getToDate() {
        return toDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public List<BigInteger> getReviewsId() {
        return reviewsId;
    }

    @Override
    public void setReviewsId(List<BigInteger> reviewsId) {
        if (this.reviewsId == null) {
            this.reviewsId = new ArrayList<>();
        }
        this.reviewsId.addAll(reviewsId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectImpl project = (ProjectImpl) o;

        return getProjectId().equals(project.getProjectId());
    }

    @Override
    public int hashCode() {
        return getProjectId().hashCode();
    }

    public static class Builder {

        private BigInteger projectId;
        private String projectName;
        private BigInteger projectManagerId;
        private BigInteger technicalManagerId;
        private Collection<BigInteger> developersId;
        private Collection<BigInteger> issuesId;
        private String repoName;
        private String gitLogin;
        private String gitPassword;
        private String jiraLogin;
        private String jiraPassword;
        private Date fromDate;
        private Date toDate;
        private Status status;
        private List<BigInteger> reviewsId;

        public Builder(String projectName, BigInteger projectManagerId) {
            this.projectName = projectName;
            this.projectManagerId = projectManagerId;
        }

        public Builder setProjectId(BigInteger projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder setTechnicalManagerId(BigInteger technicalManagerId) {
            this.technicalManagerId = technicalManagerId;
            return this;
        }

        public Builder setDevelopersId(Collection<BigInteger> developersId) {
            this.developersId = developersId;
            return this;
        }

        public Builder setIssuesId(Collection<BigInteger> issuesId) {
            this.issuesId = issuesId;
            return this;
        }

        public Builder setRepoName(String repoName) {
            this.repoName = repoName;
            return this;
        }

        public Builder setGitLogin(String gitLogin) {
            this.gitLogin = gitLogin;
            return this;
        }

        public Builder setGitPassword(String gitPassword) {
            this.gitPassword = gitPassword;
            return this;
        }

        public Builder setJiraLogin(String jiraLogin) {
            this.jiraLogin = jiraLogin;
            return this;
        }

        public Builder setJiraPassword(String jiraPassword) {
            this.jiraPassword = jiraPassword;
            return this;
        }

        public Builder setFromDate(Date fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder setToDate(Date toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setReviewsId(List<BigInteger> reviewsId) {
            this.reviewsId = reviewsId;
            return this;
        }

        public Project build() {
            return new ProjectImpl(this);
        }

    }

    private ProjectImpl(Builder builder) {
        this.projectId = builder.projectId;
        this.projectName = builder.projectName;
        this.projectManagerId = builder.projectManagerId;
        this.technicalManagerId = builder.technicalManagerId;
        this.developersId = builder.developersId;
        this.issuesId = builder.issuesId;
        this.repoName = builder.repoName;
        this.gitLogin = builder.gitLogin;
        this.gitPassword = builder.gitPassword;
        this.jiraLogin = builder.jiraLogin;
        this.jiraPassword = builder.jiraPassword;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.status = builder.status;
        this.reviewsId = builder.reviewsId;
    }

}
