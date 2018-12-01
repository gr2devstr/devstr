package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum UserRole {

    DEVELOPER(BigInteger.valueOf(109L)),
    GROUP_MANAGER(BigInteger.valueOf(108L)),
    TECHNICAL_MANAGER(BigInteger.valueOf(107L)),
    PROJECT_MANAGER(BigInteger.valueOf(106L)),
    ADMIN(BigInteger.valueOf(112L));

    private BigInteger userRoleId;

    UserRole(BigInteger userRoleId) {
        this.userRoleId = userRoleId;
    }

    public BigInteger getId() {
        return userRoleId;
    }

}
