package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.IssueDAO;
import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.Project;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.services.StatisticService;

import java.math.BigInteger;
import java.util.*;

public class StatisticServiceImpl implements StatisticService {
    private IssueDAO issueDAO = DevstrFactoryManager.getDAOFactory().getIssueDAO();

    @Override
    public double reopenRateToClosedIssues(Project project, BigInteger userId) {
        long reopenNumber = countByStatus(project.getIssuesId(), userId, IssueStatus.REOPEN);
        return (double) reopenNumber/countClosedIssues(project, userId);
    }

    @Override
    public double reopenRateToProjectReopens(Project project, BigInteger userId) {
        long reopenNumber = countByStatus(project.getIssuesId(), userId, IssueStatus.REOPEN);
        return (double) reopenNumber/countProjectReopens(project);
    }

    @Override
    public long countClosedIssues(Project project) {
        return countByStatus(project.getIssuesId(), IssueStatus.CLOSED);
    }

    @Override
    public long countClosedIssues(Project project, BigInteger userId) {
        return countByStatus(project.getIssuesId(), userId, IssueStatus.CLOSED);
    }

    @Override
    public double countClosedIssuesWithPriority(Project project, BigInteger userId) {
        Map<IssuePriority, Integer> issuePriorityMap = getClosedIssuesWithPriorityMap(project.getIssuesId(), userId);
        Set<Map.Entry<IssuePriority, Integer>> entrySet = issuePriorityMap.entrySet();
        double result = 0;
        for (Map.Entry<IssuePriority, Integer> e : entrySet) {
            result+= e.getValue()*getWeight(e.getKey());
        }
        return result;
    }

    @Override
    public double countIssueMarkPrioritized(Project project, BigInteger userId) {
        return countClosedIssuesWithPriority(project, userId) / getProjectTotalWeight(project);
    }

    @Override
    public long countOverDates(Project project, BigInteger userId) {
        long result = 0;
        for (BigInteger issueId :project.getIssuesId()) {
            Issue issue = issueDAO.readIssueById(issueId);
            if (issue.getUserId().equals(userId)) //&& issue.isOverdated()
                result++;
        }
        return result;
    }

    @Override
    public long countFailBuildsOnProjectOfUser(Project project, BigInteger userId) {
        long result = 0;
        for (BigInteger issueId : project.getIssuesId()) {
            Issue issue = issueDAO.readIssueById(issueId);
            result += countFailsOfUser(issue.getCommits(), userId);
        }
        return result;
    }

    @Override
    public long countFailBuildsOnProjectOfGroupUsers(Project project, Collection<BigInteger> users) {
        long result = 0;
        for (BigInteger userId : users) {
            result += countFailBuildsOnProjectOfUser(project, userId);
        }
        return result;
    }

    @Override
    public double failedBuildMarkOnProject(Project project, BigInteger userId) {
        return ((double) countFailBuildsOnProjectOfUser(project, userId))/
                countFailBuildsOnProjectOfGroupUsers(project, project.getDevelopersId());
    }

    private long countByStatus(Collection<BigInteger> issues, BigInteger userId, IssueStatus issueStatus) {
        long counter = 0;
        for (BigInteger issueId : issues) {
            Issue issue = issueDAO.readIssueById(issueId);
            if (issue.getUserId().equals(userId) && issue.getStatus().equals(issueStatus))
                counter++;
        }
        return counter;
    }

    private long countByStatus(Collection<BigInteger> issues, IssueStatus issueStatus) {
        long counter = 0;
        for (BigInteger issueId : issues) {
            Issue issue = issueDAO.readIssueById(issueId);
            if (issue.getStatus().equals(issueStatus))
                counter++;
        }
        return counter;
    }

    private long countFailsOfUser(List<Commit> commits, BigInteger userId) {
        long counter = 0;
        for (Commit commit : commits) {
            if (commit.getBuildStatus().equals(BuildStatus.FAILURE) && commit.getUserId().equals(userId))
                counter++;
        }
        return counter;
    }

    private Map<IssuePriority, Integer> getClosedIssuesWithPriorityMap(Collection<BigInteger> issues, BigInteger userId) {
        Map<IssuePriority, Integer> numberOfSolvedGroupByPriority = new HashMap<>();
        for (IssuePriority priority: IssuePriority.values()) {
            Integer counter = 0;
            for (BigInteger issueId : issues) {
                Issue issue = issueDAO.readIssueById(issueId);
                if (issue.getPriority().equals(priority))
                    counter++;
            }
            numberOfSolvedGroupByPriority.put(priority, counter);
        }
        return numberOfSolvedGroupByPriority;
    }

    private double getWeight(IssuePriority priority) {
        switch (priority) {
            case BLOCKER:
                return 1.5;
            case CRITICAL:
                return 1;
            case HIGH:
                return 0.8;
            case MEDIUM:
                return 0.5;
            case LOW:
                return 0.3;
            case LOWEST:
                return 0.1;
        }
        return 0;
    }

    private double getProjectTotalWeight(Project project) {
        double weight = 0;
        for (BigInteger issueId : project.getIssuesId()) {
            Issue issue = issueDAO.readIssueById(issueId);
            weight += getWeight(issue.getPriority());
        }
        return weight;
    }

    private double countProjectReopens(Project project) {
        return countByStatus(project.getIssuesId(), IssueStatus.REOPEN);
    }
}
