package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(43L)),
    IMPROVEMENT(BigInteger.valueOf(44L)),
    TASK(BigInteger.valueOf(45L)),
    NEW_FEATURE(BigInteger.valueOf(46L)),
    DESIGN_BUG(BigInteger.valueOf(47L)),
    EPIC(BigInteger.valueOf(48L)),
    SUB_TASK(BigInteger.valueOf(60L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
