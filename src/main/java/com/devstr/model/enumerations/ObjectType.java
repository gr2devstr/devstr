package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum ObjectType {

    USER(BigInteger.valueOf(29L)),
    PROJECT(BigInteger.valueOf(30L)),
    REVIEW(BigInteger.valueOf(31L)),
    USER_REVIEW(BigInteger.valueOf(32L)),
    PROJECT_REVIEW(BigInteger.valueOf(33L)),
    TOKEN(BigInteger.valueOf(34L));

    private BigInteger objTypeId;

    ObjectType(BigInteger objTypeId) {
        this.objTypeId = objTypeId;
    }

    public BigInteger getId() {
        return objTypeId;
    }

}
