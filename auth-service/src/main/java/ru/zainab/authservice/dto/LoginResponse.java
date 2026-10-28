package ru.zainab.authservice.dto;

public record LoginResponse(
       String accessToken,
       Long expiresIn
) {
}
