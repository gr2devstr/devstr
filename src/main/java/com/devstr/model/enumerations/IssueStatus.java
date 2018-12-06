package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueStatus {

    OPEN(BigInteger.valueOf(51L)),
    IN_PROGRESS(BigInteger.valueOf(52L)),
    READY_FOR_TESTING(BigInteger.valueOf(53L)),
    CLOSED(BigInteger.valueOf(54L)),
    REOPEN(BigInteger.valueOf(55L));

    private BigInteger statusId;

    IssueStatus(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
