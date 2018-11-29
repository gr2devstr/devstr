package com.devstr.dao.impl;

import com.devstr.dao.ProjectDAO;
import com.devstr.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Set;

public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void createProject(String name, int managerId, int techId, Set<Integer> devs, String repoName, String gitLogin, String gitPass, String jiraLogin, String jiraPass) {
        String sql = "INSERT ALL " +
                "INTO OBJECTS(NAME, OBJECT_TYPE_ID) " +
                " VALUES(?,2)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE)" +
                "VALUES(5, PROJ_ID, SYSDATE)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)" +
                "VALUES(7,PROJ_ID,5)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, DATE_VALUE)" +
                "VALUES(8,PROJ_ID,?)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE)" +
                "VALUES(9,PROJ_ID,?)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE)" +
                "VALUES(10,PROJ_ID,?)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE)" +
                "VALUES(11,PROJ_ID,?)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE)" +
                "VALUES(12,PROJ_ID,?)" +
                "INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, VALUE)" +
                "VALUES(13,PROJ_ID,?)" +
                "SELECT proj.OBJECT_ID AS PROJ_ID" +
                "        FROM OBJECTS proj WHERE proj.NAME = ?;";
        jdbcTemplate.update(sql, new Object[]{
                name,
                null,
                gitLogin,
                jiraPass,
                jiraLogin,
                jiraPass,
                name
        });
    }

    @Override
    public Project readProjectById(int projectID) {
        return null;
    }

    @Override
    public Project readProjectByName(String projectName) {
        return null;
    }

    @Override
    public void updateProject(Project project) {

    }

    /*class ProjectMapper implements RowMapper<Project>{
        @Override
        public Project mapRow(ResultSet resultSet, int i) throws SQLException {
            return  ProjectImpl.Builder(resultSet.getBigDecimal(AttributeID.PROJECT.getId()),)
                    .setProjectId(resultSet.getBigDecimal("OBJECT_ID").toBigInteger())
                    //.setProjectName(resultSet.getString(AttributeID.PROJECT.getId()))
                    //.setProjectManagerId(resultSet.getBigDecimal("OBJECT_TYPE_ID").toBigInteger())
                    .setTechnicalManagerId(resultSet.getBigDecimal("OBJECT_TYPE_ID").toBigInteger())
                    .setFromDate(resultSet.getDate(AttributeID.CREATION_DATE.getId()))
                    .setToDate(resultSet.getDate(AttributeID.TO_DATE.getId()))
                    .setStatus(resultSet.getBoolean(AttributeID.STATUS.getId()))
                    .setRepoName(resultSet.getString(AttributeID.REPOSITORY_NAME.getId()))
                    .setGitLogin(resultSet.getString(AttributeID.GIT_LOGIN.getId()))
                    .setGitPassword(resultSet.getString(AttributeID.GIT_PASSWORD.getId()))
                    .setJiraLogin(resultSet.getString(AttributeID.JIRA_LOGIN.getId()))
                    .setJiraPassword(resultSet.getString(AttributeID.JIRA_PASSWORD.getId()))
                    .build();

        }
    }*/
}
