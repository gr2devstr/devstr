package com.devstr.dao;

import com.devstr.dao.impl.ProjectDAOImpl;
import com.devstr.model.Project;
import com.devstr.model.enumerations.AttributeID;
import com.devstr.model.enumerations.ObjectType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectDAOImplTest {

    @Autowired
    ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }


    @Test
    public void createProjectTest() {

        String projectName = "DEVSTR_TEST";
        BigInteger pmID = BigInteger.valueOf(78L);

        BigInteger idCreate = projectDAO.createProject(projectName, pmID);
        BigInteger attrTypeId = ObjectType.PROJECT.getId();
        BigInteger getID = projectDAO.readObjectIdByName(attrTypeId, projectName);

        assertEquals(getID, idCreate);

        projectDAO.deleteObjectById(idCreate);
    }

    @Test
    public void updateProjectRepositoryNameTest() {
        String value = "devstr";
        BigInteger projectID = BigInteger.valueOf(81L);

        projectDAO.updateProjectRepositoryName(projectID, value);

        BigInteger attrnId = AttributeID.REPOSITORY_NAME.getId();

        String getValue = projectDAO.readAttributeValue(attrnId, projectID);

        assertEquals(value, getValue);
    }

    @Test
    public void updateProjectGitLoginTest() {
        String value = "git login";
        BigInteger projectID = BigInteger.valueOf(81L);

        projectDAO.updateProjectGitLogin(projectID, value);

        BigInteger attrnId = AttributeID.GIT_LOGIN.getId();

        String getValue = projectDAO.readAttributeValue(attrnId, projectID);

        assertEquals(value, getValue);
    }

    @Test
    public void updateProjectGitPasswordTest() {
        String value = "git pass";
        BigInteger projectID = BigInteger.valueOf(81L);

        projectDAO.updateProjectGitPassword(projectID, value);

        BigInteger attrnId = AttributeID.GIT_PASSWORD.getId();

        String getValue = projectDAO.readAttributeValue(attrnId, projectID);

        assertEquals(value, getValue);
    }

    @Test
    public void updateProjectJiraLoginTest() {
        String value = "jira login";
        BigInteger projectID = BigInteger.valueOf(81L);

        projectDAO.updateProjectJiraLogin(projectID, value);

        BigInteger attrnId = AttributeID.JIRA_LOGIN.getId();

        String getValue = projectDAO.readAttributeValue(attrnId, projectID);

        assertEquals(value, getValue);
    }

    @Test
    public void updateProjectJiraPasswordTest() {
        String value = "jira pass";
        BigInteger projectID = BigInteger.valueOf(81L);

        projectDAO.updateProjectJiraPassword(projectID, value);

        BigInteger attrnId = AttributeID.JIRA_PASSWORD.getId();

        String getValue = projectDAO.readAttributeValue(attrnId, projectID);

        assertEquals(value, getValue);
    }

    @Test
    public void addDevOnProject() {
        boolean defaulValue = true;

        BigInteger projID = BigInteger.valueOf(81L);
        BigInteger devID = BigInteger.valueOf(76L);
        projectDAO.addDevOnProject(projID, devID);

        Collection<BigInteger> ids = projectDAO.readObjectReferences(AttributeID.PROJECT_USERS.getId(), projID);

        boolean newValue = ids.contains(devID);

        assertEquals(defaulValue, newValue);

        projectDAO.deleteObjectReference(AttributeID.PROJECT_USERS.getId(), projID, devID);
    }

    @Ignore
    @Test
    public void deactivateUserOnProjectTest() {
        BigInteger user_id = BigInteger.valueOf(80L);
        projectDAO.deactivateUserOnProject(user_id);
    }


    @Test
    public void readProjectByIdTest() {
        String projName = "DEVSTR";
        BigInteger projid = BigInteger.valueOf(81L);

        Project project = projectDAO.readProjectById(projid);
        assertEquals(projName, project.getProjectName());
    }

    @Ignore
    @Test
    public void readProjectByNameTest() {
        String projName = "DEVSTR";
        BigInteger projid = BigInteger.valueOf(81L);

        Project project = projectDAO.readProjectByName(projName);
        assertEquals(projid, project.getProjectId());
    }


    @Test
    public void addIssueOnProjectTest() {
        projectDAO.addIssueOnProject(BigInteger.valueOf(93), BigInteger.valueOf(89));
    }
}
