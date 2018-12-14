package com.devstr.controllers;

import com.devstr.dao.ProjectDAO;
import com.devstr.model.Project;
import com.devstr.services.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private GitService gitService;

    static {
        Locale.setDefault(Locale.ENGLISH);
    }

    @RequestMapping(value = "/crateProject/{name}/{pmid}", method = RequestMethod.POST)
    public ResponseEntity<Void> createProject(@PathVariable("name") String name, @PathVariable("pmid") BigInteger managerId, UriComponentsBuilder builder) throws IOException {
        BigInteger flag = projectDAO.createProject(name, managerId);
        if (flag == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/crateProject/{name}/{pmid}").buildAndExpand().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/crateProject/{name}/{projid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProjectRepositoryName(@PathVariable("name") String name, @PathVariable("projid") BigInteger projectID, UriComponentsBuilder builder) throws IOException {
        projectDAO.updateProjectRepositoryName(projectID, name);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/update/{name}/{projid}").buildAndExpand().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{jiraLogin}/{projid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProjectJiraLogin(@PathVariable("jiraLogin") String name, @PathVariable("projid") BigInteger projectID, UriComponentsBuilder builder) throws IOException {
        projectDAO.updateProjectJiraLogin(projectID, name);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/update/{jiraLogin}/{projid}").buildAndExpand().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/update/{jiraPassword}/{projid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProjectJiraPassword(@PathVariable("jiraPassword") String name, @PathVariable("projid") BigInteger projectID, UriComponentsBuilder builder) throws IOException {
        projectDAO.updateProjectJiraPassword(projectID, name);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/update/{jiraPassword}/{projid}").buildAndExpand().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{domain}/{projid}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProjectJiraDomain(@PathVariable("domain") String name, @PathVariable("projid") BigInteger projectID, UriComponentsBuilder builder) throws IOException {
        projectDAO.updateProjectJiraDomain(projectID, name);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/update/{domain}/{projid}").buildAndExpand().toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/read/{projid}", method = RequestMethod.GET)
    public ResponseEntity<Project> readProjectById(@PathVariable("projid") BigInteger projID) throws IOException {
        Project project = projectDAO.readProjectById(projID);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

}
