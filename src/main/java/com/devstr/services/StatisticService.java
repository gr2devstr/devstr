package com.devstr.services;

import com.devstr.model.Issue;
import com.devstr.model.Project;

import java.math.BigInteger;
import java.util.List;

public interface StatisticService {

    double reopenRate(Project project, BigInteger userId);

    long countClosedIssues(Project project, BigInteger userId);

    double countClosedIssuesWithPriority(Project project, BigInteger userId);

    double countIssueMarkPrioritized(Project project, BigInteger userId);

    long countOverDates(List<Issue> issues);

    long countFailBuildsOnProjectOfUser(Project project, BigInteger user);

    long countFailBuildsOnProjectOfGroupUsers(Project project, List<BigInteger> users);

    double failedBuildMarkPercentageOnProject(Project project, BigInteger userId);

}
