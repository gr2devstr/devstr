package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(49L)),
    IN_PROGRESS(BigInteger.valueOf(50L)),
    READY_FOR_TESTING(BigInteger.valueOf(51L)),
    CLOSED(BigInteger.valueOf(52L)),
    REOPEN(BigInteger.valueOf(53L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
