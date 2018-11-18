package com.devstr.dao.row.mappers;

import com.devstr.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Robert in 18.11.2018
 */
public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        return Project.builder()
                .setProjectId(resultSet.getInt("OBJECT_ID"))
                .setProjectName(resultSet.getString(EnumRowMapper.PROJECT.getFullName()))
                .setProjectManagerId(resultSet.getInt("OBJECT_TYPE_ID"))
                .setTechnicalManagerId(resultSet.getInt("OBJECT_TYPE_ID"))
                .setFromDate(resultSet.getDate(EnumRowMapper.CREATION_DATE.getFullName()))
                .setToDate(resultSet.getDate(EnumRowMapper.TO_DATE.getFullName()))
                .setStatus(resultSet.getBoolean(EnumRowMapper.STATUS.getFullName()))
                .setRepoName(resultSet.getString(EnumRowMapper.REPOSITORY_NAME.getFullName()))
                .setGitLogin(resultSet.getString(EnumRowMapper.GIT_LOGIN.getFullName()))
                .setGitPassword(resultSet.getString(EnumRowMapper.GIT_PASSWORD.getFullName()))
                .setJiraLogin(resultSet.getString(EnumRowMapper.JIRA_LOGIN.getFullName()))
                .setJiraPassword(resultSet.getString(EnumRowMapper.JIRA_PASSWORD.getFullName()))
                .build();

    }
}
