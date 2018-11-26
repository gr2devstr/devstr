package com.devstr.model;

import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface User {
    BigInteger getUserId();

    String getLogin();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();

    UserRole getRole();

    Date getHireDate();

    Status getStatus();

    BigInteger getProjectId();

    List<BigInteger> getReviewsId();

    void setReviewsId(List<BigInteger> reviewsId);

}