package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum IssueType {

    BUG(BigInteger.valueOf(44L)),
    IMPROVEMENT(BigInteger.valueOf(45L)),
    TASK(BigInteger.valueOf(46L)),
    NEW_FEATURE(BigInteger.valueOf(47L)),
    DESIGN_BUG(BigInteger.valueOf(48L)),
    EPIC(BigInteger.valueOf(49L)),
    SUB_TASK(BigInteger.valueOf(50L));

    private BigInteger typeId;

    IssueType(BigInteger typeId) {
        this.typeId = typeId;
    }

    public BigInteger getId() {
        return typeId;
    }

}
