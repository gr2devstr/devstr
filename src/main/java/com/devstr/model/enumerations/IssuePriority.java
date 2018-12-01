package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(19L)),
    CRITICAL(BigInteger.valueOf(20L)),
    HIGH(BigInteger.valueOf(21L)),
    MEDIUM(BigInteger.valueOf(22L)),
    LOW(BigInteger.valueOf(23L)),
    LOWEST(BigInteger.valueOf(24L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
