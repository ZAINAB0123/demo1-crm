package ru.zainab.userservice.dto.error;

public record FieldErrorResponse (
        String field,
        String message
){
}
