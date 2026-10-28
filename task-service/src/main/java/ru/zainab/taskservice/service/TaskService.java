package ru.zainab.taskservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zainab.taskservice.dto.TaskCreateRequest;
import ru.zainab.taskservice.dto.TaskResponse;
import ru.zainab.taskservice.dto.TaskUpdateRequest;
import ru.zainab.taskservice.entity.Task;
import ru.zainab.taskservice.exception.TaskNotFoundException;
import ru.zainab.taskservice.mapper.TaskMapper;
import ru.zainab.taskservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.toEntity(request);
        Task taskSaved = taskRepository.save(task);
        return taskMapper.toResponse(taskSaved);
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id " + id));
        task.setTitle(request.title());
        task.setDescription(request.description());
        return taskMapper.toResponse(task);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id " + id));
        return taskMapper.toResponse(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskMapper.toResponseList(taskRepository.findAll());
    }

    @Transactional()
    public void deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }
}
