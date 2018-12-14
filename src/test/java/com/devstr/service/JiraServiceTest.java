package com.devstr.service;

import com.devstr.services.impl.JiraServiceImpl;
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
public class JiraServiceTest {

    @Autowired
    JiraServiceImpl jiraService;

    @Before
    public void setUp() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        jiraService.setDomain("https://nc-std.atlassian.net/");
        jiraService.setLogin("robert.talabishka@gmail.com");
        jiraService.setPassword("Robert99!");
    }

    @Test
    public void getIssuesByProjectId() throws Exception {
        jiraService.updateIssues(BigInteger.valueOf(67L));
        //assertEquals("DEVSTR-1",issues.get(0).getIssueKey());
    }
}
