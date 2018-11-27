package com.devstr.model;

import com.devstr.model.impl.CommitClassImpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

public interface Commit {
    BigInteger getCommitId();

    BigInteger getUserId();

    String getSha();

    Date getDate();

    Set<CommitClassImpl> getCommitClasses();

    boolean getBuildStatus();

    String getBuildSha();

    void setCommitClass(CommitClassImpl commitClass);

   void setCommitClasses(Set<CommitClassImpl> commitClasses);
}
