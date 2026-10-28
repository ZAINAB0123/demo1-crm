package ru.zainab.taskservice.dto;


import ru.zainab.taskservice.entity.TaskStatus;

public record TaskFilterRequest(
        String title,
        TaskStatus status,
        Integer minDeals,
        Integer maxDeals
) {
}
