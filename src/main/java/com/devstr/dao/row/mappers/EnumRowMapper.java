package com.devstr.dao.row.mappers;

/**
 * Created by Robert in 18.11.2018
 */
public enum EnumRowMapper {

    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    EMAIL("email"),
    ROLE("role"),
    CREATION_DATE("creation_date"),
    PASSWORD("password"),
    STATUS("status"),
    TO_DATE("to_date"),
    GIT_LOGIN("git_login"),
    GIT_PASSWORD("git_pass"),
    JIRA_LOGIN("jira_login"),
    JIRA_PASSWORD("jira_pass"),
    REPOSITORY_NAME("repository_name"),
    REVIEW_TEXT("review_text"),
    JOB_QUALITY("job_quality"),
    JOB_AMOUNT("job_amount"),
    COMMUNICATION("communication"),
    XP_QUALITY("xp_quality"),
    TEAM_SPIRIT("team_spirit"),
    ORGANIZATION("organization"),
    TIME_MANAGMENT("time_mng"),
    SERVICE_NAME("service_name"),
    TOKEN("token"),
    TOKEN_CODE("token_code"),
    REVIEWS("reviews"),
    PROJECT_USERS("project_users"),
    REVIEW_AUTHOR("review_author"),
    PROJECT("project");

    private String fullName;

    EnumRowMapper(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }

}
