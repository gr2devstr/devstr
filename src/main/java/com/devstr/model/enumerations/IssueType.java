package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(42L)),
    IMPROVEMENT(BigInteger.valueOf(43L)),
    TASK(BigInteger.valueOf(44L)),
    NEW_FEATURE(BigInteger.valueOf(45L)),
    DESIGN_BUG(BigInteger.valueOf(46L)),
    EPIC(BigInteger.valueOf(47L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
