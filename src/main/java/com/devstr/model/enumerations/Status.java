package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum Status {

    ACTIVE(BigInteger.valueOf(5L)),
    INACTIVE(BigInteger.valueOf(6L));

    private BigInteger statusId;

    Status(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }
}
