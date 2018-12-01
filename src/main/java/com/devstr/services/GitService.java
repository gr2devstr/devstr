package com.devstr.services;

import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public interface GitService {

    GHRepository getGHRepository() throws IOException;

    List<GHCommit> getAllGHCommits() throws IOException;

    List<CommitClass> getClassesFromCommit(GHCommit commit) throws IOException;

    Commit getCommit(GHCommit commit) throws IOException;

    List<Commit> getAllCommits() throws IOException;

}
