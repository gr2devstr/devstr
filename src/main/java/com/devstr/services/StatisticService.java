package com.devstr.services;

import com.devstr.model.Project;

import java.math.BigInteger;
import java.util.Collection;

public interface StatisticService {
    /**
     * Counts part of tasks that were in status reopen to total closed by user
     *
     * @param project project tasks of which we look for
     * @param userId id of interested user
     * @return double value from 0 to 1
     */
    double reopenRateToClosedIssues(Project project, BigInteger userId);

    /**
     * Counts part of tasks that were in status REOPEN for the user from all number of reopen
     * tasks of a certain project
     *
     * @param project project tasks of which we look for
     * @param userId id of interested user
     * @return double value from 0 to 1
     */
    double reopenRateToProjectReopens(Project project, BigInteger userId);

    /**
     * Counts all closed Issues on project
     *
     * @param project project that we look for
     * @return long integer value >=0
     */
    long countClosedIssues(Project project);

    /**
     * Counts closed issues on project of a certain user
     *
     * @param project project where user worked
     * @param userId id of user we look for
     * @return long integer value >=0
     */
    long countClosedIssues(Project project, BigInteger userId);

    /**
     * Counts weighted mark of closed issues for certain user
     * @param project project where user worked
     * @param userId id of user we look for
     * @return double value >=0
     */
    double countClosedIssuesWithPriority(Project project, BigInteger userId);

    /**
     * Counts how user was involved into project depending on total project weight
     *
     * @param project project where user worked
     * @param userId id of user we look for
     * @return double value from 0 to 1.
     */
    double countIssueMarkPrioritized(Project project, BigInteger userId);

    /**
     * Counts number of overdated tasks by user on the project
     *
     * @param project project where user worked
     * @param userId id of user we look for
     * @return long integer value >=0
     */
    long countOverDates(Project project, BigInteger userId);

    /**
     * Counts failed builds of user during work on the project.
     *
     * @param project project where user worked
     * @param user id of user we look for
     * @return long integer value >=0
     */
    long countFailBuildsOnProjectOfUser(Project project, BigInteger user);

    /**
     * Counts failed build on project of several users
     *
     * @param project where user worked
     * @param users collection with ids of users we're interested
     * @return total number of failed builds of users in collection
     */
    long countFailBuildsOnProjectOfGroupUsers(Project project, Collection<BigInteger> users);

    /**
     * Counts part of this user's failed builds to total project failed builds
     *
     * @param project project where user worked
     * @param userId id of user we look for
     * @return double value from 0 to 1.
     */
    double failedBuildMarkOnProject(Project project, BigInteger userId);

}
