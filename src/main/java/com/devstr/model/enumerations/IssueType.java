package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(1L)),
    IMPROVEMENT(BigInteger.valueOf(2L)),
    TASK(BigInteger.valueOf(3L)),
    NEW_FEATURE(BigInteger.valueOf(4L)),
    DESIGN_BUG(BigInteger.valueOf(5L)),
    EPIC(BigInteger.valueOf(6L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
