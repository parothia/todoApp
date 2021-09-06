package com.hatio.projectApp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

//@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;
    @Column(name = "title")
    private String title;

    @CreationTimestamp
    @Column(name = "created_date",nullable = false,updatable = false)
    private Date createdDate;

    @OneToMany(targetEntity = Task.class, mappedBy = "project", cascade = CascadeType.ALL)

    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    public Project(String eat_thrice) {
        this.title= eat_thrice;
    }

    public Project() {

    }

    public Project(Long i, String new_project) {
        this.projectId = i;
        this.title = new_project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
