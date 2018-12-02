package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    PROJECT_MANAGER(BigInteger.valueOf(35L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(36L)),
    GROUP_MANAGER(BigInteger.valueOf(37L)),
    DEVELOPER(BigInteger.valueOf(38L)),
    ADMIN(BigInteger.valueOf(41L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
