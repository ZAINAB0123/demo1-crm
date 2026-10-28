package ru.zainab.dealservice.dto;

import ru.zainab.dealservice.entity.DealStatus;
import java.math.BigDecimal;


public record DealFilterRequest(
    String title,
    DealStatus status,
    BigDecimal minAmount,
    BigDecimal maxAmount,
    String customerName
)
{
}
