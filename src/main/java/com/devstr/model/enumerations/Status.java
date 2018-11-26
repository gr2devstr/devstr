package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum Status {

    ACTIVE_STATUS(BigInteger.valueOf(5L)),
    INACTIVE_STATUS(BigInteger.valueOf(6L));

    BigInteger status;

    Status(BigInteger status) {
        this.status = status;
    }

    public BigInteger getStatus() {
        return status;
    }
}