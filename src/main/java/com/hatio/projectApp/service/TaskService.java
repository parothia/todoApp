package com.hatio.projectApp.service;

import com.hatio.projectApp.Repo.ProjectRepo;
import com.hatio.projectApp.Repo.TaskRepo;
import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo, ProjectRepo projectRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void deleteTaskById( Long taskId) {
         taskRepo.deleteById(taskId);
    }

    public Task updateTaskByTask(Task task) {
        Optional<Task> oldtask = this.taskRepo.findById(task.getTaskId());
        if(oldtask.isPresent()) {
            task.setCreatedDate(oldtask.get().getCreatedDate());
            return this.taskRepo.save(task);
        }

        return null;
    }

    public Optional<Task> findById(Long taskId) {
        return this.taskRepo.findById(taskId);
    }


    public Task setProjectId(Project project, Task task) {
        task.setProject(project);
        return task;
    }

    public Task addTask(Task task) {
        return taskRepo.save(task);

    }


//    public List<Task> getAllTasksByProjectId(Long projectId) {
//        List<Task> alltasks = (List<Task>) this.taskRepo.findAll();
////        for(Task alltask:alltasks) {
////            System.out.println(alltask.getTaskId());
////            System.out.println(alltask.);
////        }
////        this.taskRepo.getAllTasksByProjectId(projectId);
//        return alltasks;
//    }


}
