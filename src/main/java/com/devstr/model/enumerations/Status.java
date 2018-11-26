package com.devstr.model.enumerations;

public enum Status {
    ACTIVE(5),
    INACTIVE(6);

    private int listValueId;

    private Status(int listValueId) {
        this.listValueId = listValueId;
    }

    public int getListValueId() {
        return listValueId;
    }
}
