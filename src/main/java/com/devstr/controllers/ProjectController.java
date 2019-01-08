package com.devstr.controllers;

import com.devstr.DevstrFactoryManager;
import com.devstr.controllers.validation.ProjectValidator;
import com.devstr.dao.ProjectDAO;
import com.devstr.exception.DaoException;
import com.devstr.logger.DevstrLogger;
import com.devstr.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private static final DevstrLogger LOGGER = DevstrFactoryManager.getLoggerFactory()
            .getLogger(ProjectController.class.getName());

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private ProjectValidator projectValidator;

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        if (!projectValidator.isNameValid(project.getProjectName())) {
            String message = "Project with this name already exists";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (projectValidator.isPmIdValid(project.getProjectManagerId())) {
                BigInteger id = projectDAO.createProject(
                        project.getProjectName(),
                        project.getProjectManagerId()
                );
                Project newProject = projectDAO.readProjectById(id);
                return new ResponseEntity<>(newProject, HttpStatus.OK);
            } else {
                String message = "Project manager ID is incorrect, ID: " + project.getProjectManagerId();
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @PatchMapping("/update/reponame/{name}/id/{id}")
    public ResponseEntity<Project> updateProjectRepositoryName(@PathVariable("name") String name,
                                                               @PathVariable("id") BigInteger id) {
        if (projectValidator.isTextNotValid(name)) {
            String message = "Repo name: " + name + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (projectValidator.isProjectIdValid(id)) {
                Project oldProject = projectDAO.readProjectById(id);
                if (oldProject.getRepoName() != null && !oldProject.getRepoName().equals(name)) {
                    projectDAO.updateProjectRepositoryName(id, name);
                    Project updProject = projectDAO.readProjectById(id);
                    return new ResponseEntity<>(updProject, HttpStatus.OK);
                } else {
                    String message = "Repo name: " + name + " - isn't valid, already exist";
                    LOGGER.info(message);
                    return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
                }
            } else {
                String message = "Project not found, ID: " + id;
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @PatchMapping("/update/jiralogin/{login}/id/{id}")
    public ResponseEntity<Project> updateJiraLogin(@PathVariable("login") String login,
                                                   @PathVariable("id") BigInteger id) {
        if (projectValidator.isTextNotValid(login)) {
            String message = "Jira login: " + login + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (projectValidator.isProjectIdValid(id)) {
                Project oldProject = projectDAO.readProjectById(id);
                if (oldProject.getJiraLogin() != null && !oldProject.getJiraLogin().equals(login)) {
                    projectDAO.updateProjectJiraLogin(id, login);
                    Project updProject = projectDAO.readProjectById(id);
                    return new ResponseEntity<>(updProject, HttpStatus.OK);
                } else {
                    String message = "Jira login: " + login + " - isn't valid, already exist";
                    LOGGER.info(message);
                    return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
                }
            } else {
                String message = "Project not found, ID: " + id;
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @PatchMapping("/update/jirapass/{pass}/id/{id}")
    public ResponseEntity<Project> updateJiraPassword(@PathVariable("pass") String pass,
                                                      @PathVariable("id") BigInteger id) {
        if (projectValidator.isTextNotValid(pass)) {
            String message = "Jira password: " + pass + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (projectValidator.isProjectIdValid(id)) {
                Project oldProject = projectDAO.readProjectById(id);
                if (oldProject.getJiraPassword() != null && !oldProject.getJiraPassword().equals(pass)) {
                    projectDAO.updateProjectJiraLogin(id, pass);
                    Project updProject = projectDAO.readProjectById(id);
                    return new ResponseEntity<>(updProject, HttpStatus.OK);
                } else {
                    String message = "Jira password: " + pass + " - isn't valid, already exist";
                    LOGGER.info(message);
                    return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
                }
            } else {
                String message = "Project not found, ID: " + id;
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('PROJECT_MANAGER')")
    @PatchMapping("/update/jiradomain/{domain}/id/{id}")
    public ResponseEntity<Project> updateJiraDomain(@PathVariable("domain") String domain,
                                                    @PathVariable("id") BigInteger id) {
        if (projectValidator.isTextNotValid(domain)) {
            String message = "Jira domain: " + domain + " - isn't valid, have to be not null or empty";
            LOGGER.info(message);
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
        try {
            if (projectValidator.isProjectIdValid(id)) {
                Project oldProject = projectDAO.readProjectById(id);
                if (oldProject.getJiraDomain() != null && !oldProject.getJiraDomain().equals(domain)) {
                    projectDAO.updateProjectJiraLogin(id, domain);
                    Project updProject = projectDAO.readProjectById(id);
                    return new ResponseEntity<>(updProject, HttpStatus.OK);
                } else {
                    String message = "Jira domain: " + domain + " - isn't valid, already exist";
                    LOGGER.info(message);
                    return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
                }
            } else {
                String message = "Project not found, ID: " + id;
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TECHNICAL_MANAGER','PROJECT_MANAGER','GROUP_MANAGER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<Project> readProjectById(@PathVariable("id") BigInteger id) {
        try {
            if (projectValidator.isProjectIdValid(id)) {
                Project project = projectDAO.readProjectById(id);
                return new ResponseEntity<>(project, HttpStatus.OK);
            } else {
                String message = "Project not found, ID: " + id;
                LOGGER.info(message);
                return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.NOT_FOUND);
            }
        } catch (DaoException exc) {
            LOGGER.error(exc.getMessage(), exc);
            String message = "Sorry, server is temporarily busy";
            return new ResponseEntity<>(null, getErrorMsg(message), HttpStatus.BAD_REQUEST);
        }
    }

    private MultiValueMap<String, String> getErrorMsg(String message) {
        MultiValueMap<String, String> errorMessage = new LinkedMultiValueMap<>();
        List<String> errorBody = Collections.singletonList(message);
        errorMessage.put("Error", errorBody);
        return errorMessage;
    }

}
