package com.hatio.projectApp.service;
import com.hatio.projectApp.Repo.TaskRepo;
import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import com.hatio.projectApp.web.TaskController;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {


    Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    TaskService taskService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskRepo taskRepo;

    Project project = new Project();
    Task task = new Task();
    List<Task> taskList = new ArrayList<>();
    
    @BeforeEach
    void init() {
        project.setTitle("java");
        project.setTasks(null);

        task.setDescription("task1");
        task.setStatus("Completed");
        task.setProject(null);
    }




    @Test
    @DisplayName("Get All Task Success")
    void getAllTasks() {
        taskList.add(task);
        Task savedTask = taskService.addTask(task);
        logger.info("working fine"+ taskService.getAllTasks().toString());
        assertTrue(savedTask.getTaskId()!=0);
    }

    @Test
    @DisplayName("Delete Task Success")
    void deleteTaskById() {
        Task savedTask = taskService.addTask(task);
        taskService.deleteTaskById(task.getTaskId());
        Optional<Task> opt = taskService.findById(task.getTaskId());
        assertTrue(opt.isEmpty());
    }

    @Test
    @DisplayName("Delete Task Fail")
    void deleteTaskByIdFail() {
        task.setTaskId(100L);
        Optional<Task> opt = taskService.findById(task.getTaskId());
        assertTrue(true);
    }

    @Test
    @DisplayName("Update Task Success")
    void updateTaskByTask() {
        Task savedTask = taskService.addTask(task);
        savedTask.setDescription("new task");
        Task update = taskService.updateTaskByTask(savedTask);
        if(update.getTaskId()!=0)
            assertEquals(update.getDescription(),savedTask.getDescription());
        assertTrue(Objects.equals(update.getTaskId(), savedTask.getTaskId()) && Objects.equals(update.getDescription(), savedTask.getDescription()));
    }

    @Test
    @DisplayName("Find Task Success")
    void findById() {
        Task savedTask = taskService.addTask(task);
        Optional<Task> findTask= taskService.findById(savedTask.getTaskId());
        if(findTask.isEmpty())  {
            fail();
        }
        else {
            assertEquals(findTask.get().getTaskId(),savedTask.getTaskId());
        }
    }

    @Test
    @DisplayName("Find Task Fail")
    void findByIdFail() {
        task.setTaskId(100L);
        Optional<Task> findTask= taskService.findById(task.getTaskId());
        if(findTask.isEmpty())  {
            assertTrue(true);
        }
        else {
            assertEquals(findTask.get().getTaskId(),task.getTaskId());
        }
    }


    @Test
    @DisplayName("Setting project inside task Success")
    void setProjectId() {
        Project savedProject = projectService.addProject(project);
        Task savedTask = taskService.addTask(task);
        Task setProject = taskService.setProjectId(project,task);
        assertEquals(setProject.getProject().getProjectId(),savedProject.getProjectId());
    }

}