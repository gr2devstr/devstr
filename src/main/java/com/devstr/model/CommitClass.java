package com.devstr.model;

public interface CommitClass {
    String getClassName();

    int getNumberOfLinesAdded();

    int getNumberOfLinesChanged();

    int getNumberOfLinesDeleted();
}
