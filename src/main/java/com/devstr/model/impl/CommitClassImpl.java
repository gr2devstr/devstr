package com.devstr.model.impl;

import com.devstr.model.CommitClass;

public class CommitClassImpl implements CommitClass {

    private String className;
    private int numberOfLinesAdded;
    private int numberOfLinesChanged;
    private int numberOfLinesDeleted;

    private CommitClassImpl() {
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public int getNumberOfLinesAdded() {
        return numberOfLinesAdded;
    }

    @Override
    public int getNumberOfLinesChanged() {
        return numberOfLinesChanged;
    }

    @Override
    public int getNumberOfLinesDeleted() {
        return numberOfLinesDeleted;
    }

    public static CommitClassBuilder builder() {
        return new CommitClassImpl().new CommitClassBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitClassImpl that = (CommitClassImpl) o;

        if (getNumberOfLinesAdded() != that.getNumberOfLinesAdded()) return false;
        if (getNumberOfLinesChanged() != that.getNumberOfLinesChanged()) return false;
        if (getNumberOfLinesDeleted() != that.getNumberOfLinesDeleted()) return false;
        return getClassName() != null ? getClassName().equals(that.getClassName()) : that.getClassName() == null;
    }

    @Override
    public int hashCode() {
        int result = getClassName() != null ? getClassName().hashCode() : 0;
        result = 31 * result + getNumberOfLinesAdded();
        result = 31 * result + getNumberOfLinesChanged();
        result = 31 * result + getNumberOfLinesDeleted();
        return result;
    }

    public class CommitClassBuilder {

        private CommitClassBuilder(){
        }

        public CommitClassBuilder setClassName(String name) {
            CommitClassImpl.this.className = name;
            return this;
        }

        public CommitClassBuilder setAddedLines(int number) {
            CommitClassImpl.this.numberOfLinesAdded = number;
            return this;
        }

        public CommitClassBuilder setChangedLines(int number) {
            CommitClassImpl.this.numberOfLinesChanged = number;
            return this;
        }

        public CommitClassBuilder setDeletedLines(int number) {
            CommitClassImpl.this.numberOfLinesDeleted = number;
            return this;
        }

        public CommitClassImpl build() {
            return CommitClassImpl.this;
        }

    }

}
