package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    PROJECT_MANAGER(BigInteger.valueOf(1L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(2L)),
    GROUP_MANAGER(BigInteger.valueOf(3L)),
    DEVELOPER(BigInteger.valueOf(4L)),
    ADMIN(BigInteger.valueOf(5L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
