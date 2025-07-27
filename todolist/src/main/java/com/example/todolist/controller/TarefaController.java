package com.example.todolist.controller;

import com.example.todolist.dto.TarefaRequestDTO;
import com.example.todolist.dto.TarefaResponseDTO;
import com.example.todolist.enums.Prioridade;
import com.example.todolist.enums.Status;
import com.example.todolist.model.TarefaModel;
import com.example.todolist.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/listar")
    public List<TarefaResponseDTO> listarTarefas(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Prioridade prioridade,
            @RequestParam(required = false) String responsavel) {
        return tarefaService.listarTarefas(status, prioridade, responsavel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<TarefaModel> tarefaOpt = tarefaService.buscarPorId(id);
        return tarefaOpt.map(tarefa -> ResponseEntity.ok(new TarefaResponseDTO(tarefa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@Valid @RequestBody TarefaRequestDTO tarefaDTO) {
        TarefaModel tarefa = new TarefaModel();
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setResponsavel(tarefaDTO.responsavel());
        tarefa.setStatus(tarefaDTO.status() != null ? tarefaDTO.status() : Status.PENDENTE);
        tarefa.setPrioridade(tarefaDTO.prioridade());
        tarefa.setDeadline(tarefaDTO.deadline());
        tarefa.setConcluida(false);

        TarefaModel novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.status(201).body(new TarefaResponseDTO(novaTarefa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(@PathVariable Long id,
            @Valid @RequestBody TarefaModel tarefaAtualizada) {
        Optional<TarefaModel> tarefaOpt = tarefaService.atualizarTarefa(id, tarefaAtualizada);
        return tarefaOpt.map(tarefa -> ResponseEntity.ok(new TarefaResponseDTO(tarefa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponseDTO> concluirTarefa(@PathVariable Long id) {
        Optional<TarefaModel> tarefaOpt = tarefaService.concluirTarefa(id);
        return tarefaOpt.map(tarefa -> ResponseEntity.ok(new TarefaResponseDTO(tarefa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable Long id) {
        try {
            boolean deletado = tarefaService.deletarTarefa(id);
            if (deletado) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/prioridades")
    public ResponseEntity<String[]> listarPrioridades() {
        String[] prioridades = Arrays.stream(Prioridade.values())
                .map(Enum::name)
                .toArray(String[]::new);
        return ResponseEntity.ok(prioridades);
    }

    @GetMapping("/status")
    public ResponseEntity<String[]> listarStatus() {
        String[] status = Arrays.stream(Status.values())
                .map(Enum::name)
                .toArray(String[]::new);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/responsaveis")
    public ResponseEntity<List<String>> listarResponsaveis() {
        List<String> responsaveis = tarefaService.listarResponsaveis();
        return ResponseEntity.ok(responsaveis);
    }
}
