package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    PROJECT_MANAGER(BigInteger.valueOf(36L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(37L)),
    GROUP_MANAGER(BigInteger.valueOf(38L)),
    DEVELOPER(BigInteger.valueOf(39L)),
    ADMIN(BigInteger.valueOf(42L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
