package com.devstr.model.impl;

import com.devstr.model.Commit;
import com.devstr.model.CommitClass;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CommitImpl  implements Commit {

    private BigInteger commitId;
    private BigInteger userId;
    private String sha;
    private Date date;
    private Set<CommitClass> commitClasses;
    private boolean buildStatus;
    private String buildSha;

    private CommitImpl() {
    }

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
    public Set<CommitClass> getCommitClasses() {
        return commitClasses;
    }

    @Override
    public boolean getBuildStatus() {
        return buildStatus;
    }

    @Override
    public String getBuildSha() {
        return buildSha;
    }

    @Override
    public void setCommitClass(CommitClassImpl commitClass) {
        if (this.commitClasses == null) {
            this.commitClasses = new HashSet<>();
        }
        this.commitClasses.add(commitClass);
    }

    @Override
    public void setCommitClasses(Set<CommitClassImpl> commitClasses) {
        if (this.commitClasses == null) {
            this.commitClasses = new HashSet<>();
        }
        this.commitClasses.addAll(commitClasses);
    }

    public static CommitBuilder builder() {
        return new CommitImpl().new CommitBuilder();
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

    public class CommitBuilder {

        private CommitBuilder() {
        }

        public CommitBuilder setCommitId(BigInteger id) {
            CommitImpl.this.commitId = id;
            return this;
        }

        public CommitBuilder setUserId(BigInteger id) {
            CommitImpl.this.userId = id;
            return this;
        }

        public CommitBuilder setSha(String sha) {
            CommitImpl.this.sha = sha;
            return this;
        }

        public CommitBuilder setDate(Date date) {
            CommitImpl.this.date = date;
            return this;
        }

        public CommitBuilder setCommitClass(CommitClass commitClass) {
            if (CommitImpl.this.commitClasses == null) {
                CommitImpl.this.commitClasses = new HashSet<>();
            }
            CommitImpl.this.commitClasses.add(commitClass);
            return this;
        }

        public CommitBuilder setCommitClasses(Set<CommitClass> commitClasses) {
            if (CommitImpl.this.commitClasses == null) {
                CommitImpl.this.commitClasses = new HashSet<>();
            }
            CommitImpl.this.commitClasses.addAll(commitClasses);
            return this;
        }

        public CommitBuilder setBuildStatus(boolean status) {
            CommitImpl.this.buildStatus = status;
            return this;
        }

        public CommitBuilder setBuildSha(String buildSha) {
            CommitImpl.this.buildSha = buildSha;
            return this;
        }

        public CommitImpl build() {
            return CommitImpl.this;
        }

    }

}
