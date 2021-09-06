//package com.hatio.projectApp.Repo;
//
//import com.hatio.projectApp.entity.Task;
//import com.hatio.projectApp.service.TaskService;
//import org.junit.Before;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class TaskRepoTest {
//
//    @Autowired
//    TaskService taskService;
//    @Mock
//    TaskRepo taskRepo;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    @DisplayName("Get All projects")
//    public void getAllTasksTest() {
//        List<Task> tasks = new ArrayList<Task>();
//        long millis = System.currentTimeMillis();
//        Task t1 = new Task();
//        t1.setTaskId(1L);
//        t1.setCreatedDate(new Date(millis));
//        t1.setDescription("TTTTTTT");
//        tasks.add(t1);
//        System.out.println(tasks.size()+"\n");
//        try {
//            System.out.println(t1.getDescription()+t1.getTaskId()+t1.getCreatedDate());
//            when(taskRepo.findAll()).thenReturn(tasks);
//            assertEquals(1, tasks.size());
//            verify(taskRepo, times(0)).findAll();
//        } catch (NullPointerException e) {
//            e.printStackTrace ();
//            System.out.println("NULLLLLLL");
//        }
//    }
//
//}