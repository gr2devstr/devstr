package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(56L)),
    CRITICAL(BigInteger.valueOf(57L)),
    HIGH(BigInteger.valueOf(58L)),
    MEDIUM(BigInteger.valueOf(59L)),
    LOW(BigInteger.valueOf(60L)),
    LOWEST(BigInteger.valueOf(61L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
