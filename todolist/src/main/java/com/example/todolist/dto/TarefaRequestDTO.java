package com.example.todolist.dto;

import com.example.todolist.enums.Prioridade;
import com.example.todolist.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TarefaRequestDTO(
        @NotBlank(message = "Título é obrigatório") @Size(max = 255, message = "Título deve ter no máximo 255 caracteres") String titulo,

        @NotBlank(message = "Descrição é obrigatória") @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres") String descricao,

        @NotBlank(message = "Responsável é obrigatório") String responsavel,

        Status status,

        @NotNull(message = "Prioridade é obrigatória") Prioridade prioridade,

        @NotNull(message = "Deadline é obrigatório") LocalDate deadline) {
}
