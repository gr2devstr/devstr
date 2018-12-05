package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum ObjectType {

    USER(BigInteger.valueOf(30L)),
    PROJECT(BigInteger.valueOf(31L)),
    REVIEW(BigInteger.valueOf(32L)),
    USER_REVIEW(BigInteger.valueOf(33L)),
    PROJECT_REVIEW(BigInteger.valueOf(34L)),
    TOKEN(BigInteger.valueOf(35L));

    private BigInteger objTypeId;

    ObjectType(BigInteger objTypeId) {
        this.objTypeId = objTypeId;
    }

    public BigInteger getId() {
        return objTypeId;
    }

}
