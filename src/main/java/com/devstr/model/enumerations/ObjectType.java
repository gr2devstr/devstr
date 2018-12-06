package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum ObjectType {

    USER(BigInteger.valueOf(31L)),
    PROJECT(BigInteger.valueOf(32L)),
    REVIEW(BigInteger.valueOf(33L)),
    USER_REVIEW(BigInteger.valueOf(34L)),
    PROJECT_REVIEW(BigInteger.valueOf(35L)),
    TOKEN(BigInteger.valueOf(36L));

    private BigInteger objTypeId;

    ObjectType(BigInteger objTypeId) {
        this.objTypeId = objTypeId;
    }

    public BigInteger getId() {
        return objTypeId;
    }

}
