package ru.zainab.userservice.dto;

import ru.zainab.userservice.entity.UserRole;
import ru.zainab.userservice.entity.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        UserStatus status,
        UserRole role,
        LocalDateTime createdAt,
        Long version
) {
}
