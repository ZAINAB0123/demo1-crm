package ru.zainab.userservice.dto;

public record LoginResponse(
       String accessToken,
       Long expiresIn
) {
}
