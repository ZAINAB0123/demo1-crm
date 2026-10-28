package ru.zainab.taskservice.dto;


import ru.zainab.taskservice.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        Long version

) {

}
