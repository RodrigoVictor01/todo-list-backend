package com.example.todolist.dto;


import com.example.todolist.model.TarefaModel;

public record TarefaResponseDTO(Long id,
                                String titulo,
                                String descricao,
                                String responsavel,
                                String status,
                                String prioridade,
                                String deadline) {
    public TarefaResponseDTO(TarefaModel tarefa) {
        this(tarefa.getId(),
             tarefa.getTitulo(),
             tarefa.getDescricao(),
             tarefa.getResponsavel(),
             tarefa.getStatus().getDescricao(),
             tarefa.getPrioridade().getDescricao(),
             tarefa.getDeadline() != null ? tarefa.getDeadline().toString() : null);
    }
}
