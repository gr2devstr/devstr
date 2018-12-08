package com.devstr.services;

import com.devstr.model.Commit;
import com.devstr.model.CommitClass;
import org.kohsuke.github.GHCommit;

import java.io.IOException;
import java.util.List;

public interface GitService {


    /**
     * @param repositoryName
     */
    void setRepositoryName(String repositoryName);

    /**
     * @param token
     */
    void setToken(String token);

    /**
     * The method creates a GHCommits object
     *
     */
    void getAllGHCommits() throws IOException;

    /**
     * The method returns a list of classes from commit
     *
     * @param commit ghcommit
     * @return class list in ghcommit
     * @throws IOException
     */
    List<CommitClass> getClassesFromCommit(GHCommit commit) throws IOException;

    /**
     * The method creates an instance of the Commit class
     *
     * @param commit ghcommit
     * @return Object Commit
     * @throws IOException
     */
    Commit getCommit(GHCommit commit) throws IOException;

    /**
     * The method returns a list of commit class objects
     *
     * @return commit list
     * @throws IOException
     */
    List<Commit> getAllCommits() throws IOException;


    /**
     * The method returns a list of commit class objects by issue key
     *
     * @param issueKey search issue key
     * @return commit list
     * @throws IOException
     */
    List<Commit> getCommitsByIssueKey(String issueKey) throws IOException;

}
