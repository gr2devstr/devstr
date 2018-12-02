package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(48L)),
    IN_PROGRESS(BigInteger.valueOf(49L)),
    READY_FOR_TESTING(BigInteger.valueOf(50L)),
    CLOSED(BigInteger.valueOf(51L)),
    REOPEN(BigInteger.valueOf(52L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
