package com.devstr.services.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import com.atlassian.jira.rest.client.api.domain.ChangelogItem;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.devstr.dao.impl.UserDAOImpl;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import com.devstr.services.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiraServiceImpl implements JiraService {

    @Autowired
    private UserDAOImpl userDAO;

    private String login;
    private String password;
    private String domain;

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
    public Issue getIssueByKey(String issueKey, JiraRestClient restClient) throws URISyntaxException {
        List<String> assineeEmail = new ArrayList<>();
        BigInteger assigneeId;
        boolean overDate = false;

        Promise<com.atlassian.jira.rest.client.api.domain.Issue> issuePromise = restClient.getIssueClient().getIssue(issueKey);
        com.atlassian.jira.rest.client.api.domain.Issue issue = issuePromise.claim();

        if (getIssueStatus(issue.getStatus().getName()) == IssueStatus.CLOSED) {
            for (ChangelogGroup group : issue.getChangelog()) {
                for (ChangelogItem changelogItem : group.getItems()) {
                    if (changelogItem.getField().equals("assignee")) {
                        assineeEmail.add(changelogItem.getFrom());
                    }
                }
            }

            assigneeId = userDAO.readUserIdByEmail(restClient.getUserClient().getUser(assineeEmail.get(0)).claim().getEmailAddress());
        } else assigneeId = userDAO.readUserIdByEmail(issue.getAssignee().getEmailAddress());

        return new IssueImpl.Builder()
                .setIssueKey(issue.getKey())
                .setIssueType(getIssueType(issue.getIssueType().getName()))
                .setIssueStatus(getIssueStatus(issue.getStatus().getName()))
                .setIssuePriority(getIssuePriority(issue.getPriority().getName()))
                .setStartDate(issue.getCreationDate().toDate())
                .setDueDate(issue.getDueDate().toDate())
                .setUserId(assigneeId)
                .setReporterId(userDAO.readUserIdByEmail(issue.getReporter().getEmailAddress()))
                .setOverdate(overDate)
                .build();
    }

    @Override
    public List<Issue> getIssuesByProjectId(BigInteger projectId) throws URISyntaxException, IOException {
        JiraRestClient jiraRestClient = getConnection();
        List<Issue> issuesList = new ArrayList<>();
        String jql = String.format("project = %s", userDAO.readObjectNameById(projectId));

        SearchRestClient searchRestClient = getConnection().getSearchClient();
        SearchResult searchResult = searchRestClient.searchJql(jql, MAX_VLUE, START_VLUE, SEARCH_REST_FIELDS).claim();
        Iterable<com.atlassian.jira.rest.client.api.domain.Issue> issues = searchResult.getIssues();
        for (com.atlassian.jira.rest.client.api.domain.Issue issue : issues) {
            issuesList.add(getIssueByKey(issue.getKey(), jiraRestClient));
        }
        jiraRestClient.close();
        return issuesList;
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

}
