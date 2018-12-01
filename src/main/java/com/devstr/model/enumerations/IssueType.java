package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(113L)),
    IMPROVEMENT(BigInteger.valueOf(114L)),
    TASK(BigInteger.valueOf(115L)),
    NEW_FEATURE(BigInteger.valueOf(116L)),
    DESIGN_BUG(BigInteger.valueOf(117L)),
    EPIC(BigInteger.valueOf(118L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
