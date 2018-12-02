package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssuePriority {

    BLOCKER(BigInteger.valueOf(53L)),
    CRITICAL(BigInteger.valueOf(54L)),
    HIGH(BigInteger.valueOf(55L)),
    MEDIUM(BigInteger.valueOf(56L)),
    LOW(BigInteger.valueOf(57L)),
    LOWEST(BigInteger.valueOf(58L));

    private BigInteger priorityId;

    IssuePriority(BigInteger priorityId) {
        this.priorityId = priorityId;
    }

    public BigInteger getId() {
        return priorityId;
    }

}
