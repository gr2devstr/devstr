package com.devstr.services;

import com.devstr.model.Issue;
import com.devstr.model.Project;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface StatisticService {

    double reopenRate(Project project, BigInteger userId);

    BigInteger countClosedIssues(Project project, BigInteger userId);

    BigDecimal countClosedIssuesWithPriority(Project project, BigInteger userId);

    BigInteger countOverDates(List<Issue> issues);

    BigInteger countFailBuilds(Issue issue);

    BigInteger countFailBuildsOnProjectOfUser(Project project, BigInteger user);

    BigInteger countFailBuildsOnProjectOfGroupUsers(Project project, List<BigInteger> users);

    double failedBuildMarkPercentageOnProject(Project project, BigInteger userId);

}
