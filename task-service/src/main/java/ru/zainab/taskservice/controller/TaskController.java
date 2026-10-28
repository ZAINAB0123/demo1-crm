package ru.zainab.taskservice.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zainab.taskservice.dto.TaskCreateRequest;
import ru.zainab.taskservice.dto.TaskResponse;
import ru.zainab.taskservice.dto.TaskUpdateRequest;
import ru.zainab.taskservice.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(
            @RequestBody TaskCreateRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request
    ) {
        TaskResponse taskUpdate = taskService.updateTask(id, request);
        return ResponseEntity.ok(taskUpdate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskId(
            @PathVariable Long id
    ) {
        TaskResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> response = taskService.getAllTasks();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
