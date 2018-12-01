package com.devstr.services.impl;

import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import com.devstr.model.impl.CommitClassImpl;
import com.devstr.services.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitServiceImpl implements GitService {

    private String repositoryName;
    private String token;


    public GitServiceImpl(String repositoryName, String token) {
        this.repositoryName = repositoryName;
        this.token = token;
    }

    @Override
    public GHRepository getGHRepository() throws IOException {
        return GitHub.connectUsingOAuth(token).getRepository(repositoryName);
    }

    @Override
    public GHCommit getLastGHCommit() throws IOException {
        return getGHRepository().listCommits().asList().get(0);
    }

    @Override
    public GHCommit getGHCommit(String commitSHA) throws IOException {
        return getGHRepository().getCommit(commitSHA);
    }

    @Override
    public List<CommitClass> getAllCommitClasses(GHCommit commit) throws IOException {

        List<CommitClass> commitClasses = new ArrayList<>();
        List<GHCommit.File> files = getLastGHCommit().getFiles();

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
    public GHUser getCommiter(GHCommit commit) {
        return null;
    }

    @Override
    public Commit getCommit() {
        return null;
    }
}
