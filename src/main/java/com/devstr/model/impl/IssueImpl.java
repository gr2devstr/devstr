package com.devstr.model.impl;

import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import java.math.BigInteger;
import java.util.*;

public class IssueImpl implements Issue {

    private BigInteger issueId;
    private String issueKey;
    private IssueType type;
    private IssueStatus status;
    private IssuePriority priority;
    private Date startDate;
    private Date dueDate;
    private BigInteger projectId;
    private BigInteger userId;
    private BigInteger reporterId;
    private List<Commit> commits;

    @Override
    public BigInteger getIssueId() {
        return issueId;
    }

    @Override
    public String getIssueKey() {
        return issueKey;
    }

    @Override
    public IssueType getType() {
        return type;
    }

    @Override
    public IssueStatus getStatus() {
        return status;
    }

    @Override
    public IssuePriority getPriority() {
        return priority;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public BigInteger getProjectId() {
        return projectId;
    }

    @Override
    public BigInteger getUserId() {
        return userId;
    }

    @Override
    public BigInteger getReporterId() {
        return reporterId;
    }

    @Override
    public List<Commit> getCommits() {
        return commits;
    }

    @Override
    public void setCommit(Commit commit) {
        if (this.commits == null) {
            this.commits = new ArrayList<>();
        }
        this.commits.add(commit);
    }

    @Override
    public void setCommits(List<Commit> commits) {
        if (this.commits == null) {
            this.commits = new ArrayList<>();
        }
        this.commits.addAll(commits);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueImpl issue = (IssueImpl) o;

        return getIssueId().equals(issue.getIssueId());
    }

    @Override
    public int hashCode() {
        return getIssueId().hashCode();
    }

    public static class Builder {

        private BigInteger issueId;
        private String issueKey;
        private IssueType type;
        private IssueStatus status;
        private IssuePriority priority;
        private Date startDate;
        private Date dueDate;
        private BigInteger projectId;
        private BigInteger userId;
        private BigInteger reporterId;
        private List<Commit> commits;

        public Builder() {}

        public Builder setIssueId(BigInteger id) {
            this.issueId = id;
            return this;
        }

        public Builder setIssueKey(String key) {
            this.issueKey = key;
            return this;
        }

        public Builder setIssueType(IssueType type) {
            this.type = type;
            return this;
        }

        public Builder setIssueStatus(IssueStatus status) {
            this.status = status;
            return this;
        }

        public Builder setIssuePriority(IssuePriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setProjectId(BigInteger id) {
            this.projectId = id;
            return this;
        }

        public Builder setUserId(BigInteger id) {
            this.userId = id;
            return this;
        }

        public Builder setReporterId(BigInteger reporterId) {
            this.reporterId = reporterId;
            return this;
        }

        public Builder setCommit(Commit commit) {
            if (this.commits == null) {
                this.commits = new ArrayList<>();
            }
            this.commits.add(commit);
            return this;
        }

        public Builder setCommits(Set<Commit> commits) {
            if (this.commits == null) {
                this.commits = new ArrayList<>();
            }
            this.commits.addAll(commits);
            return this;
        }

        public Issue build() {
            return new IssueImpl(this);
        }

    }

    private IssueImpl(Builder builder){
        this.issueId = builder.issueId;
        this.issueKey = builder.issueKey;
        this.type = builder.type;
        this.status = builder.status;
        this.priority = builder.priority;
        this.startDate = builder.startDate;
        this.dueDate = builder.dueDate;
        this.projectId = builder.projectId;
        this.userId = builder.userId;
        this.reporterId = builder.reporterId;
        this.commits = builder.commits;
    }
}
