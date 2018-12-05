package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(54L)),
    CRITICAL(BigInteger.valueOf(55L)),
    HIGH(BigInteger.valueOf(56L)),
    MEDIUM(BigInteger.valueOf(57L)),
    LOW(BigInteger.valueOf(58L)),
    LOWEST(BigInteger.valueOf(59L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
