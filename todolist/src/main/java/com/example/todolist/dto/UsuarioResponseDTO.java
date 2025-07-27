package com.example.todolist.dto;

import com.example.todolist.model.UsuarioModel;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String username,
        Boolean ativo,
        String dataCriacao) {
    public UsuarioResponseDTO(UsuarioModel usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getAtivo(),
                usuario.getDataCriacao() != null ? usuario.getDataCriacao().toString() : null);
    }
}
