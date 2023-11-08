package com.example.gira.service.serviceImpl;

import com.example.gira.model.dto.CreateTaskDTO;
import com.example.gira.model.dto.TaskDTO;
import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.Task;
import com.example.gira.model.entity.User;
import com.example.gira.model.enums.Progress;
import com.example.gira.model.session.LoggedUser;
import com.example.gira.repository.ClassificationRepository;
import com.example.gira.repository.TaskRepository;
import com.example.gira.repository.UserRepository;
import com.example.gira.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ClassificationRepository classificationRepository;

    private UserRepository userRepository;

    private LoggedUser userSession;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ClassificationRepository classificationRepository, UserRepository userRepository, LoggedUser userSession) {
        this.taskRepository = taskRepository;
        this.classificationRepository = classificationRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    @Override
    public boolean nameExists(CreateTaskDTO createTaskDTO) {
        Optional<Task> optionalTask = this.taskRepository.findByName(createTaskDTO.getName());
        return optionalTask.isPresent();
    }

    @Override
    public void register(CreateTaskDTO createTaskDTO) {
        Classification classification = this.classificationRepository
                .findByName(createTaskDTO.getClassification());
        User user = this.userRepository.findById(this.userSession.getId()).get();

        Task task = new Task(createTaskDTO.getName(),
                createTaskDTO.getDescription(),
                Progress.OPEN,
                createTaskDTO.getDueDate(),
                classification,
                user);
        this.taskRepository.save(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        return tasks.stream().map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void progress(Long id) {
        Task task = this.taskRepository.findById(id).get();
        if (task.getProgress().equals(Progress.OPEN)) {
            task.setProgress(Progress.IN_PROGRESS);
            this.taskRepository.save(task);
        } else if (task.getProgress().equals(Progress.IN_PROGRESS)) {
            task.setProgress(Progress.COMPLETED);
            this.taskRepository.save(task);
        } else if (task.getProgress().equals(Progress.COMPLETED)) {
            this.taskRepository.delete(task);
        }
    }
}
