package com.devstr.services.impl;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import com.atlassian.jira.rest.client.api.domain.ChangelogItem;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.devstr.DevstrFactoryManager;
import com.devstr.dao.IssueDAO;
import com.devstr.dao.impl.UserDAOImpl;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import com.devstr.services.JiraService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JiraServiceImpl implements JiraService {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(JiraServiceImpl.class.getName());


    @Autowired
    private UserDAOImpl userDAO;
    @Autowired
    private IssueDAO issueDAO;


    private String login;
    private String password;
    private String domain;

    private DateTime closeTime;

    private JiraRestClient getConnection() throws URISyntaxException {
        URI jiraServerUri = new URI(domain);
        return new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(jiraServerUri, login, password);
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }


    @Override
    public Issue getIssueByKey(BigInteger projectId, String issueKey, JiraRestClient restClient) throws URISyntaxException {
        BigInteger assigneeId;
        boolean overDate = false;

        IssueRestClient.Expandos[] expandArr = new IssueRestClient.Expandos[]{IssueRestClient.Expandos.CHANGELOG};
        List expand = Arrays.asList(expandArr);

        Promise<com.atlassian.jira.rest.client.api.domain.Issue> issuePromise = restClient.getIssueClient().getIssue(issueKey, expand);
        com.atlassian.jira.rest.client.api.domain.Issue issue = issuePromise.claim();

        if (getIssueStatus(issue.getStatus().getName()) == IssueStatus.CLOSED) {
            String assigneeName = getLastAssignee(issue);
            if (closeTime.isAfter(issue.getDueDate())) overDate = true;
            if (assigneeName != null) {
                String email = restClient.getUserClient().getUser(assigneeName).claim().getEmailAddress();
                LOGGER.info("getAssignee " + email + " " + issue.getKey());
                LOGGER.info("getReporter " + issue.getReporter().getEmailAddress());
                assigneeId = userDAO.readUserIdByEmail(email);
                LOGGER.info("Read email");
            } else assigneeId = userDAO.readUserIdByEmail(issue.getAssignee().getEmailAddress());
        } else {
            String email = issue.getAssignee().getEmailAddress();
            LOGGER.info("getAssignee " + email + " " + issue.getKey());
            LOGGER.info("getReporter " + issue.getReporter().getEmailAddress());
            assigneeId = userDAO.readUserIdByEmail(email);
        }

        return new IssueImpl.IssueBuilder()
                .setIssueKey(issue.getKey())
                .setProjectId(projectId)
                .setIssueType(getIssueType(issue.getIssueType().getName()))
                .setIssueStatus(getIssueStatus(issue.getStatus().getName()))
                .setIssuePriority(getIssuePriority(issue.getPriority().getName()))
                .setStartDate(new java.sql.Date(issue.getCreationDate().toDate().getTime()))
                .setDueDate(new java.sql.Date(issue.getDueDate().toDate().getTime()))
                .setUserId(assigneeId)
                .setReporterId(userDAO.readUserIdByEmail(issue.getReporter().getEmailAddress()))
                .setOverdate(overDate)
                .build();
    }


    @Override
    public List<Issue> getIssuesByProjectId(BigInteger projectId) throws URISyntaxException, IOException {
        JiraRestClient jiraRestClient = getConnection();
        ArrayList<Issue> issuesList = new ArrayList<>();
        String jql = String.format("project = %s", userDAO.readObjectNameById(projectId));

        SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
        SearchResult searchResult = searchRestClient.searchJql(jql, MAX_VLUE, START_VLUE, SEARCH_REST_FIELDS).claim();
        Iterable<com.atlassian.jira.rest.client.api.domain.Issue> issues = searchResult.getIssues();
        for (com.atlassian.jira.rest.client.api.domain.Issue issue : issues) {
            issuesList.add(getIssueByKey(projectId, issue.getKey(), jiraRestClient));
        }
        jiraRestClient.close();
        writeIssuesToDb(issuesList);
        return issuesList;
    }

    @Override
    public void updateIssues(BigInteger projectId) throws IOException, URISyntaxException {
        JiraRestClient jiraRestClient = getConnection();
        String jql = String.format("project = %s", userDAO.readObjectNameById(projectId));

        SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
        SearchResult searchResult = searchRestClient.searchJql(jql, MAX_VLUE, START_VLUE, SEARCH_REST_FIELDS).claim();
        Iterable<com.atlassian.jira.rest.client.api.domain.Issue> issues = searchResult.getIssues();
        String lastKey = issueDAO.getLastIssueKey();

        ArrayList<Issue> issueList = new ArrayList<>();
        updateIssuesStatus(issues);
        for (com.atlassian.jira.rest.client.api.domain.Issue issue : issues) {
            if (lastKey != null && !issue.getKey().equals(lastKey)) {
                issueList.add(getIssueByKey(projectId, issue.getKey(), jiraRestClient));
            } else break;
        }
        writeIssuesToDb(issueList);
        jiraRestClient.close();
    }

    @Override
    public void updateIssuesStatus(Iterable<com.atlassian.jira.rest.client.api.domain.Issue> issues) {
        List<Issue> issueList = new ArrayList<>();

        for (com.atlassian.jira.rest.client.api.domain.Issue issue : issues) {
            issueList.add(new IssueImpl.IssueBuilder()
                    .setIssueKey(issue.getKey())
                    .setIssueStatus(getIssueStatus(issue.getStatus().getName()))
                    .build());
        }
        issueDAO.updateIssuesStatus(issueList);
    }

    @Override
    public void writeIssuesToDb(ArrayList<Issue> issues) {
        issueDAO.createIssues(issues);
    }


    private IssueType getIssueType(String type) {
        return IssueType.valueOf(modify(type));
    }

    private IssueStatus getIssueStatus(String status) {
        return IssueStatus.valueOf(modify(status));
    }

    private IssuePriority getIssuePriority(String priority) {
        return IssuePriority.valueOf(modify(priority));
    }

    private String modify(String s) {
        return s.toUpperCase().replace('-', '_').replace(' ', '_');
    }

    private String getLastAssignee(com.atlassian.jira.rest.client.api.domain.Issue issue) {
        for (ChangelogGroup group : issue.getChangelog()) {
            for (ChangelogItem changelogItem : group.getItems()) {
                closeTime = group.getCreated();
                if (changelogItem.getField().equals("assignee") && changelogItem.getFrom() != null) {
                    return changelogItem.getFrom();
                }
            }
        }
        return null;
    }

}
