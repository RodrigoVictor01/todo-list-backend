package com.example.todolist.service;

import com.example.todolist.dto.UsuarioRequestDTO;
import com.example.todolist.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuarios();

    UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO);

}
