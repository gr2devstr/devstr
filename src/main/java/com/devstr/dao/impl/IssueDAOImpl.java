package com.devstr.dao.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Transactional
@Repository
public class IssueDAOImpl implements IssueDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createIssue(Issue issue) {
        jdbcTemplate.update(CREATE_ISSUE,new Object[]{
                issue.getIssueKey(), issue.getProjectId(),
                issue.getType(), issue.getStatus(), issue.getPriority(),
                issue.getStartDate(), issue.getDueDate(),
                issue.getUserId(), issue.getReporterId()
        });
    }

    @Override
    public List<Issue> readIssuesByProject(BigInteger projectId) {
        return null;
    }

    @Override
    public List<Issue> readIssuesByUser(BigInteger userId) {
        return null;
    }

    @Override
    public Issue readIssueById(BigInteger id) {
        return null;
    }

    @Override
    public void updateIssue(Issue issue) {

    }
}
