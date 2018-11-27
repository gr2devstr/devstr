package com.devstr.model.impl;

import com.devstr.model.User;
import com.devstr.model.enumerations.Status;
import com.devstr.model.enumerations.UserRole;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserImpl implements User {

    private BigInteger userId;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private Date hireDate;
    private Status status;
    private BigInteger projectId;
    private List<BigInteger> reviewsId;

    @Override
    public BigInteger getUserId() {
        return userId;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public Date getHireDate() {
        return hireDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public BigInteger getProjectId() {
        return projectId;
    }

    @Override
    public List<BigInteger> getReviewsId() {
        return reviewsId;
    }

    @Override
    public void setReviewsId(List<BigInteger> reviewsId) {
        if (this.reviewsId == null) {
            this.reviewsId = new ArrayList<>();
        }
        this.reviewsId.addAll(reviewsId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserImpl user = (UserImpl) o;

        return getUserId().equals(user.userId);
    }

    @Override
    public int hashCode() {

        return getUserId().hashCode();
    }

    public static class Builder {
        private String login;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private UserRole role;
        private Status status;
        private BigInteger userId;
        private Date hireDate;
        private BigInteger projectId;
        private List<BigInteger> reviewsId;

        public Builder(String login, String password, String email, String firstName, String lastName, UserRole role, Status status) {
            this.login = login;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.role = role;
            this.status = status;
        }

        public Builder setUserId(BigInteger userId) {
            this.userId = userId;
            return this;
        }

        public Builder setHiraDate(Date hiraDate) {
            this.hireDate = hiraDate;
            return this;
        }

        public Builder setReviewsId(List<BigInteger> reviewsId) {
            this.reviewsId = reviewsId;
            return this;
        }

        public Builder setProjectId(BigInteger projectId) {
            this.projectId = projectId;
            return this;
        }
    }

    private UserImpl(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
        this.status = builder.status;
        this.userId = builder.userId;
        this.hireDate = builder.hireDate;
        this.reviewsId = builder.reviewsId;
        this.projectId = builder.projectId;
    }
}
