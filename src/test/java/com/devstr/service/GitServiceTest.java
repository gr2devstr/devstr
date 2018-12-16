package com.devstr.service;

import com.devstr.model.Commit;
import com.devstr.services.GitService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GitServiceTest {


    @Autowired
    private GitService gitService;



    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        gitService.setRepositoryName("gr2devstr/devstr");
        gitService.setToken("7d2a8630b9b5a9946c809c3f02149ba5ef2b2050");
//        gitService.setRepositoryName("19Robert99/patchwork");
//        gitService.setToken("10107a471e4b8fda374e61501839bb13ff607230 ");
    }

    @Test
    public void getCommitTest() throws IOException {
        List<Commit> commits = gitService.getCommitsByIssueKey();
        //List<Commit> commits = gitService.getAllCommits();

        //assertEquals(bigInteger, BigInteger.valueOf(549L));
    }
}
