package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum ObjectType {

    USER(BigInteger.valueOf(1L)),
    PROJECT(BigInteger.valueOf(2L)),
    REVIEW(BigInteger.valueOf(3L)),
    USER_REVIEW(BigInteger.valueOf(4L)),
    PROJECT_REVIEW(BigInteger.valueOf(5L)),
    TOKEN(BigInteger.valueOf(6L));

    private BigInteger objTypeId;

    ObjectType(BigInteger objTypeId) {
        this.objTypeId = objTypeId;
    }

    public BigInteger getId() {
        return objTypeId;
    }

}
