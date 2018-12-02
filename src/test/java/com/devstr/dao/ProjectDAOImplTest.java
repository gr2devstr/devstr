package com.devstr.dao;

import com.devstr.dao.impl.ProjectDAOImpl;
import com.devstr.model.Project;
import com.devstr.model.impl.ProjectImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
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
        Project project = new ProjectImpl.Builder("DEVSTR", BigInteger.valueOf(78)).build();
        BigInteger id = projectDAO.createProject(project.getProjectName(), project.getProjectManagerId());
        //assertEquals(BigInteger.valueOf(92L),id);
    }

    @Test
    public void updateProjectRepositoryName() {
        projectDAO.updateProjectRepositoryName(BigInteger.valueOf(81L), "TestProjDaoRepoName");
    }

    @Test
    public void readProjectById() {
        Project project = projectDAO.readProjectById(BigInteger.valueOf(81L));
        assertEquals("DEVSTR", project.getProjectName());
    }

    @Test
    public void addIssueOnProject() {
        projectDAO.addIssueOnProject(BigInteger.valueOf(93), BigInteger.valueOf(89));
    }
}
