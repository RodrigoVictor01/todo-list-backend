package com.example.todolist.exception;

import com.example.todolist.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();

        if (message.contains("Usuário não encontrado") ||
                message.contains("Senha incorreta") ||
                message.contains("Usuário inativo")) {
            message = "Usuário ou senha inválidos";
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Erro interno do servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
