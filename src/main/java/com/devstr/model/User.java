package com.devstr.model;

import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

public interface User {

    public BigInteger getUserId();

    public String getLogin();

    public String getPassword();

    public String getEmail();

    public String getFirstName();

    public String getLastName();

    public UserRole getRole();

    public Date getHireDate();

    public boolean isStatus();

    public Set<BigInteger> getReviewsId();

    public BigInteger getProjectId();

    public void setReviewId(BigInteger reviewId);

    public void setReviewsId(Set<BigInteger> reviewsId);

    public void setProjectId(BigInteger projectId);

}
