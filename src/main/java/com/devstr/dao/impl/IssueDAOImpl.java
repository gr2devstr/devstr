package com.devstr.dao.impl;

import com.devstr.dao.IssueDAO;
import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.CommitImpl;
import com.devstr.model.impl.IssueImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
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
    @Transactional
    public void createCommits(List<Commit> commits, BigInteger issueId){
        jdbcTemplate.batchUpdate(CREATE_COMMIT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Commit commit = commits.get(i);
                ps.setLong(1,commit.getUserId().longValue());
                ps.setString(2,commit.getSha());
                ps.setDate(3,new Date(commit.getDate().getTime()));
                ps.setLong(4,commit.getBuildStatus().getStatus().longValue());
                ps.setLong(5,issueId.longValue());
            }

            @Override
            public int getBatchSize() {
                return commits.size();
            }
        });
    }

    @Override
    @Transactional
    public void createCommitClasses(List<CommitClass> commitClasses,BigInteger commitId){
        jdbcTemplate.batchUpdate(CREATE_COMMIT, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CommitClass commitClass = commitClasses.get(i);
                ps.setString(1,commitClass.getClassName());
                ps.setInt(2,commitClass.getNumberOfLinesAdded());
                ps.setInt(3,commitClass.getNumberOfLinesChanged());
                ps.setInt(4,commitClass.getNumberOfLinesDeleted());
                ps.setLong(5,commitId.longValue());
            }

            @Override
            public int getBatchSize() {
                return commitClasses.size();
            }
        });
    }

    @Override
    public String getShaLastCommitOnProject() {
        return jdbcTemplate.queryForObject(GET_COMMIT_SHA, String.class);
    }

    class IssueMapper implements RowMapper<Issue>{

        @Override
        public Issue mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IssueImpl.IssueBuilder()
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
                    .setBuildStatus(BuildStatus.getStatusByValue(BigInteger.valueOf(resultSet.getLong(5))))
                    .build();
        }
    }
}
