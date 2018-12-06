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
    JIRA_DOMAIN(BigInteger.valueOf(13L)),
    REPOSITORY_NAME(BigInteger.valueOf(14L)),
    REVIEW_TEXT(BigInteger.valueOf(15L)),
    JOB_QUALITY(BigInteger.valueOf(16L)),
    JOB_AMOUNT(BigInteger.valueOf(17L)),
    COMMUNICATION(BigInteger.valueOf(18L)),
    XP_QUALITY(BigInteger.valueOf(19L)),
    TEAM_SPIRIT(BigInteger.valueOf(20L)),
    ORGANIZATION(BigInteger.valueOf(21L)),
    TIME_MANAGMENT(BigInteger.valueOf(22L)),
    SERVICE_NAME(BigInteger.valueOf(23L)),
    TOKEN_PROJECT(BigInteger.valueOf(24L)),
    TOKEN_CODE(BigInteger.valueOf(25L)),
    REVIEWS(BigInteger.valueOf(26L)),
    PROJECT_USERS(BigInteger.valueOf(27L)),
    REVIEW_AUTHOR(BigInteger.valueOf(28L)),
    PROJECT(BigInteger.valueOf(29L)),
    USER_PROJECT(BigInteger.valueOf(30L));

    private BigInteger id;

    AttributeID(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

}
