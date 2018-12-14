package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.IssueDAO;
import com.devstr.dao.ProjectDAO;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Issue;
import com.devstr.model.Project;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.services.JiraService;
import com.devstr.services.StatisticUpdateService;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StatisticUpdateServiceImpl implements StatisticUpdateService {

    private JiraService jiraService = DevstrFactoryManager.getServiceFactory().getJiraService();
    private IssueDAO issueDAO = DevstrFactoryManager.getDAOFactory().getIssueDAO();
    private ProjectDAO projectDAO = DevstrFactoryManager.getDAOFactory().getProjectDAO();
    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(StatisticUpdateServiceImpl.class.getName());

    @Override
    public void updateStatisticByTravis(BigInteger projectId) {
        update(projectId);
    }

    @Override
    public void updateStatisticByView(BigInteger projectId) {
        update(projectId);
    }

    private void update(BigInteger projectId) {
        Project project = projectDAO.readProjectById(projectId);
        jiraService.setDomain(project.getJiraDomain());
        jiraService.setLogin(project.getJiraLogin());
        jiraService.setPassword(project.getJiraPassword());
        try {
            ArrayList<Issue> issues = jiraService.getIssuesByProjectId(projectId);
//            for (Issue issue : issues) {
//                if (issue.getStatus().equals(IssueStatus.REOPEN)||issue.getStatus().equals(IssueStatus.CLOSED))
//                    issueDAO.createIssue(issue);
//            }
            issues.stream().filter(issue -> issue.getStatus().equals(IssueStatus.REOPEN) || issue.getStatus().equals(IssueStatus.CLOSED)).collect(Collectors.toList());
            issueDAO.createIssues(issues);


        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error while getting statistics from jira: ", e);
        }
    }
}
