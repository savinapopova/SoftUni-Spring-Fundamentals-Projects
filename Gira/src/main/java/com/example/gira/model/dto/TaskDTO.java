package com.example.gira.model.dto;

import com.example.gira.model.entity.Task;
import com.example.gira.model.enums.ClassificationName;
import com.example.gira.model.enums.Progress;

import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    private String name;

    private String assignedTo;

    private String classification;

    private LocalDate dueDate;

    private String progress;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.assignedTo = task.getUser().getUsername();
        this.classification = String.valueOf(task.getClassification().getName());
        this.dueDate = task.getDueDate();
        this.progress = String.valueOf(task.getProgress());
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public TaskDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskDTO setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public String getClassification() {
        return classification;
    }

    public TaskDTO setClassification(String classification) {
        this.classification = classification;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getProgress() {
        return progress;
    }

    public TaskDTO setProgress(String progress) {
        this.progress = progress;
        return this;
    }
}
