package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum AttributeID {

    FIRST_NAME(BigInteger.valueOf(1L)),
    LAST_NAME(BigInteger.valueOf(2L)),
    EMAIL(BigInteger.valueOf(3L)),
    ROLE(BigInteger.valueOf(4L)),
    CREATION_DATE(BigInteger.valueOf(5L)),
    PASSWORD(BigInteger.valueOf(6L)),
    STATUS(BigInteger.valueOf(7L)),
    TO_DATE(BigInteger.valueOf(8L)),
    GIT_LOGIN(BigInteger.valueOf(9L)),
    GIT_PASSWORD(BigInteger.valueOf(10L)),
    JIRA_LOGIN(BigInteger.valueOf(11L)),
    JIRA_PASSWORD(BigInteger.valueOf(12L)),
    REPOSITORY_NAME(BigInteger.valueOf(13L)),
    REVIEW_TEXT(BigInteger.valueOf(14L)),
    JOB_QUALITY(BigInteger.valueOf(15L)),
    JOB_AMOUNT(BigInteger.valueOf(16L)),
    COMMUNICATION(BigInteger.valueOf(17L)),
    XP_QUALITY(BigInteger.valueOf(18L)),
    TEAM_SPIRIT(BigInteger.valueOf(19L)),
    ORGANIZATION(BigInteger.valueOf(20L)),
    TIME_MANAGMENT(BigInteger.valueOf(21L)),
    SERVICE_NAME(BigInteger.valueOf(22L)),
    TOKEN_PROJECT(BigInteger.valueOf(23L)),
    TOKEN_CODE(BigInteger.valueOf(24L)),
    REVIEWS(BigInteger.valueOf(25L)),
    PROJECT_USERS(BigInteger.valueOf(26L)),
    REVIEW_AUTHOR(BigInteger.valueOf(27L)),
    PROJECT(BigInteger.valueOf(28L)),
    USER_PROJECT(BigInteger.valueOf(29L));

    private BigInteger id;

    AttributeID(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

}
