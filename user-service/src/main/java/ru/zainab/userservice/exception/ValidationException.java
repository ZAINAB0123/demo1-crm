package ru.zainab.userservice.exception;


import ru.zainab.userservice.dto.error.FieldErrorResponse;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<FieldErrorResponse> errors;

    public ValidationException(List<FieldErrorResponse> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }
}
