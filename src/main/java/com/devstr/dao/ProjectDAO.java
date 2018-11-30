package com.devstr.dao;


import com.devstr.model.Project;

import java.math.BigInteger;
import java.util.Collection;

public interface ProjectDAO {


    /**
     * The method that creates the new project
     *
     * @param name project name
     * @param managerId
     *
     */
    void createProject(String name, BigInteger managerId);

    /**
     * Add/Update parameters for project API
     *
     * @param projectId
     * @param repoName GitHub repository name
     * @param gitLogin
     * @param gitPassword
     * @param jiraLogin
     * @param jiraPassword
     */
    void updateProjectApisParam(BigInteger projectId, String repoName, String gitLogin, String gitPassword, String jiraLogin, String jiraPassword);

    /**
     * Add/Update developers on project
     *
     * @param developersId
     */
    void addDevsOnProject(BigInteger projectId, Collection<BigInteger> developersId);

    /**
     * Add/Update issues on project
     *
     * @param issuesId
     */
    void updateIssuesOnProject(BigInteger projectId, Collection<BigInteger> issuesId);

    /**
     * Method to get the project by ID
     *
     * @param projectID project id for find
     * @return found project
     */
    Project readProjectById(BigInteger projectID);

    /**
     * @param projectName project name for find
     * @return found project
     */
    Project readProjectByName(String projectName);


    String CREATE_BASIC_PROJECT = "begin" +
            "INSERT INTO OBJECTS(NAME, OBJECT_TYPE_ID)VALUES(?,2);" +
            "INSERT all" +
            "    INTO OBJREFERENCE (ATTRN_ID,REFERENCE, OBJECT_ID)VALUES(26,?,OBJ_ID.currval)" +//pm to project
            "    into ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)values (5,OBJ_ID.currval,sysdate)" +// create date
            "    INTO ATTRIBUTES(ATTRN_ID, OBJECT_ID, LIST_VALUE_ID) VALUES(7,OBJ_ID.currval,5)" +// status active
            "select * from dual;" +
            "END;";
    String UPDATE_PROJECT_API_PARAMETERS = "BEGIN" +
            "update ATTRIBUTES set ATTRN_ID=13, OBJECT_ID=?, VALUE=?" +
            "where ATTRN_ID = 13;" +
            "update ATTRIBUTES set ATTRN_ID=9, OBJECT_ID=?, VALUE=?" +
            "where ATTRN_ID = 9;" +
            "update ATTRIBUTES set ATTRN_ID=10, OBJECT_ID=?, VALUE=?" +
            "where ATTRN_ID = 10;" +
            "update ATTRIBUTES set ATTRN_ID=11, OBJECT_ID=?, VALUE=?" +
            "where ATTRN_ID = 11;" +
            "tupdate ATTRIBUTES set ATTRN_ID=12, OBJECT_ID=?, VALUE=?" +
            "twhere ATTRN_ID = 12;" +
            "end;";
    String ADD_PROJECT_DEVS = "INSERT INTO OBJREFERENCE (ATTRN_ID,REFERENCE, OBJECT_ID)VALUES(26,?,?);";
    String UPDATE_PROJECT_ISSUES = "update  ISSUES set PROJECT_ID = ? where ISSUE_ID =?;";

