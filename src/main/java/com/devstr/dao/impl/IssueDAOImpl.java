package com.devstr.dao.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.util.List;

public class IssueDAOImpl implements IssueDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createIssue(Issue issue) {

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
