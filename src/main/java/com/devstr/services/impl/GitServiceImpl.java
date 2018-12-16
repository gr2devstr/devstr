package com.devstr.services.impl;

import com.devstr.DevstrFactoryManager;
import com.devstr.dao.IssueDAO;
import com.devstr.dao.impl.UserDAOImpl;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Commit;
import com.devstr.model.enumerations.BuildStatus;
import com.devstr.model.impl.CommitImpl;
import com.devstr.services.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GitServiceImpl implements GitService {

    private static DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory().getLogger(GitServiceImpl.class.getName());

    private String repositoryName;
    private String token;

    private Map<String, BigInteger> map;
    private Set<String> keys;

    private Collection<GHCommit> ghCommits;

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private IssueDAO issueDAO;


    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public GHRepository getGHRepository() throws IOException {
        LOGGER.info("Start");
        if (repositoryName != null && token != null) {
            return GitHub.connectUsingOAuth(token).getRepository(repositoryName);
        }
        return null;
    }


    @Override
    public Collection<GHCommit> getAllGHCommits() throws IOException {
        LOGGER.info("Start");
        if (repositoryName != null && token != null) {
            return GitHub.connectUsingOAuth(token).getRepository(repositoryName).listCommits().asList();
        }
        return null;
    }

    @Override
    public Commit getCommit(GHCommit commit) {
        BigInteger userID;
        BuildStatus status;
        BigInteger issueId = null;

        try {
            status = getBuildStatus(commit);
            String info = commit.getCommitShortInfo().getMessage();

            for (Map.Entry<String, BigInteger> entry : map.entrySet()) {
                String key = entry.getKey();
                if (info.contains(key)) {
                    LOGGER.info("---------------------------" + key);
                    issueId = entry.getValue();
                    LOGGER.info("---------------------------" + issueId);
                    break;
                }

            }

            if (commit.getCommitter().getEmail() != null && status != null && issueId != null) {
                userID = userDAO.readUserIdByEmail(commit.getCommitter().getEmail());

                return new CommitImpl.CommitBuilder()
                        .setIssueId(issueId)
                        .setUserId(userID)
                        .setSha(commit.getSHA1())
                        .setDate(commit.getCommitDate())
                        .setCommitClasses(commit.getFiles())
                        .setBuildStatus(status)
                        .build();

            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }


    @Deprecated
    @Override
    public List<Commit> getAllCommits() throws IOException {
        map = issueDAO.readAllIssuesKey();

        Collection<GHCommit> ghCommits = getAllGHCommits();
        List<Commit> commits = null;
        if (ghCommits != null) {
            LOGGER.info("Commits :" + ghCommits.size());
            commits = new ArrayList<>();
            commits.addAll(ghCommits.stream().parallel().map(this::getCommit).filter(Objects::nonNull).collect(Collectors.toList()));
            LOGGER.info("End");
        }
        return commits;
    }

    @Override
    public List<Commit> getCommitsByIssueKey() throws IOException {

        map = issueDAO.readAllIssuesKey();

        LOGGER.info(map.entrySet());
        Date date = issueDAO.getDateLastCommitOnProject();
        if (date != null) ghCommits = getGHRepository().queryCommits().since(date).list().asList();
        else ghCommits = getAllGHCommits();

        List<Commit> commits = null;
        if (ghCommits != null) {
            LOGGER.info("Commits :" + ghCommits.size());
            commits = new ArrayList<>();
            commits.addAll(ghCommits.stream().parallel().map(this::getCommit).filter(Objects::nonNull).collect(Collectors.toList()));
            LOGGER.info("End");
        }


        writeToDataBase(commits);
        return commits;
    }

    @Override
    public void writeToDataBase(Collection<Commit> commits) {
        issueDAO.createCommits((List<Commit>) commits);
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
