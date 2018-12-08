package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.impl.UserDAOImpl;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.impl.CommitClassImpl;
import com.devstr.model.impl.CommitImpl;
import com.devstr.services.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(GitServiceImpl.class.getName());

    private String repositoryName;
    private String token;

    private List<GHCommit> commitList;

    @Autowired
    private UserDAOImpl userDAO;

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void getAllGHCommits() throws IOException {
        LOGGER.info("getAllGHCommits start");
        if (repositoryName != null && token != null) {
            commitList = GitHub.connectUsingOAuth(token).getRepository(repositoryName).listCommits().asList();
        }
        LOGGER.info("getAllGHCommits end");
    }


//    @Override
//    public List<GHCommit> getAllGHCommits() throws IOException {
//        return getGHRepository().listCommits().asList();
//    }

    @Override
    public List<CommitClass> getClassesFromCommit(GHCommit commit) throws IOException {
        LOGGER.info("getClassesFromCommit start");
        List<CommitClass> commitClasses = new ArrayList<>();
        List<GHCommit.File> files = commit.getFiles();
        for (GHCommit.File file : files) {
            commitClasses.add(new CommitClassImpl.CommitClassBuilder()
                    .setClassName(file.getFileName())
                    .setAddedLines(file.getLinesAdded())
                    .setChangedLines(file.getLinesChanged())
                    .setDeletedLines(file.getLinesDeleted())
                    .build());
        }
        LOGGER.info("getClassesFromCommit end");
        return commitClasses;
    }

    @Override
    public Commit getCommit(GHCommit commit) throws IOException {
        LOGGER.info("getCommit start");
        BigInteger userID;
        BuildStatus status;

        if (commit.getCommitter().getEmail() != null && getBuildStatus(commit) != null) {
            LOGGER.info("getCommit get user id by email start");
            userID = userDAO.readUserIdByEmail(commit.getCommitter().getEmail());
            LOGGER.info("getCommit get user id by email end");
            status = getBuildStatus(commit);

            LOGGER.info("getCommit end");

            return new CommitImpl.CommitBuilder()
                    .setUserId(userID)
                    .setSha(commit.getSHA1())
                    .setDate(commit.getCommitDate())
                    .setCommitClasses(getClassesFromCommit(commit))
                    .setBuildStatus(status)
                    .build();

        }

        return null;
    }


    @Override
    public List<Commit> getAllCommits() throws IOException {
        LOGGER.info("getAllCommits start");
        getAllGHCommits();
        List<Commit> commits = new ArrayList<>();
        //PagedIterable<GHCommit> ghcommits = getGHRepository().listCommits();
        for (GHCommit commit : commitList) {
            commits.add(getCommit(commit));
        }
        LOGGER.info("getAllCommits end");
        return commits;
    }

    @Override
    public List<Commit> getCommitsByIssueKey(String issueKey) throws IOException {
        getAllGHCommits();
        List<Commit> commits = new ArrayList<>();
        //PagedIterable<GHCommit> ghcommits = getGHRepository().listCommits();
        for (GHCommit commit : commitList) {
            if (commit.getCommitShortInfo().getMessage().contains(issueKey)) {
                commits.add(getCommit(commit));
            }
        }
        return commits;
    }

    private BuildStatus getBuildStatus(GHCommit commit) throws IOException {
        String currentStatus;

        if (commit.getLastStatus() != null) {
            currentStatus = commit.getLastStatus().getState().toString();
        } else return null;

        if (currentStatus.equals(BuildStatus.SUCCESS.toString())) return BuildStatus.SUCCESS;
        if (currentStatus.equals(BuildStatus.FAILURE.toString())) return BuildStatus.FAILURE;
        return BuildStatus.PENDING;
    }
}
