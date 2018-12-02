package com.devstr.dao;

import com.devstr.dao.impl.ProjectDAOImpl;
import com.devstr.model.Project;
import com.devstr.model.impl.ProjectImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectDAOImplTest {

    @Autowired
    ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Ignore
    @Test
    public void createProjectTest() {
        Project project = new ProjectImpl.Builder("DEVSTR", BigInteger.valueOf(78)).build();
        BigInteger id = projectDAO.createProject(project.getProjectName(), project.getProjectManagerId());
        assertEquals(BigInteger.valueOf(92L), id);
    }

    @Test
    public void updateProjectRepositoryName() {
        projectDAO.updateProjectRepositoryName(BigInteger.valueOf(81L), "devstr");
    }

    @Test
    public void updateProjectGitLogin() {
        projectDAO.updateProjectGitLogin(BigInteger.valueOf(81L), "git login");
    }

    @Test
    public void updateProjectGitPassword() {
        projectDAO.updateProjectGitPassword(BigInteger.valueOf(81L), "git pass");
    }

    @Test
    public void updateProjectJiraLogin() {
        projectDAO.updateProjectJiraLogin(BigInteger.valueOf(81L), "jira login");
    }

    @Test
    public void updateProjectJiraPassword() {
        projectDAO.updateProjectJiraPassword(BigInteger.valueOf(81L), "jira pass");
    }

    @Test
    public void addDevOnProject() {
        BigInteger projID = BigInteger.valueOf(81);
        BigInteger devID = BigInteger.valueOf(80);
        projectDAO.addDevOnProject(projID, devID);
    }


    @Test
    public void readProjectById() {
        String projName = "DEVSTR";
        BigInteger projid = BigInteger.valueOf(81L);

        Project project = projectDAO.readProjectById(projid);
        assertEquals(projName, project.getProjectName());
    }

    @Ignore
    @Test
    public void readProjectByName() {
        String projName = "DEVSTR";
        BigInteger projid = BigInteger.valueOf(81L);

        Project project = projectDAO.readProjectByName(projName);
        assertEquals(projid, project.getProjectId());
    }

    @Test
    public void addIssueOnProject() {
        projectDAO.addIssueOnProject(BigInteger.valueOf(93), BigInteger.valueOf(89));
    }
}
