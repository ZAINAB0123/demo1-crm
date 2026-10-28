package ru.zainab.customerservice.dto;

import ru.zainab.customerservice.entity.CustomerStatus;


import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        CustomerStatus status,
        LocalDateTime createdAt,
        Long version
) {
}
