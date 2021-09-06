//package com.hatio.projectApp.web;
//
//import com.hatio.projectApp.entity.Project;
//import com.hatio.projectApp.entity.Task;
//import com.hatio.projectApp.service.ProjectService;
//import com.hatio.projectApp.service.TaskService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@WebMvcTest(ProjectController.class)
//class ProjectControllerTest {
//
//    @MockBean
//    ProjectService projectService;
//    @MockBean
//    TaskService taskService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    Logger logger = LoggerFactory.getLogger(ProjectController.class);
//
//    List<Project> projectList = new ArrayList<>();
//    Project project = new Project();
//    Project updatedProject = new Project();
//    Task task = new Task();
//    @BeforeEach
//    void init() throws ParseException {
//        project.setProjectId(1);
//        project.setTitle("java");
//        project.setTasks(null);
//
//        updatedProject.setProjectId(1);
//        updatedProject.setTitle("new java");
//        updatedProject.setTasks(null);
//
//        task.setTaskId(1L);
//        task.setDescription("new task");
//        task.setStatus("completed");
//        task.setProject(project);
//
//        projectList.add(project);
//    }
//
//
//    @DisplayName("Getting All projects")
//    @Test
//    void getProjects() throws Exception {
//        Mockito.mock(ProjectService.class);
//        Mockito.when(projectService.getAllProjects())
//                .thenReturn(projectList);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/projects").accept(MediaType.ALL)).andReturn();
//        verify(projectService).getAllProjects();
//    }
//
//
//    @Test
//    void addProject() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/addproject"))
//                .andExpect(view().name("addprojects")).andReturn();
//        verify(projectService, times(0)).addProject(project);
//    }
//
//    @Test
//    void updateProjectById() throws Exception {
//
//        Mockito.when(projectService.findById(project.getProjectId()))
//                .thenReturn(project);
//
//        long projectId = 1L;
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/updateproject/}"+projectId)
//                .accept(MediaType.TEXT_HTML_VALUE);
//        MvcResult result = mockMvc.perform(get("/updateproject/" + projectId).accept(MediaType.TEXT_HTML_VALUE))
//                .andExpect(view().name("updateprojects")).andReturn();
//        verify(projectService).findById(projectId);
//    }
//
//    @Test
//    void updatedProject() throws Exception {
//        Mockito.when(projectService.updateProjectByProject(project))
//                .thenReturn(updatedProject);
//        MvcResult result = mockMvc.perform(post("/updatedproject").accept(MediaType.ALL))
//                .andExpect(view().name("redirect:/projects")).andReturn();
//        System.out.println("\n\n\n"+updatedProject.toString()+"\n\n\n\n");
//        verify(projectService).updateProjectByProject(project);
//
//    }
//
//
//
//
//
//    ///////////////////////////////////////////////////////////////
//    //Arguements are different with only difference in ids
//    @Test
//    @DisplayName("Adding project")
//    void processProject() throws Exception {
//        System.out.println(project.getProjectId());
//        when(projectService.addProject(project)).thenReturn(project);
//        var result = mockMvc.perform(post("/processproject"))
//                .andExpect(view().name("redirect:/projects")).andReturn();
//        verify(projectService,times(1)).addProject(project);
//
//    }
//
//    @Test
//    void deletingProject() throws Exception {
//        when(projectService.deletingProject(1L)).thenReturn(null);
//        mockMvc.perform(get("/deleteproject/"+1))
//                .andExpect(view().name("redirect:/projects"));
//        verify(projectService).deletingProject(1L);
//    }
//
////    @Test
////    void viewTasksOfProject() throws Exception {
//////        long millis = System.currentTimeMillis();
//////        Project p = new Project(1, "new project");
//////        Mockito.when(projectService.getTasksList(p.getProjectId()))
//////                .thenReturn(List.of(new Task(1L,"new task",p)));
//////        RequestBuilder requestBuilder = get("/viewtasksofproject/1")
//////                .accept(MediaType.TEXT_HTML_VALUE);
//////        mockMvc.perform(requestBuilder)
//////                .andExpect(MockMvcResultMatchers
//////                        .view()
//////                        .name("tasks"));
////
////    }
//
//    @Test
//    void createTaskOfProject() throws Exception {
//
////        mockMvc.perform(get("/createtaskofproject/"+1L))
////                .andExpect(view().name("createtasks"))
////                .andExpect(model().attributeExists());
//    }
//
//
//
//    ////////////////////////////////////////////////////
//    ///Arguements are different
//    @Test
//    void processTask() throws Exception {
//        when(taskService.processTask(task)).thenReturn(task);
//        mockMvc.perform(post("/processcreatetask"))
//                .andExpect(view()
//                        .name("redirect:/projects"));
//        verify(taskService).processTask(task);
//    }
//
////    @Test
////    void getSummary() {
////    }
//}