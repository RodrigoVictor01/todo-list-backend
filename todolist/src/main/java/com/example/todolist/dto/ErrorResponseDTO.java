package com.example.todolist.dto;

public record ErrorResponseDTO(
        String message,
        int status,
        String timestamp
) {
}
