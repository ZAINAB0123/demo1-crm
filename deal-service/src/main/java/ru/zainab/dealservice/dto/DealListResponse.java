package ru.zainab.dealservice.dto;

import java.math.BigDecimal;

public record DealListResponse(
       String title,
       BigDecimal amount,
       String customerName
) {
}
