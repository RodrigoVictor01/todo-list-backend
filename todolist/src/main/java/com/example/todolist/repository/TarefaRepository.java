package com.example.todolist.repository;

import com.example.todolist.enums.Prioridade;
import com.example.todolist.enums.Status;
import com.example.todolist.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
    List<TarefaModel> findByStatus(Status status);

    List<TarefaModel> findByPrioridade(Prioridade prioridade);

    List<TarefaModel> findByResponsavel(String responsavel);

    Optional<TarefaModel> findByTitulo(String titulo);

    @Query("SELECT DISTINCT t.responsavel FROM TarefaModel t WHERE t.responsavel IS NOT NULL")
    List<String> findDistinctResponsaveis();
}
