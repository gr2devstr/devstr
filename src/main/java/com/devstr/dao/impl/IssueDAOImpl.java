package com.devstr.dao.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.CommitImpl;
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

@Repository
public class IssueDAOImpl implements IssueDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void createIssue(Issue issue) {
           jdbcTemplate.update(CREATE_ISSUE, issue.getIssueKey(), issue.getProjectId().longValue(),
                issue.getType().getId().longValue(), issue.getStatus().getId().longValue(), issue.getPriority().getId().longValue(),
                issue.getStartDate(), issue.getDueDate(),
                issue.getUserId().longValue(), issue.getReporterId().longValue());
    }

    @Override
    @Transactional
    public List<Issue> readIssuesByProject(BigInteger projectId) {
        return jdbcTemplate.query(READ_ISSUES_BY_PROJECT,new Object[]{projectId.longValue()}, new IssueMapper());
    }

    @Override
    @Transactional
    public List<Issue> readIssuesByUser(BigInteger userId) {
        return jdbcTemplate.query(READ_ISSUES_BY_USER,new Object[]{userId.longValue()},new IssueMapper());
    }

    @Override
    @Transactional
    public Issue readIssueById(BigInteger id) {
        return jdbcTemplate.queryForObject(READ_ISSUE_BY_ID,new IssueMapper(),id.longValue());
    }

    @Override
    @Transactional
    public void updateIssueType(BigInteger id, IssueType type) {
        jdbcTemplate.update(UPDATE_ISSUE_TYPE,type.getId().longValue(),id.longValue());
    }

    @Override
    @Transactional
    public void updateIssueStatus(BigInteger id, IssueStatus status) {
        jdbcTemplate.update(UPDATE_ISSUE_STATUS,status.getId().longValue(),id.longValue());
    }

    @Override
    @Transactional
    public void updateIssuePriority(BigInteger id, IssuePriority priority) {
        jdbcTemplate.update(UPDATE_ISSUE_PRIORITY,priority.getId().longValue(),id.longValue());
    }

    @Override
    @Transactional
    public void updateIssueUser(BigInteger id, BigInteger userId) {
        jdbcTemplate.update(UPDATE_ISSUE_USER,userId.longValue(),id.longValue());
    }

    @Override
    @Transactional
    public void deleteIssueById(BigInteger id) {
        jdbcTemplate.update(DELETE_ISSUE,id.longValue());
    }

    @Override
    @Transactional
    public List<Commit> readCommitsByIssue(BigInteger issueId){
        return jdbcTemplate.query(GET_COMMITS_BY_ISSUE_ID,new Object[]{issueId.longValue()},new CommitMapper());
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
                    .setIssueType(IssueType.valueOf(resultSet.getString(4)))
                    .setIssueStatus(IssueStatus.valueOf(resultSet.getString(5)))
                    .setIssuePriority(IssuePriority.valueOf(resultSet.getString(6)))
                    .setStartDate(resultSet.getDate(7))
                    .setDueDate(resultSet.getDate(8))
                    .setUserId(BigInteger.valueOf(resultSet.getLong(9)))
                    .setReporterId(BigInteger.valueOf(resultSet.getLong(10)))
                    .build();
        }
    }

    class CommitMapper implements RowMapper<Commit>{

        @Override
        public Commit mapRow(ResultSet resultSet, int i) throws SQLException {
            return new CommitImpl.CommitBuilder()
                    .setCommitId(BigInteger.valueOf(resultSet.getLong(1)))
                    .setUserId(BigInteger.valueOf(resultSet.getLong(2)))
                    .setSha(resultSet.getString(3))
                    .setDate(resultSet.getDate(4))
                    .setBuildStatus(BuildStatus.valueOf(resultSet.getString(5)))
                    .build();
        }
    }
}
