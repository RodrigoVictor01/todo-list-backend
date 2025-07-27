package com.example.todolist.dto;

public record AuthResponseDTO(
        String token,
        String type,
        String username,
        Long expiresIn) {
    public AuthResponseDTO(String token, String username, Long expiresIn) {
        this(token, "Bearer", username, expiresIn);
    }
}
