package dev.tolana.exambackend.exception;

public record ErrorResponse(int status, String errorMessage) {
}
