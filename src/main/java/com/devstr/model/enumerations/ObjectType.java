package com.devstr.model.enumerations;

public enum ObjectType {
    USER(1),
    PROJECT(2),
    REVIEW(3),
    USER_REVIEW(4),
    PROJECT_REVIEW(5),
    TOKEN(6);

    private int objectTypeId;

    private ObjectType(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public int getObjectTypeId() {
        return objectTypeId;
    }
}
