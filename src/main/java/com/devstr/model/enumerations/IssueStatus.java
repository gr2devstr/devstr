package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(119L)),
    IN_PROGRESS(BigInteger.valueOf(120L)),
    READY_FOR_TESTING(BigInteger.valueOf(121L)),
    CLOSED(BigInteger.valueOf(122L)),
    REOPEN(BigInteger.valueOf(123L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
