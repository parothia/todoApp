package com.hatio.projectApp.web;

import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import com.hatio.projectApp.service.ProjectService;
import com.hatio.projectApp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }



    @GetMapping("/projects")
    public String getProjects(  Model model) {
        List<Project> projects= (List<Project>) this.projectService.getAllProjects();
        model.addAttribute("projects",projects);
        logger.info("INFOOO");
        return "projects";
    }

    @GetMapping("/showproject/{projectId}")
    public String showProject(@PathVariable Long projectId, Model model) {
        List<Task> tasksOfProject= this.projectService.getTasksList(projectId);
        model.addAttribute("tasks",tasksOfProject);
        model.addAttribute("projectName",projectService.findById(projectId).getTitle());
        return "tasks";
    }


    @RequestMapping("/newproject")
    @GetMapping("/newproject")
    public String newProject(Model model) {
        model.addAttribute("newproject",new Project());
        return "newproject";
    }
    @PostMapping("/newproject")
    public String newProject(Project project) {
        projectService.addProject(project);
        return "redirect:/projects";
    }




    @GetMapping("/editproject/{projectId}")
    public String editProject(@PathVariable Long projectId, Model model) {
        model.addAttribute("editproject",this.projectService.findById(projectId));
        return "editproject";
    }
    @PostMapping("/updateproject")
    public String updateProject(Project project, Model model) {
        this.projectService.updateProjectByProject(project);
        return "redirect:/projects";
    }






    @GetMapping("/deleteproject/{projectId}")
    public String deletingProject(@PathVariable Long projectId) {
        System.out.println("NNNNNNNNNNNNNNNNN");
        projectService.deletingProject(projectId);
        return "redirect:/projects";
    }



    @RequestMapping("/getsummary/{projectId}")
    @ResponseBody
    public String getSummary(@PathVariable Long projectId) throws IOException {
        try {
            Project p = this.projectService.giveMeProject(projectId);
//            FileWriter myWriter = new FileWriter(p.getTitle()+".md");
            System.out.println(p.getTitle());
            List<Task> tasks = this.projectService.getTasksList(projectId);
            List<String> completed = new ArrayList<>();
            List<String> pending = new ArrayList<>();
            for (Task task : tasks) {
                if(task.getStatus().equals("completed")) {
                    completed.add(task.getDescription());
                }
                else pending.add(task.getDescription());
            }
            StringBuilder sb=new StringBuilder("# "+p.getTitle());
            sb.append(System.getProperty("line.separator"));
            sb.append("  ***  ");
            sb.append(System.getProperty("line.separator"));
            sb.append("**Summary:** ");
            sb.append(+completed.size()).append("/").append(tasks.size()).append(" Todos Completed  ");
            sb.append(System.getProperty("line.separator"));
            sb.append("### Completed    ");
            sb.append(System.getProperty("line.separator"));
            int t=0;
            while(completed.size()>t) {
                sb.append("- [x] ").append(completed.get(t));
                sb.append(System.getProperty("line.separator"));
                t++;
            }
            t=0;
            sb.append(System.getProperty("line.separator"));
            sb.append("### Pending    ");
            sb.append(System.getProperty("line.separator"));
            while(pending.size()>t) {
                sb.append("- [ ] ").append(pending.get(t));
                sb.append(System.getProperty("line.separator"));
                t++;
            }
//            myWriter.write(sb.toString());
//            myWriter.close();
            File targetFile = new File("/home/parothia/README_FILES/"+p.getTitle()+".md");
            targetFile.createNewFile();
            Writer targetFileWriter = new FileWriter(targetFile);
            targetFileWriter.write(sb.toString());
            targetFileWriter.close();
//            return new FileSystemResource(targetFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "redirect:/projects";
//        return new FileSystemResource(new File("failed"));
    }

}


