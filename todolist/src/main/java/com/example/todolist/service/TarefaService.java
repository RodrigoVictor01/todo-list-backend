package com.example.todolist.service;

import com.example.todolist.dto.TarefaResponseDTO;
import com.example.todolist.enums.Prioridade;
import com.example.todolist.enums.Status;
import com.example.todolist.model.TarefaModel;

import java.util.List;
import java.util.Optional;

public interface TarefaService {
    List<TarefaResponseDTO> listarTarefas(Status status, Prioridade prioridade, String responsavel);

    Optional<TarefaModel> buscarPorId(Long id);

    TarefaModel criarTarefa(TarefaModel tarefa);

    Optional<TarefaModel> atualizarTarefa(Long id, TarefaModel tarefaAtualizada);

    Optional<TarefaModel> concluirTarefa(Long id);

    boolean deletarTarefa(Long id);

    List<String> listarResponsaveis();
}
