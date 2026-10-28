package ru.zainab.customerservice.dto;
import ru.zainab.customerservice.entity.CustomerStatus;


public record CustomerFilterRequest(
        String name,
        CustomerStatus status,
        String title
) {
}
