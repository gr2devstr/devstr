package com.devstr.model.impl;

import com.devstr.model.CommitClass;

public class CommitClassImpl implements CommitClass {

    private String className;
    private int numberOfLinesAdded;
    private int numberOfLinesChanged;
    private int numberOfLinesDeleted;

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

    public static class CommitClassBuilder {

        private String className;
        private int numberOfLinesAdded;
        private int numberOfLinesChanged;
        private int numberOfLinesDeleted;

        public CommitClassBuilder(){
        }

        public CommitClassBuilder setClassName(String name) {
            this.className = name;
            return this;
        }

        public CommitClassBuilder setAddedLines(int number) {
            this.numberOfLinesAdded = number;
            return this;
        }

        public CommitClassBuilder setChangedLines(int number) {
            this.numberOfLinesChanged = number;
            return this;
        }

        public CommitClassBuilder setDeletedLines(int number) {
            this.numberOfLinesDeleted = number;
            return this;
        }

        public CommitClass build() {
            return new CommitClassImpl(this);
        }
    }

    private CommitClassImpl(CommitClassBuilder builder){
        this.className = builder.className;
        this.numberOfLinesAdded = builder.numberOfLinesAdded;
        this.numberOfLinesChanged = builder.numberOfLinesChanged;
        this.numberOfLinesDeleted = builder.numberOfLinesChanged;
    }

}
