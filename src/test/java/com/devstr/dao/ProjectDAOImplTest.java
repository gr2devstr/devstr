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
    @Test
    public void createProjectTest() {
        Project project = new ProjectImpl.Builder("test", BigInteger.valueOf(3)).build();
        projectDAO.createProject(project.getProjectName(), project.getProjectManagerId());
    }
}
