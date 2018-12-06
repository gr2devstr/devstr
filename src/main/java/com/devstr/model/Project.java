package com.devstr.model;

import com.devstr.model.enumerations.Status;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface Project {

    BigInteger getProjectId();

    String getProjectName();

    BigInteger getProjectManagerId();

    BigInteger getTechnicalManagerId();

    Collection<BigInteger> getDevelopersId();

    Collection<BigInteger> getIssuesId();

    String getRepoName();

    String getGitLogin();

    String getGitPassword();

    String getJiraLogin();

    String getJiraPassword();

    String getJiraDomain();

    Date getFromDate();

    Date getToDate();

    Status getStatus();

}
