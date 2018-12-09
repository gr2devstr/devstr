package com.devstr.services;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.devstr.model.Issue;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface JiraService {
    static final HashSet<String> SEARCH_REST_FIELDS = new HashSet<String>(
            Arrays.asList(new String[]{"project", "summary", "issuetype",
                    "status", "created", "updated", "priority", "timetracking",
                    "versions", "assignee", "worklog", "duedate", "resolved", "resolutiondate", "self", "components"}));

    void setLogin(String login);

    void setPassword(String password);

    void setDomain(String domain);

    Issue getIssueByKey(BigInteger projectId, String issueKey, JiraRestClient restClient) throws URISyntaxException;

    List<Issue> getIssuesByProjectId(BigInteger projectId) throws URISyntaxException, IOException;

    void updateIssues(BigInteger projectId) throws IOException, URISyntaxException;

    void updateIssuesStatus(Iterable<com.atlassian.jira.rest.client.api.domain.Issue> issues);

    void writeIssuesToDb(ArrayList<Issue> issues);

    Integer START_VLUE = 0;
    Integer MAX_VLUE = 100000;


}
