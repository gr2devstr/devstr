package com.devstr.services;

import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import java.io.IOException;
import java.util.List;

public interface GitService {

    GHRepository getGHRepository() throws IOException;

    GHCommit getLastGHCommit() throws IOException;

    GHCommit getGHCommit(String commitSHA) throws IOException;

    List<CommitClass> getAllCommitClasses(GHCommit commit) throws IOException;

    GHUser getCommiter(GHCommit commit);

    public Commit getCommit();
}
