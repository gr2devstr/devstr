package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(1L)),
    CRITICAL(BigInteger.valueOf(2L)),
    HIGH(BigInteger.valueOf(3L)),
    MEDIUM(BigInteger.valueOf(4L)),
    LOW(BigInteger.valueOf(5L)),
    LOWEST(BigInteger.valueOf(6L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
