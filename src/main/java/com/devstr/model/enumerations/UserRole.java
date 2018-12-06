package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    PROJECT_MANAGER(BigInteger.valueOf(37L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(38L)),
    GROUP_MANAGER(BigInteger.valueOf(39L)),
    DEVELOPER(BigInteger.valueOf(40L)),
    ADMIN(BigInteger.valueOf(43L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
