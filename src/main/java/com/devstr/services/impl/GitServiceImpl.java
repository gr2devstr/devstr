package com.devstr.services.impl;

import com.devstr.dao.impl.IssueDAOImpl;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitServiceImpl implements GitService {

    private String repositoryName;
    private String token;

    @Autowired
    IssueDAOImpl issueDAO;


    public GitServiceImpl(String repositoryName, String token) {
        this.repositoryName = repositoryName;
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
        BuildStatus status = getBuildStatus(commit);
        return CommitImpl.builder()
                //.setUserId()//some method in UserDaoImpl  some(commit.getCommitter().getEmail())
                .setSha(commit.getSHA1())
                .setDate(commit.getCommitDate())
                .setCommitClasses(getClassesFromCommit(commit))
                .setBuildStatus(status)
                .build();
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

    private BuildStatus getBuildStatus(GHCommit commit) throws IOException {
        String currentStatus = commit.getLastStatus().getState().toString();
        if (currentStatus.equals(BuildStatus.SUCCESS.toString())) return BuildStatus.SUCCESS;
        if (currentStatus.equals(BuildStatus.FAILURE.toString())) return BuildStatus.FAILURE;
        return BuildStatus.PENDING;
    }
}
