package com.hatio.projectApp.service;

import com.hatio.projectApp.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    Logger logger = LoggerFactory.getLogger(ProjectServiceTest.class);
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;


    Project project = new Project();

    @BeforeEach
    void init() {
        project.setProjectId(4);
        project.setTitle("java");
        project.setTasks(null);
    }


    @Test
    @DisplayName("get All projects")
    void getAllProjects() {
        logger.info(projectService.getAllProjects().toString());
        Project savedProject = projectService.addProject(project);
        assertTrue((savedProject.getProjectId())!=0);
    }

    @Test
    @DisplayName("Add Project")
    void addProject() {
        Project savedProject = projectService.addProject(project);
        Project p3= projectService.findById(savedProject.getProjectId());
        assertEquals(p3.getProjectId(),savedProject.getProjectId());

    }

    @Test
    @DisplayName("Delete Project")
    void deletingProject() {
        Project savedProject = projectService.addProject(project);
        assertEquals(HttpStatus.OK,projectService.deletingProject(savedProject.getProjectId()));
    }

    @Test
    @DisplayName("Find Project Success")
    void findById() {
        Project savedProject = projectService.addProject(project);
        Project findProject= projectService.findById(savedProject.getProjectId());
        if(findProject != null)
            assertEquals(findProject.getProjectId(),savedProject.getProjectId());
    }

    @Test
    @DisplayName("Find Project Fail")
    void findByIdFail() {
        project.setProjectId(100);
        Project findProject= projectService.findById(project.getProjectId());
        assertNull(findProject);
    }

    @Test
    @DisplayName("Update Project Success")
    void updateProjectByProject() {
        Project savedProject = projectService.addProject(project);
        savedProject.setTitle("new java1");
        Project update = projectService.updateProjectByProject(savedProject);
        if(update.getProjectId()!=0)
            assertEquals(update.getTitle(),savedProject.getTitle());
        assertTrue(update.getProjectId()==savedProject.getProjectId() && update.getTitle()== savedProject.getTitle());
    }

    @Test
    @DisplayName("Update Project Fail")
    void updateProjectByProjectFail() {
        project.setProjectId(100);
        Project findproject = projectService.updateProjectByProject(project);
        assertNull(findproject);
    }


    @Test
    @DisplayName("Find Project Custom fxn")
    void giveMeProject() {
        Project savedProject = projectService.addProject(project);
        Project findProject= projectService.giveMeProject(savedProject.getProjectId());
        if(findProject != null)
            assertEquals(findProject.getProjectId(),savedProject.getProjectId());
    }

    @Test
    @DisplayName("Find Project Fail")
    void giveMeProjectFail() {
        project.setProjectId(100);
        Project findProject= projectService.giveMeProject(project.getProjectId());
        assertNull(findProject);
    }
}