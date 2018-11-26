package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(1L)),
    IN_PROGRESS(BigInteger.valueOf(2L)),
    READY_FOR_TESTING(BigInteger.valueOf(3L)),
    CLOSED(BigInteger.valueOf(4L)),
    REOPEN(BigInteger.valueOf(5L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
