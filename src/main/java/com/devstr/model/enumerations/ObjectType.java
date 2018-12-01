package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum ObjectType {

    USER(BigInteger.valueOf(100L)),
    PROJECT(BigInteger.valueOf(101L)),
    REVIEW(BigInteger.valueOf(102L)),
    USER_REVIEW(BigInteger.valueOf(103L)),
    PROJECT_REVIEW(BigInteger.valueOf(104L)),
    TOKEN(BigInteger.valueOf(105L));

    private BigInteger objTypeId;

    ObjectType(BigInteger objTypeId) {
        this.objTypeId = objTypeId;
    }

    public BigInteger getId() {
        return objTypeId;
    }

}
