package com.devstr.model;

import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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

    BigInteger getReporterId();

    List<Commit> getCommits();

    void setCommit(Commit commit);

    void setCommits(List<Commit> commits);
}
