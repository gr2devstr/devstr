package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    DEVELOPER(BigInteger.valueOf(1L)),
    GROUP_MANAGER(BigInteger.valueOf(2L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(3L)),
    PROJECT_MANAGER(BigInteger.valueOf(4L)),
    ADMIN(BigInteger.valueOf(7L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
