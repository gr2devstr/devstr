package com.devstr.model.impl;

import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private boolean isOverdated;

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
    public boolean isOverdated() {
        return isOverdated;
    }

    @Override
    public void setOverdate(boolean overdated) {
        this.isOverdated = overdated;
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

    public static class IssueBuilder {

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
        private boolean isOverdated;

        public IssueBuilder() {}

        public IssueBuilder setIssueId(BigInteger id) {
            this.issueId = id;
            return this;
        }

        public IssueBuilder setIssueKey(String key) {
            this.issueKey = key;
            return this;
        }

        public IssueBuilder setIssueType(IssueType type) {
            this.type = type;
            return this;
        }

        public IssueBuilder setIssueStatus(IssueStatus status) {
            this.status = status;
            return this;
        }

        public IssueBuilder setIssuePriority(IssuePriority priority) {
            this.priority = priority;
            return this;
        }

        public IssueBuilder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public IssueBuilder setDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public IssueBuilder setProjectId(BigInteger id) {
            this.projectId = id;
            return this;
        }

        public IssueBuilder setUserId(BigInteger id) {
            this.userId = id;
            return this;
        }

        public IssueBuilder setReporterId(BigInteger reporterId) {
            this.reporterId = reporterId;
            return this;
        }

        public IssueBuilder setCommit(Commit commit) {
            if (this.commits == null) {
                this.commits = new ArrayList<>();
            }
            this.commits.add(commit);
            return this;
        }


        public IssueBuilder setCommits(Set<Commit> commits) {
            if (this.commits == null) {
                this.commits = new ArrayList<>();
            }
            this.commits.addAll(commits);
            return this;
        }

        public IssueBuilder setOverdate(boolean overdated) {
            this.isOverdated = overdated;
            return this;
        }

        public Issue build() {
            return new IssueImpl(this);
        }

    }

    private IssueImpl(IssueBuilder builder){
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
        this.isOverdated = builder.isOverdated;
    }
}
