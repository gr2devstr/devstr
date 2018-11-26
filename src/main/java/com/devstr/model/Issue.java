package com.devstr.model;

import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

public interface Issue {

    BigInteger getIssueId();

    String getIssueKey();

    IssueType getType();

    IssueStatus getStatus();

    IssuePriority getPriority();

    Date getStartDate();

    Date getDueDate();

    BigInteger getProjectId();

    BigInteger getUserId();

    String getReporter();

    Set<Commit> getCommits();

    void setCommit(Commit commit);

    void setCommits(Set<Commit> commits);
}
