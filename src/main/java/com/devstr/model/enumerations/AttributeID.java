package com.devstr.model.enumerations;

import java.math.BigInteger;

public enum AttributeID {

    FIRST_NAME(BigInteger.valueOf(1)),
    LAST_NAME(BigInteger.valueOf(2)),
    EMAIL(BigInteger.valueOf(3)),
    ROLE(BigInteger.valueOf(4)),
    CREATION_DATE(BigInteger.valueOf(5)),
    PASSWORD(BigInteger.valueOf(6)),
    STATUS(BigInteger.valueOf(7)),
    TO_DATE(BigInteger.valueOf(8)),
    GIT_LOGIN(BigInteger.valueOf(9)),
    GIT_PASSWORD(BigInteger.valueOf(10)),
    JIRA_LOGIN(BigInteger.valueOf(11)),
    JIRA_PASSWORD(BigInteger.valueOf(12)),
    REPOSITORY_NAME(BigInteger.valueOf(13)),
    REVIEW_TEXT(BigInteger.valueOf(14)),
    JOB_QUALITY(BigInteger.valueOf(15)),
    JOB_AMOUNT(BigInteger.valueOf(16)),
    COMMUNICATION(BigInteger.valueOf(17)),
    XP_QUALITY(BigInteger.valueOf(18)),
    TEAM_SPIRIT(BigInteger.valueOf(19)),
    ORGANIZATION(BigInteger.valueOf(20)),
    TIME_MANAGMENT(BigInteger.valueOf(21)),
    SERVICE_NAME(BigInteger.valueOf(22)),
    TOKEN_PROJECT(BigInteger.valueOf(23)),
    TOKEN_CODE(BigInteger.valueOf(24)),
    REVIEWS(BigInteger.valueOf(25)),
    PROJECT_USERS(BigInteger.valueOf(26)),
    REVIEW_AUTHOR(BigInteger.valueOf(27)),
    PROJECT(BigInteger.valueOf(28)),
    USER_PROJECT(BigInteger.valueOf(500));/*When the database is updated, change the ID to 29*/

    private BigInteger id;

    AttributeID(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

}