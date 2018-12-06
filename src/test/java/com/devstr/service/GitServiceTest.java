package com.devstr.service;

import com.devstr.model.Commit;
import com.devstr.services.impl.GitServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GitServiceTest {


    @Autowired
    private GitServiceImpl gitService;


    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        gitService.setRepositoryName("gr2devstr/devstr");
        gitService.setToken("7d60ff6e79d4f39a953f9f31467a95d9dac5f738");
    }

    @Test
    public void getCommitTest() throws IOException {
        //List<Commit> commits = gitService.getCommitsByIssueKey("DEVSTR-31");
        List<Commit> commits = gitService.getAllCommits();
        assertEquals(BigInteger.valueOf(79L), commits.get(0).getUserId());
    }
}
