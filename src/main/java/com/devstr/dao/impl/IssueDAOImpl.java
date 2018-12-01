package com.devstr.dao.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.queryForList(READ_ISSUES_BY_PROJECT,new Object[]{projectId},Issue.class);
    }

    @Override
    public List<Issue> readIssuesByUser(BigInteger userId) {
        return jdbcTemplate.queryForList(READ_ISSUES_BY_USER,new Object[]{userId},Issue.class);
    }

    @Override
    public Issue readIssueById(BigInteger id) {
        RowMapper<Issue> mapper = new IssueMapper();
        return jdbcTemplate.query(READ_ISSUE_BY_ID,new Object[]{id},mapper)
                .get(0);
    }

    @Override
    public void updateIssue(Issue issue) {

    }

    @Override
    public String getShaLastCommitOnProject() {
        return jdbcTemplate.queryForObject(GET_COMMIT_SHA, String.class);
    }

    class IssueMapper implements RowMapper<Issue>{

        @Override
        public Issue mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IssueImpl.Builder()
                    .setIssueId(BigInteger.valueOf(resultSet.getLong(1)))
                    .setIssueKey(resultSet.getString(2))
                    .setProjectId(BigInteger.valueOf(resultSet.getLong(3)))
                    .setIssueType(IssueType.values()[resultSet.getInt(4)])
                    .setIssueStatus(IssueStatus.values()[resultSet.getInt(5)])
                    .setIssuePriority(IssuePriority.values()[resultSet.getInt(6)])
                    .setStartDate(resultSet.getDate(7))
                    .setDueDate(resultSet.getDate(8))
                    .setUserId(BigInteger.valueOf(resultSet.getLong(9)))
                    .setReporterId(BigInteger.valueOf(resultSet.getLong(10)))
                    .build();
        }
    }

}
