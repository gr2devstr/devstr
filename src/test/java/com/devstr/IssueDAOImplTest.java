package com.devstr;

import com.devstr.dao.IssueDAO;
import com.devstr.dao.impl.IssueDAOImpl;
import com.devstr.model.Issue;
import com.devstr.model.enumerations.IssuePriority;
import com.devstr.model.enumerations.IssueStatus;
import com.devstr.model.enumerations.IssueType;
import com.devstr.model.impl.IssueImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueDAOImplTest {

    @Autowired
    IssueDAOImpl issueDAO;

    @Test
    public void createIssueTest(){

        Issue issue = new IssueImpl.Builder()
                .setIssueKey("key")
                .setIssueType(IssueType.TASK)
                .setIssuePriority(IssuePriority.HIGH)
                .setIssueStatus(IssueStatus.OPEN)
                .setStartDate(new Date())
                .setDueDate(new Date(12312312))
                .setProjectId(BigInteger.valueOf(1))
                .setReporterId(BigInteger.valueOf(2))
                .setUserId(BigInteger.valueOf(3))
                .build();

        issueDAO.createIssue(issue);
    }
}
