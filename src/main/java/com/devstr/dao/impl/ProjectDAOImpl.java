package com.devstr.dao.impl;

import com.devstr.dao.ProjectDAO;
import com.devstr.model.Project;
import com.devstr.model.enumerations.Status;
import com.devstr.model.impl.ProjectImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createProject(String name, BigInteger managerId) {
        jdbcTemplate.update(CREATE_BASIC_PROJECT, new Object[]{name, managerId});
    }

    @Override
    public void updateProjectApisParam(BigInteger projectId, String repoName, String gitLogin, String gitPassword, String jiraLogin, String jiraPassword) {
        jdbcTemplate.update(UPDATE_PROJECT_API_PARAMETERS, new Object[]{
                projectId, repoName,
                projectId, gitLogin,
                projectId, gitPassword,
                projectId, jiraLogin,
                projectId, jiraPassword
        });
    }

    @Override
    public void addDevsOnProject(BigInteger projectId, Collection<BigInteger> developersId) {
        List<BigInteger> devs = new ArrayList<>(developersId);
        jdbcTemplate.batchUpdate(ADD_PROJECT_DEVS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, devs.get(i).longValue());
                preparedStatement.setLong(2, projectId.longValue());
            }

            @Override
            public int getBatchSize() {
                return devs.size();
            }
        });
    }

    @Override
    public void updateIssuesOnProject(BigInteger projectId, Collection<BigInteger> issuesId) {
        List<BigInteger> issues = new ArrayList<>(issuesId);
        jdbcTemplate.batchUpdate(UPDATE_PROJECT_ISSUES, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, projectId.longValue());
                preparedStatement.setLong(2, issues.get(i).longValue());
            }

            @Override
            public int getBatchSize() {
                return issues.size();
            }
        });
    }

    @Override
    public Project readProjectById(BigInteger projectID) {
        RowMapper<Project> mapper = new ProjectMapper();
        return jdbcTemplate.queryForObject(READ_PROJECT_BY_ID, mapper, projectID);
    }

    @Override
    public Project readProjectByName(String projectName) {
        RowMapper<Project> mapper = new ProjectMapper();
        return jdbcTemplate.queryForObject(READ_PROJECT_BY_NAME, mapper, projectName);
    }


    class ProjectMapper implements RowMapper<Project> {

        @Override
        public Project mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ProjectImpl.Builder(resultSet.getString(1), BigInteger.valueOf(resultSet.getLong(2)))
                    .setProjectId(BigInteger.valueOf(resultSet.getLong(3)))
                    .setTechnicalManagerId(BigInteger.valueOf(resultSet.getLong(4)))
                    .setFromDate(resultSet.getDate(5))
                    .setStatus(Status.valueOf(resultSet.getString(6)))
                    .setRepoName(resultSet.getString(7))
                    .setGitLogin(resultSet.getString(8))
                    .setGitPassword(resultSet.getString(9))
                    .setJiraLogin(resultSet.getString(10))
                    .setJiraPassword(resultSet.getString(11))
                    .build();
        }
    }
}
