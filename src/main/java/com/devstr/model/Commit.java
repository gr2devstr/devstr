package com.devstr.model;

import com.devstr.model.enumerations.BuildStatus;
import org.kohsuke.github.GHCommit;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface Commit {
    BigInteger getCommitId();

    BigInteger getUserId();

    String getSha();

    Date getDate();

    Collection<GHCommit.File> getCommitClasses();

    BuildStatus getBuildStatus();

    void setCommitClasses(List<GHCommit.File> commitClasses);
}
