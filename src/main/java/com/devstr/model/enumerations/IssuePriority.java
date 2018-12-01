package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(124L)),
    CRITICAL(BigInteger.valueOf(125L)),
    HIGH(BigInteger.valueOf(126L)),
    MEDIUM(BigInteger.valueOf(127L)),
    LOW(BigInteger.valueOf(128L)),
    LOWEST(BigInteger.valueOf(129L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
