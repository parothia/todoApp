package com.hatio.projectApp.entity;

import com.hatio.projectApp.Repo.TaskRepo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    private String description;
    private String status;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "p_fk",referencedColumnName = "id")
    private Project project;

    public Task() {

    }

    public Task(Long taskId, String description,  Project project) {
        this.taskId = taskId;
        this.description = description;
        this.project = project;
    }

    public Task(String description, Project project) {
        this.description = description;
        this.project = project;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }


    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", project=" + project +
                '}';
    }
}
