package com.devstr.model.impl;

import com.devstr.model.Commit;
import com.devstr.model.enumerations.BuildStatus;
import org.kohsuke.github.GHCommit;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class CommitImpl  implements Commit {

    private BigInteger commitId;
    private BigInteger userId;
    private String sha;
    private Date date;
    private Collection<GHCommit.File> commitClasses;
    private BuildStatus buildStatus;


    @Override
    public BigInteger getCommitId() {
        return commitId;
    }

    @Override
    public BigInteger getUserId() {
        return userId;
    }

    @Override
    public String getSha() {
        return sha;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Collection<GHCommit.File> getCommitClasses() {
        return commitClasses;
    }

    @Override
    public BuildStatus getBuildStatus() {
        return buildStatus;
    }

    @Override
    public void setCommitClasses(List<GHCommit.File> commitClasses) {
        if (this.commitClasses == null) {
            this.commitClasses = new HashSet<>();
        }
        this.commitClasses.addAll(commitClasses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitImpl commit = (CommitImpl) o;

        return getCommitId().equals(commit.getCommitId());
    }

    @Override
    public int hashCode() {
        return getCommitId().hashCode();
    }

    public static class CommitBuilder {

        private BigInteger commitId;
        private BigInteger userId;
        private String sha;
        private Date date;
        private Collection<GHCommit.File> commitClasses;
        private BuildStatus buildStatus;

        public CommitBuilder() {
        }

        public CommitBuilder setCommitId(BigInteger id) {
            this.commitId = id;
            return this;
        }

        public CommitBuilder setUserId(BigInteger id) {
            this.userId = id;
            return this;
        }

        public CommitBuilder setSha(String sha) {
            this.sha = sha;
            return this;
        }

        public CommitBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public CommitBuilder setCommitClasses(Collection<GHCommit.File> commitClasses) {
            if (this.commitClasses == null) {
                this.commitClasses = new HashSet<>();
            }
            this.commitClasses.addAll(commitClasses);
            return this;
        }

        public CommitBuilder setBuildStatus(BuildStatus status) {
            this.buildStatus = status;
            return this;
        }


        public Commit build() {
            return new CommitImpl(this);
        }

    }

    private CommitImpl(CommitBuilder builder){
        this.commitId = builder.commitId;
        this.userId = builder.userId;
        this.sha = builder.sha;
        this.date = builder.date;
        this.buildStatus = builder.buildStatus;
        this.commitClasses = builder.commitClasses;
    }

}
