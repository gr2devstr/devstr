package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(8L)),
    IMPROVEMENT(BigInteger.valueOf(9L)),
    TASK(BigInteger.valueOf(10L)),
    NEW_FEATURE(BigInteger.valueOf(11L)),
    DESIGN_BUG(BigInteger.valueOf(12L)),
    EPIC(BigInteger.valueOf(13L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
