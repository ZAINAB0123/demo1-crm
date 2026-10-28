package ru.zainab.taskservice.mapper;

import org.springframework.stereotype.Component;
import ru.zainab.taskservice.dto.TaskCreateRequest;
import ru.zainab.taskservice.dto.TaskResponse;
import ru.zainab.taskservice.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    public Task toEntity(TaskCreateRequest request) {
        return new Task(
                request.title(),
                request.description()
        );
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getVersion()
        );
    }

    public List<TaskResponse> toResponseList(List<Task> tasks) {
        List<TaskResponse> responseList = new ArrayList<>();
        for (Task task : tasks) {
            responseList.add(toResponse(task));
        }
        return responseList;
    }

}
