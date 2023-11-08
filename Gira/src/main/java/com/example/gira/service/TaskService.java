package com.example.gira.service;

import com.example.gira.model.dto.CreateTaskDTO;
import com.example.gira.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    boolean nameExists(CreateTaskDTO createTaskDTO);

    void register(CreateTaskDTO createTaskDTO);

    List<TaskDTO> getAllTasks();

    void progress(Long id);
}
