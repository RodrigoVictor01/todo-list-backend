package com.example.todolist.service;

import com.example.todolist.dto.TarefaResponseDTO;
import com.example.todolist.enums.Prioridade;
import com.example.todolist.enums.Status;
import com.example.todolist.model.TarefaModel;
import com.example.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public List<TarefaResponseDTO> listarTarefas(Status status, Prioridade prioridade, String responsavel) {
        List<TarefaModel> tarefas;

        if (status != null) {
            tarefas = tarefaRepository.findByStatus(status);
        } else if (prioridade != null) {
            tarefas = tarefaRepository.findByPrioridade(prioridade);
        } else if (responsavel != null) {
            tarefas = tarefaRepository.findByResponsavel(responsavel);
        } else {
            tarefas = tarefaRepository.findAll();
        }

        return tarefas.stream()
                .map(TarefaResponseDTO::new)
                .toList();
    }

    @Override
    public Optional<TarefaModel> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    @Override
    public TarefaModel criarTarefa(TarefaModel tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @Override
    public Optional<TarefaModel> atualizarTarefa(Long id, TarefaModel tarefaAtualizada) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
            tarefa.setStatus(tarefaAtualizada.getStatus());
            tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            tarefa.setDeadline(tarefaAtualizada.getDeadline());
            return tarefaRepository.save(tarefa);
        });
    }

    @Override
    public Optional<TarefaModel> concluirTarefa(Long id) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setStatus(Status.CONCLUIDA);
            tarefa.setConcluida(true);
            return tarefaRepository.save(tarefa);
        });
    }

    @Override
    public boolean deletarTarefa(Long id) {
        Optional<TarefaModel> tarefaOpt = tarefaRepository.findById(id);

        if (tarefaOpt.isPresent()) {
            TarefaModel tarefa = tarefaOpt.get();

            if (tarefa.isConcluida() || tarefa.getStatus() == Status.CONCLUIDA) {
                throw new IllegalStateException("Não é possível deletar uma tarefa concluída");
            }

            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<String> listarResponsaveis() {
        return tarefaRepository.findDistinctResponsaveis();
    }
}
