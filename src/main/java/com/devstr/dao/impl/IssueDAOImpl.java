package com.devstr.dao.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.IssueDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.CommitImpl;
import com.devstr.model.impl.IssueImpl;
import org.kohsuke.github.GHCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import java.util.*;

@Repository
public class IssueDAOImpl implements IssueDAO {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(IssueDAOImpl.class.getName());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void createIssue(Issue issue) {
        try {
            jdbcTemplate.update(CREATE_ISSUE, issue.getIssueKey(), issue.getProjectId().longValue(),
                    issue.getType().getId().longValue(), issue.getStatus().getId().longValue(), issue.getPriority().getId().longValue(),
                    issue.getStartDate(), issue.getDueDate(),
                    issue.getUserId().longValue(), issue.getReporterId().longValue(), overdateNum(issue.isOverdated()));
        } catch (DataAccessException exc) {
            String excMessage = "Failed to create issue";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public void createIssues(ArrayList<Issue> issues) {
        Collections.reverse(issues);
        try {
            jdbcTemplate.batchUpdate(CREATE_ISSUE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Issue issue = issues.get(i);
                    ps.setString(1, issue.getIssueKey());
                    ps.setLong(2, issue.getProjectId().longValue());
                    ps.setLong(3, issue.getType().getId().longValue());
                    ps.setLong(4, issue.getStatus().getId().longValue());
                    ps.setLong(5, issue.getPriority().getId().longValue());
                    ps.setDate(6, issue.getStartDate());
                    ps.setDate(7, issue.getDueDate());
                    ps.setLong(8, issue.getUserId().longValue());
                    ps.setLong(9, issue.getReporterId().longValue());
                    ps.setLong(10, overdateNum(issue.isOverdated()));
                }

                @Override
                public int getBatchSize() {
                    return issues.size();
                }
            });
        } catch (DataAccessException exc) {
            String excMessage = "Failed to create issues";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public String getLastIssueKey() {
        if (!commitsIsEmpty().equals(BigInteger.valueOf(0L))) {
            try {
                return jdbcTemplate.queryForObject(GET_LAST_ISSUE_KEY, new Object[]{}, String.class);
            } catch (DataAccessException exc) {
                String excMessage = "Failed to read last issue key";
                LOGGER.error(excMessage, exc);
                throw new DaoException(excMessage, exc);
            }
        } else {
            return null;
        }
    }

    private BigInteger issuesIsEmpty() {
        try {
            return jdbcTemplate.queryForObject(GET_ISSUES_COUNT, BigInteger.class);
        } catch (DataAccessException exc) {
            String excMessage = "Failed to get issues count";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public List<Issue> readIssuesByProject(BigInteger projectId) {
        try {
            return jdbcTemplate.query(READ_ISSUES_BY_PROJECT, new Object[]{projectId.longValue()}, new IssueMapper());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read issues by project ID: " + projectId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public List<Issue> readIssuesByUser(BigInteger userId) {
        try {
            return jdbcTemplate.query(READ_ISSUES_BY_USER, new Object[]{userId.longValue()}, new IssueMapper());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read issues by user ID: " + userId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public Issue readIssueById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(READ_ISSUE_BY_ID, new IssueMapper(), id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read issue by ID: " + id;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public BigInteger readIdIssueByKey(String key) {
        try {
            return jdbcTemplate.queryForObject(READ_ISSUE_ID_BY_KEY, new Object[]{key}, BigInteger.class);
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read issue by key: " + key;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public Map<String, BigInteger> readAllIssuesKey() {
        try {
            return jdbcTemplate.query(READ_ALL_ISSUES_KEY, rs -> {
                Map<String, BigInteger> mapRet = new HashMap<>();
                while (rs.next()) {
                    mapRet.put(rs.getString(1), BigInteger.valueOf(rs.getLong(2)));
                }
                return mapRet;
            });
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read all issues key";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void updateIssueType(BigInteger id, IssueType type) {
        try {
            jdbcTemplate.update(UPDATE_ISSUE_TYPE, type.getId().longValue(), id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to update issue type, issue ID: " + id
                    + ", new type: " + type.toString();
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void updateIssuesStatus(List<Issue> issues) {
        try {
            jdbcTemplate.batchUpdate(UPDATE_ISSUE_STATUS, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Issue issue = issues.get(i);
                    ps.setLong(1, issue.getStatus().getId().longValue());
                    ps.setString(2, issue.getIssueKey());
                }

                @Override
                public int getBatchSize() {
                    return issues.size();
                }
            });
        } catch (DataAccessException exc) {
            String excMessage = "Failed to update issues status";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void updateIssuePriority(BigInteger id, IssuePriority priority) {
        try {
            jdbcTemplate.update(UPDATE_ISSUE_PRIORITY, priority.getId().longValue(), id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to update issue priority, issue ID: " + id
                    + ", issue priority: " + priority.toString();
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void updateIssueUser(BigInteger id, BigInteger userId) {
        try {
            jdbcTemplate.update(UPDATE_ISSUE_USER, userId.longValue(), id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to update issue user, issue ID: " + id
                    + ", user ID: " + userId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void deleteIssueById(BigInteger id) {
        try {
            jdbcTemplate.update(DELETE_ISSUE, id.longValue());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to delete issue by ID: " + id;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public List<Commit> readCommitsByIssue(BigInteger issueId) {
        try {
            return jdbcTemplate.query(GET_COMMITS_BY_ISSUE_ID, new Object[]{issueId.longValue()}, new CommitMapper());
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read commits by issue ID: " + issueId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void createCommits(List<Commit> commits) {
        try {
            jdbcTemplate.batchUpdate(CREATE_COMMIT, new BatchPreparedStatementSetter() {
                Commit commit;

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    commit = commits.get(i);
                    ps.setLong(1, commit.getUserId().longValue());
                    ps.setString(2, commit.getSha());
                    ps.setDate(3, new Date(commit.getDate().getTime()));
                    ps.setLong(4, commit.getBuildStatus().getStatus().longValue());
                    ps.setLong(5, commit.getIssueId().longValue());
                }

                @Override
                public int getBatchSize() {
                    return commits.size();
                }
            });
        } catch (DataAccessException exc) {
            String excMessage = "Failed to create commits";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    @Transactional
    public void createCommitClasses(List<GHCommit.File> commitClasses, BigInteger commitId) {
        try {
            jdbcTemplate.batchUpdate(CREATE_COMMITCLASS, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    GHCommit.File commitClass = commitClasses.get(i);
                    ps.setString(1, commitClass.getFileName());
                    ps.setInt(2, commitClass.getLinesAdded());
                    ps.setInt(3, commitClass.getLinesChanged());
                    ps.setInt(4, commitClass.getLinesDeleted());
                    ps.setLong(5, commitId.longValue());
                }

                @Override
                public int getBatchSize() {
                    return commitClasses.size();
                }
            });
        } catch (DataAccessException exc) {
            String excMessage = "Failed to create committed classes, commit ID: " + commitId;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public BigInteger readCommitIdBySha(String sha) {
        try {
            return jdbcTemplate.queryForObject(GET_COMMIT_ID_BY_SHA, new Object[]{sha}, BigInteger.class);
        } catch (DataAccessException exc) {
            String excMessage = "Failed to read commit ID by SHA: " + sha;
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    @Override
    public Date getDateLastCommitOnProject() {
        if (!commitsIsEmpty().equals(BigInteger.valueOf(0L))) {
            try {
                return jdbcTemplate.queryForObject(GET_COMMIT_DATE, Date.class);
            } catch (DataAccessException exc) {
                String excMessage = "Failed to read date of last commit on project";
                LOGGER.error(excMessage, exc);
                throw new DaoException(excMessage, exc);
            }
        } else {
            return null;
        }
    }

    private BigInteger commitsIsEmpty() {
        try {
            return jdbcTemplate.queryForObject(GET_COUNT, BigInteger.class);
        } catch (DataAccessException exc) {
            String excMessage = "Failed to get commits count";
            LOGGER.error(excMessage, exc);
            throw new DaoException(excMessage, exc);
        }
    }

    private long overdateNum(boolean over) {
        if (over) {
            return 1L;
        } else {
            return 0;
        }
    }

    class IssueMapper implements RowMapper<Issue> {

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

    class IssueKeyMapper implements RowMapper<Map<String, BigInteger>> {
        @Override
        public Map<String, BigInteger> mapRow(ResultSet resultSet, int i) throws SQLException {
            HashMap<String, BigInteger> map = new HashMap<>();
            map.put(resultSet.getString(1), BigInteger.valueOf(resultSet.getLong(2)));
            return map;
        }
    }

    class CommitMapper implements RowMapper<Commit> {

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
