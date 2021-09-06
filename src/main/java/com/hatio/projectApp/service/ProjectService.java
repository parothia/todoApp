package com.hatio.projectApp.service;

import com.hatio.projectApp.Repo.ProjectRepo;
import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Iterable<Project> getAllProjects() {
        return this.projectRepo.findAll();
    }


    public Project addProject(Project project) {
        return this.projectRepo.save(project);
    }

    public HttpStatus deletingProject(Long projectId) {
        this.projectRepo.deleteById(projectId);
        return HttpStatus.OK;
    }

    public Project findById(Long projectId) {
        Optional<Project> project = projectRepo.findById(projectId);
        //            throw new RuntimeException("Did not find project.");
        return project.orElse(null);
    }


    public Project updateProjectByProject(Project project) {
        Optional<Project> oldtask = this.projectRepo.findById(project.getProjectId());
        if (oldtask.isPresent()) {
            project.setCreatedDate(oldtask.get().getCreatedDate());
            return this.projectRepo.save(project);

        }
        return null;

    }


    ////////////////////////////////////////////////
    public List<Task> getTasksList(Long projectId) {
        Optional<Project> p = this.projectRepo.findById(projectId);
        return p.map(Project::getTasks).orElse(null);
    }

    public Project giveMeProject(Long projectId) {
        Optional<Project> project = this.projectRepo.findById(projectId);
        if (project.isEmpty()) {
            return null;
        }
        return project.get();
    }

}
