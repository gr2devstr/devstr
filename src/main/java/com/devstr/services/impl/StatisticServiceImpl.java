package com.devstr.services.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.Project;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.services.StatisticService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class StatisticServiceImpl implements StatisticService {
    private IssueDAO issueDAO;
    @Override
    public double reopenRate(Project project, BigInteger userId) {

        return 0;
    }

    @Override
    public BigInteger countClosedIssues(Project project, BigInteger userId) {
        return count(project.getIssuesId(), userId);
    }

    @Override
    public BigDecimal countClosedIssuesWithPriority(Project project, BigInteger userId) {
        return null;
    }

    @Override
    public BigInteger countOverDates(List<Issue> issues) {
        return null;
    }

    @Override
    public BigInteger countFailBuilds(Issue issue) {
        return countFails(issue.getCommits());
    }

    @Override
    public BigInteger countFailBuildsOnProjectOfUser(Project project, BigInteger user) {
        return null;
    }

    @Override
    public BigInteger countFailBuildsOnProjectOfGroupUsers(Project project, List<BigInteger> users) {
        return null;
    }

    @Override
    public double failedBuildMarkPercentageOnProject(Project project, BigInteger userId) {
        return 0;
    }

    private BigInteger count(Collection<BigInteger> issues, BigInteger userId) {
        BigInteger counter = BigInteger.ZERO;
        for (BigInteger issueId : issues) {
            Issue issue = issueDAO.readIssueById(issueId);
            if (issue.getUserId().equals(userId) && issue.getStatus().equals(IssueStatus.CLOSED))
                counter = counter.add(BigInteger.ONE);
        }
        return counter;
    }

    private BigInteger countFails(List<Commit> commits) {
        BigInteger counter = BigInteger.ZERO;
        for (Commit commit : commits) {
            if (commit.getBuildStatus().getStatus().equals(BigInteger.ZERO))
                counter = counter.add(BigInteger.ONE);
        }
        return counter;
    }
}
