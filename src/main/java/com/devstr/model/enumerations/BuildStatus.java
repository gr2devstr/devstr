package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum BuildStatus {

    SUCCESS(BigInteger.valueOf(1)),
    FAILURE(BigInteger.valueOf(0)),
    PENDING(BigInteger.valueOf(-1));

    private BigInteger status;

    BuildStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getStatus() {
        return status;
    }

}
