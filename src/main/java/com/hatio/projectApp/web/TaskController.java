package com.hatio.projectApp.web;

import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import com.hatio.projectApp.service.ProjectService;
import com.hatio.projectApp.service.TaskService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TaskController {


    Logger logger = LoggerFactory.getLogger(TaskController.class);
    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/alltasks")
    public String getAllTask(Model model) {
        System.out.println();
        model.addAttribute("tasks",taskService.getAllTasks());
        return "alltasks";
    }



    @GetMapping("/tasks")
    public String getTask(Model model) {
        System.out.println();
        model.addAttribute("tasks",taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/newtask/{projectId}")
    public String newTask(@PathVariable Long projectId, Model model) {
        Task task = new Task();
        Project project = projectService.findById(projectId);
        task.setProject(project);
        model.addAttribute("newtask",task);
        model.addAttribute("projectId",projectId);
        return "newtask";
    }

    @PostMapping("/newtask")
    public String newTask(Task task) {
//        this.taskService.processTask(task);
//        logger.info(projectId+"PRINTINGGGGG");
        Project project = projectService.findById(task.getProject().getProjectId());
        if(project!=null) {
            task.setProject(project);
            taskService.addTask(task);
            return "redirect:/showproject/"+task.getProject().getProjectId();
        }
        return "redirect:/projects";
    }


    @GetMapping("showproject/{projectId}/deletetask/{taskId}")
    public String deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/showproject/"+projectId;
    }

    @GetMapping("/showproject/{projectId}/edittask/{taskId}")
    public String editTask(@PathVariable Long projectId , @PathVariable Long taskId, Model model) {
        Optional<Task> tmp = this.taskService.findById(taskId);
        if(tmp.isPresent()) {
            model.addAttribute("edittask",tmp.get());
            return "edittask";
        }
        return "redirect:/projects";
    }

    @PostMapping("/showproject/{projectId}/edittask/{tId}")
    public String editTask(@PathVariable Long projectId, @PathVariable Long tId, Task task) {
        Project project = projectService.findById(projectId);
        this.taskService.updateTaskByTask(task);
        return "redirect:/showproject/"+projectId;
    }

}
