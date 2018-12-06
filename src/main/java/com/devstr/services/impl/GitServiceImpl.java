package com.devstr.services.impl;

import com.devstr.dao.impl.UserDAOImpl;
import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.impl.CommitClassImpl;
import com.devstr.model.impl.CommitImpl;
import com.devstr.services.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    private String repositoryName;
    private String token;

    @Autowired
    private UserDAOImpl userDAO;

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public GHRepository getGHRepository() throws IOException {
        return GitHub.connectUsingOAuth(token).getRepository(repositoryName);
    }

    @Override
    public List<GHCommit> getAllGHCommits() throws IOException {
        return getGHRepository().listCommits().asList();
    }

    @Override
    public List<CommitClass> getClassesFromCommit(GHCommit commit) throws IOException {
        List<CommitClass> commitClasses = new ArrayList<>();
        List<GHCommit.File> files = commit.getFiles();
        for (GHCommit.File file : files) {
            commitClasses.add(CommitClassImpl.builder()
                    .setClassName(file.getFileName())
                    .setAddedLines(file.getLinesAdded())
                    .setChangedLines(file.getLinesChanged())
                    .setDeletedLines(file.getLinesDeleted())
                    .build());
        }
        return commitClasses;
    }

    @Override
    public Commit getCommit(GHCommit commit) throws IOException {
        BigInteger userID;
        BuildStatus status;

        if (commit.getCommitter().getEmail() != null && getBuildStatus(commit) != null) {
            userID = userDAO.readUserIdByEmail(commit.getCommitter().getEmail());
            status = getBuildStatus(commit);
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
        List<Commit> commits = new ArrayList<>();
        PagedIterable<GHCommit> ghcommits = getGHRepository().listCommits();
        for (GHCommit commit : ghcommits) {
            commits.add(getCommit(commit));
        }
        return commits;
    }

    @Override
    public List<Commit> getCommitsByIssueKey(String issueKey) throws IOException {
        List<Commit> commits = new ArrayList<>();
        PagedIterable<GHCommit> ghcommits = getGHRepository().listCommits();
        for (GHCommit commit : ghcommits) {
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
