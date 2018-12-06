package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum Status {

    ACTIVE(BigInteger.valueOf(41L)),
    INACTIVE(BigInteger.valueOf(42L));

    private BigInteger statusId;

    Status(BigInteger statusId) {
        this.statusId = statusId;
    }

    public BigInteger getId() {
        return statusId;
    }

}
