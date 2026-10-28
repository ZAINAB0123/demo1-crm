package ru.zainab.dealservice.dto;
public record DealWithCustomerResponse(
        Long id,
        String title,
        String customerName
) {
}
