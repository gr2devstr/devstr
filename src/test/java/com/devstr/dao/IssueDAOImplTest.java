package com.devstr.dao;

import com.devstr.dao.impl.IssueDAOImpl;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
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
    private  Issue updatedIssue;

    @BeforeClass
    public static void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Before
    public void setIssue(){
        issue = new IssueImpl.Builder()
                .setIssueId(BigInteger.valueOf(769))
                .setIssueKey("kes")
                .setIssueType(IssueType.BUG)
                .setIssuePriority(IssuePriority.HIGH)
                .setIssueStatus(IssueStatus.OPEN)
                .setStartDate(new Date())
                .setDueDate(new Date())
                .setProjectId(BigInteger.valueOf(81))
                .setReporterId(BigInteger.valueOf(78))
                .setUserId(BigInteger.valueOf(76))
                .build();
        issueDAO.createIssue(issue);
        List<Issue> issues = issueDAO.readIssuesByProject(issue.getProjectId());
        updatedIssue = issues.get(issues.size()-1);
    }

    @After
    public void deleteIssue(){
        issueDAO.deleteIssueById(updatedIssue.getIssueId());
    }

    @Ignore
    @Test
    public void createIssueTest() throws Exception {
        issueDAO.createIssue(issue);
    }

    @Test
    public void updateIssueTest() {
        List<Issue> issues = issueDAO.readIssuesByProject(issue.getProjectId());
        BigInteger id = issues.get(issues.size()-1).getIssueId();
        issueDAO.updateIssueType(id,IssueType.EPIC);
        issueDAO.updateIssueStatus(id,IssueStatus.READY_FOR_TESTING);
        issueDAO.updateIssuePriority(id,IssuePriority.BLOCKER);
        issueDAO.updateIssueUser(id,BigInteger.valueOf(80));
        updatedIssue = issueDAO.readIssueById(id);
        Assert.assertNotEquals(updatedIssue.getType(),issue.getType());
        Assert.assertNotEquals(updatedIssue.getStatus(),issue.getStatus());
        Assert.assertNotEquals(updatedIssue.getPriority(),issue.getPriority());
        Assert.assertNotEquals(updatedIssue.getUserId(),issue.getUserId());

    }

    @Test
    public void getIssuesByProjectTest(){
        List<Issue> issues = issueDAO.readIssuesByProject(updatedIssue.getProjectId());

        Assert.assertEquals(issues.get(issues.size()-1).getIssueKey(),updatedIssue.getIssueKey());
    }

    @Test
    public void getIssuesByUserTest(){
        List<Issue> issues = issueDAO.readIssuesByUser(updatedIssue.getUserId());

        Assert.assertEquals(issues.get(issues.size()-1).getIssueKey(),updatedIssue.getIssueKey());
    }

    @Test
    public void getIssueByIdTest(){
        Issue issue1 = issueDAO.readIssueById(updatedIssue.getIssueId());

        Assert.assertEquals(issue1.getIssueKey(),issue.getIssueKey());
    }


}
