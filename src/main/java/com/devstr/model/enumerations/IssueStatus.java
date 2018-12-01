package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(14L)),
    IN_PROGRESS(BigInteger.valueOf(15L)),
    READY_FOR_TESTING(BigInteger.valueOf(16L)),
    CLOSED(BigInteger.valueOf(17L)),
    REOPEN(BigInteger.valueOf(18L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
