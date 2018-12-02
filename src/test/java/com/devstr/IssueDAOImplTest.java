package com.devstr;

import com.devstr.dao.impl.IssueDAOImpl;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueDAOImplTest {

    @Autowired
    IssueDAOImpl issueDAO;

    private Issue issue;

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        issue = new IssueImpl.Builder()
                .setIssueId(BigInteger.valueOf(242))
                .setIssueKey("ker")
                .setIssueType(IssueType.TASK)
                .setIssuePriority(IssuePriority.HIGH)
                .setIssueStatus(IssueStatus.OPEN)
                .setStartDate(new Date())
                .setDueDate(new Date())
                .setProjectId(BigInteger.valueOf(81))
                .setReporterId(BigInteger.valueOf(78))
                .setUserId(BigInteger.valueOf(76))
                .build();

    }
    @Ignore
    @Test
    public void createIssueTest(){
        issueDAO.createIssue(issue);
    }

    @Test
    public void getIssuesByProjectTest(){
        List<Issue> issues = issueDAO.readIssuesByProject(BigInteger.valueOf(81));

        Assert.assertEquals(issues.get(issues.size()-1).getIssueKey(),issue.getIssueKey());
    }

    @Test
    public void getIssuesByUserTest(){
        List<Issue> issues = issueDAO.readIssuesByUser(BigInteger.valueOf(76));

        Assert.assertEquals(issues.get(issues.size()-1).getIssueKey(),issue.getIssueKey());
    }

    @Test
    public void getIssueByIdTest(){
        Issue issue1 = issueDAO.readIssueById(BigInteger.valueOf(242));

        Assert.assertEquals(issue1.getIssueKey(),issue.getIssueKey());
    }

}
