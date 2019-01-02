package com.devstr.model;

import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;
import java.util.Date;

public interface User {
    BigInteger getUserId();

    String getLogin();

    String getPassword();

    String getEmail();

    BigInteger getManagerId();

    String getFirstName();

    String getLastName();

    UserRole getRole();

    Date getHireDate();

    Status getStatus();

    BigInteger getProjectId();

}