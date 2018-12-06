package com.devstr.dao;

import com.devstr.dao.impl.IssueDAOImpl;
import com.devstr.model.Commit;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.CommitImpl;
import com.devstr.model.impl.IssueImpl;
import org.junit.*;
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
    private  Issue updatedIssue;

    @BeforeClass
    public static void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Before
    public void setIssue(){
        issue = new IssueImpl.IssueBuilder()
                .setIssueId(BigInteger.valueOf(769))
                .setIssueKey("kee")
                .setIssueType(IssueType.BUG)
                .setIssuePriority(IssuePriority.HIGH)
                .setIssueStatus(IssueStatus.OPEN)
                .setStartDate(new Date())
                .setDueDate(new Date())
                .setProjectId(BigInteger.valueOf(89))
                .setReporterId(BigInteger.valueOf(84))
                .setUserId(BigInteger.valueOf(85))
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
        issueDAO.updateIssueUser(id,BigInteger.valueOf(86));
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

    @Test
    public void createCommitTest(){
        Commit commit1 = new CommitImpl.CommitBuilder()
                .setUserId(BigInteger.valueOf(85L))
                .setSha("sha5")
                .setDate(new Date())
                .setBuildStatus(BuildStatus.SUCCESS)
                .build();
        Commit commit2 = new CommitImpl.CommitBuilder()
                .setUserId(BigInteger.valueOf(85L))
                .setSha("sha6")
                .setDate(new Date())
                .setBuildStatus(BuildStatus.SUCCESS)
                .build();
//        List<Commit> commits = new ArrayList<>();
//        commits.add(commit1);
//        commits.add(commit2);
        BigInteger issueId = BigInteger.valueOf(108L);

//        issueDAO.createCommits(commits,issueId);
        List<Commit> commitsFromDB =  issueDAO.readCommitsByIssue(issueId);
        Assert.assertEquals(commit2.getSha(),commitsFromDB.get(commitsFromDB.size()-1).getSha());
    }


}
