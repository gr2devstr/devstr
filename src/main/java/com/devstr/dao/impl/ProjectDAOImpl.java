package com.devstr.dao.impl;

import com.devstr.dao.ProjectDAO;
import com.devstr.model.Project;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;
import com.devstr.model.impl.ProjectImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Transactional
@Repository
public class ProjectDAOImpl extends AbstractDAOImpl implements ProjectDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigInteger createProject(String name, BigInteger managerId) {

        BigInteger objID = createObject(ObjectType.PROJECT.getId(), name);
        createObjectReference(AttributeID.PROJECT_USERS.getId(), managerId, objID);
        createAttributeDateValue(AttributeID.CREATION_DATE.getId(), objID, new java.util.Date());
        createAttributeListValue(AttributeID.STATUS.getId(), objID, Status.ACTIVE.getId());
        return objID;
    }

    @Override
    @Transactional
    public void updateProjectRepositoryName(BigInteger projectId, String repoName) {
        updateAttributeValue(AttributeID.REPOSITORY_NAME.getId(), projectId, repoName);
    }

    @Override
    @Transactional
    public void updateProjectGitLogin(BigInteger projectId, String gitLogin) {
        updateAttributeValue(AttributeID.GIT_LOGIN.getId(), projectId, gitLogin);
    }

    @Override
    @Transactional
    public void updateProjectGitPassword(BigInteger projectId, String gitPassword) {
        updateAttributeValue(AttributeID.GIT_PASSWORD.getId(), projectId, gitPassword);
    }

    @Override
    @Transactional
    public void updateProjectJiraLogin(BigInteger projectId, String jiraLogin) {
        updateAttributeValue(AttributeID.JIRA_LOGIN.getId(), projectId, jiraLogin);
    }

    @Override
    @Transactional
    public void updateProjectJiraPassword(BigInteger projectId, String jiraPassword) {
        updateAttributeValue(AttributeID.JIRA_PASSWORD.getId(), projectId, jiraPassword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDevOnProject(BigInteger projectId, BigInteger developerId) {
        createObjectReference(AttributeID.PROJECT_USERS.getId(), projectId, developerId);
        updateAttributeValue(AttributeID.USER_PROJECT.getId(), developerId, projectId.toString());
    }

    @Override
    @Transactional
    public void deactivateUserOnProject(BigInteger developerId) {
        updateAttributeValue(AttributeID.USER_PROJECT.getId(), developerId, "0");
    }

    @Override
    @Transactional
    public void addIssueOnProject(BigInteger projectId, BigInteger issueId) {
        jdbcTemplate.update(UPDATE_PROJECT_ISSUE, new Object[]{projectId.longValue(), issueId.longValue()});
    }

    @Override
    @Transactional
    public Project readProjectById(BigInteger projectID) {

        BigInteger pmid = getUserID(projectID, UserRole.PROJECT_MANAGER);
        BigInteger tmid = getUserID(projectID, UserRole.TECHNICAL_MANAGER);
        return new ProjectImpl.Builder(readObjectNameById(projectID), pmid)
                .setProjectId(projectID)
                .setTechnicalManagerId(tmid)
                .setFromDate(readAttributeDateValue(AttributeID.CREATION_DATE.getId(), projectID))
                .setStatus(Status.valueOf(readAttributeListValue(AttributeID.STATUS.getId(), projectID)))
                .setRepoName(readAttributeValue(AttributeID.REPOSITORY_NAME.getId(), projectID))
                .setGitLogin(readAttributeValue(AttributeID.GIT_LOGIN.getId(), projectID))
                .setGitPassword(readAttributeValue(AttributeID.GIT_PASSWORD.getId(), projectID))
                .setJiraLogin(readAttributeValue(AttributeID.JIRA_LOGIN.getId(), projectID))
                .setJiraPassword(readAttributeValue(AttributeID.JIRA_PASSWORD.getId(), projectID))
                .setJiraDomain(readAttributeValue(AttributeID.JIRA_DOMAIN.getId(), projectID))
                .build();
    }

    @Deprecated
    @Override
    @Transactional
    public Project readProjectByName(String projectName) {
        BigInteger projectID = readObjectIdByName(ObjectType.PROJECT.getId(), projectName);
        BigInteger pmid = getUserID(projectID, UserRole.PROJECT_MANAGER);
        BigInteger tmid = getUserID(projectID, UserRole.TECHNICAL_MANAGER);
        return new ProjectImpl.Builder(readObjectNameById(projectID), pmid)
                .setProjectId(projectID)
                .setTechnicalManagerId(tmid)
                .setFromDate(readAttributeDateValue(AttributeID.CREATION_DATE.getId(), projectID))
                .setStatus(Status.valueOf(readAttributeListValue(AttributeID.STATUS.getId(), projectID)))
                .setRepoName(readAttributeValue(AttributeID.REPOSITORY_NAME.getId(), projectID))
                .setGitLogin(readAttributeValue(AttributeID.GIT_LOGIN.getId(), projectID))
                .setGitPassword(readAttributeValue(AttributeID.GIT_PASSWORD.getId(), projectID))
                .setJiraLogin(readAttributeValue(AttributeID.JIRA_LOGIN.getId(), projectID))
                .setJiraPassword(readAttributeValue(AttributeID.JIRA_PASSWORD.getId(), projectID))
                .build();
    }

    private BigInteger getUserID(BigInteger projectID, UserRole role) {
        return jdbcTemplate.queryForObject(GET_USER_ID_BY_ROLE, new Object[]{projectID.longValue(), role.getId().longValue()}, BigInteger.class);
    }

}