    String READ_PROJECT_BY_ID = "SELECT proj.NAME, pmid.OBJECT_ID, proj.OBJECT_ID, tmid.OBJECT_ID, proj_create_date.DATE_VALUE, status_value.VALUE, rep_name.VALUE," +
            "git_login.VALUE, git_pass.VALUE, jira_login.VALUE, jira_pas.VALUE" +
            "from OBJECTS proj, OBJECTS pmid, OBJECTS tmid, OBJREFERENCE pm_to_proj, OBJREFERENCE tm_to_proj, ATTRIBUTES attr_pm_rol," +
            "ATTRIBUTES attr_tm_rol, ATTRIBUTES proj_create_date, ATTRIBUTES proj_status, LISTS status_value, ATTRIBUTES rep_name," +
            "ATTRIBUTES git_login, ATTRIBUTES git_pass, ATTRIBUTES jira_login, ATTRIBUTES jira_pas" +
            "WHERE proj.OBJECT_ID = ?" +
            "and pm_to_proj.OBJECT_ID = proj.OBJECT_ID" +
            "and pmid.OBJECT_ID = pm_to_proj.REFERENCE" +
            "and attr_pm_rol.OBJECT_ID = pmid.OBJECT_ID" +
            "and attr_pm_rol.LIST_VALUE_ID = 1" +
            "and tm_to_proj.OBJECT_ID = proj.OBJECT_ID" +
            "and tmid.OBJECT_ID = tm_to_proj.REFERENCE" +
            "and attr_tm_rol.OBJECT_ID = tmid.OBJECT_ID" +
            "and attr_tm_rol.LIST_VALUE_ID = 2" +
            "and proj_create_date.OBJECT_ID = proj.OBJECT_ID" +
            "and proj_create_date.ATTRN_ID = 5" +
            "and proj_status.OBJECT_ID = proj.OBJECT_ID" +
            "and proj_status.ATTRN_ID = 7" +
            "and status_value.LIST_VALUE_ID = proj_status.LIST_VALUE_ID" +
            "and rep_name.OBJECT_ID = proj.OBJECT_ID" +
            "and rep_name.ATTRN_ID = 13" +
            "and git_login.OBJECT_ID = proj.OBJECT_ID" +
            "and git_login.ATTRN_ID = 9" +
            "and git_pass.OBJECT_ID = proj.OBJECT_ID" +
            "and git_pass.ATTRN_ID = 10" +
            "and jira_login.OBJECT_ID = proj.OBJECT_ID" +
            "and jira_login.ATTRN_ID = 11" +
            "and jira_pas.OBJECT_ID = proj.OBJECT_ID" +
            "and jira_pas.ATTRN_ID = 12;";

    String READ_PROJECT_BY_NAME = "SELECT proj.NAME, pmid.OBJECT_ID, proj.OBJECT_ID, tmid.OBJECT_ID, proj_create_date.DATE_VALUE, status_value.VALUE, rep_name.VALUE," +
            "git_login.VALUE, git_pass.VALUE, jira_login.VALUE, jira_pas.VALUE" +
            "from OBJECTS proj, OBJECTS pmid, OBJECTS tmid, OBJREFERENCE pm_to_proj, OBJREFERENCE tm_to_proj, ATTRIBUTES attr_pm_rol," +
            "ATTRIBUTES attr_tm_rol, ATTRIBUTES proj_create_date, ATTRIBUTES proj_status, LISTS status_value, ATTRIBUTES rep_name," +
            "ATTRIBUTES git_login, ATTRIBUTES git_pass, ATTRIBUTES jira_login, ATTRIBUTES jira_pas" +
            "WHERE proj.NAME = ?" +
            "and pm_to_proj.OBJECT_ID = proj.OBJECT_ID" +
            "and pmid.OBJECT_ID = pm_to_proj.REFERENCE" +
            "and attr_pm_rol.OBJECT_ID = pmid.OBJECT_ID" +
            "and attr_pm_rol.LIST_VALUE_ID = 1" +
            "and tm_to_proj.OBJECT_ID = proj.OBJECT_ID" +
            "and tmid.OBJECT_ID = tm_to_proj.REFERENCE" +
            "and attr_tm_rol.OBJECT_ID = tmid.OBJECT_ID" +
            "and attr_tm_rol.LIST_VALUE_ID = 2" +
            "and proj_create_date.OBJECT_ID = proj.OBJECT_ID" +
            "and proj_create_date.ATTRN_ID = 5" +
            "and proj_status.OBJECT_ID = proj.OBJECT_ID" +
            "and proj_status.ATTRN_ID = 7" +
            "and status_value.LIST_VALUE_ID = proj_status.LIST_VALUE_ID" +
            "and rep_name.OBJECT_ID = proj.OBJECT_ID" +
            "and rep_name.ATTRN_ID = 13" +
            "and git_login.OBJECT_ID = proj.OBJECT_ID" +
            "and git_login.ATTRN_ID = 9" +
            "and git_pass.OBJECT_ID = proj.OBJECT_ID" +
            "and git_pass.ATTRN_ID = 10" +
            "and jira_login.OBJECT_ID = proj.OBJECT_ID" +
            "and jira_login.ATTRN_ID = 11" +
            "and jira_pas.OBJECT_ID = proj.OBJECT_ID" +
            "and jira_pas.ATTRN_ID = 12;";



}
