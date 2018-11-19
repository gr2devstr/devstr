package com.devstr.model;

import com.devstr.model.enumerations.UserRole;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class User {

    private int userId;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private LocalDate hireDate;
    private boolean status;
    private Set<Integer> reviewsId;
    private int projectId;

    private User() {
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public boolean isStatus() {
        return status;
    }

    public Set<Integer> getReviewsId() {
        return reviewsId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setReviewId(int reviewId) {
        if (this.reviewsId == null) {
            this.reviewsId = new HashSet<>();
        }
        this.reviewsId.add(reviewId);
    }

    public void setReviewsId(Set<Integer> reviewsId) {
        if (this.reviewsId == null) {
            this.reviewsId = new HashSet<>();
        }
        this.reviewsId.addAll(reviewsId);
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUserId() == user.getUserId();
    }

    @Override
    public int hashCode() {
        return getUserId();
    }

    public class UserBuilder {

        private UserBuilder() {
        }

        public UserBuilder setUserId(int userId) {
            User.this.userId = userId;
            return this;
        }

        public UserBuilder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public UserBuilder setRole(UserRole role) {
            User.this.role = role;
            return this;
        }

        public UserBuilder setHireDate(LocalDate hireDate) {
            User.this.hireDate = hireDate;
            return this;
        }

        public UserBuilder setStatus(boolean status) {
            User.this.status = status;
            return this;
        }

        public UserBuilder setReviewId(int reviewId) {
            if (User.this.reviewsId == null) {
                User.this.reviewsId = new HashSet<>();
            }
            User.this.reviewsId.add(reviewId);
            return this;
        }

        public UserBuilder setReviewsId(Set<Integer> reviewsId) {
            if (User.this.reviewsId == null) {
                User.this.reviewsId = new HashSet<>();
            }
            User.this.reviewsId.addAll(reviewsId);
            return this;
        }

        public UserBuilder setProjectId(int projectId) {
            User.this.projectId = projectId;
            return this;
        }

        public User build() {
            return User.this;
        }

    }

}
