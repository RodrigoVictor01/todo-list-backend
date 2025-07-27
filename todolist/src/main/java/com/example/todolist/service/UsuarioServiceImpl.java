package com.example.todolist.service;

import com.example.todolist.dto.UsuarioRequestDTO;
import com.example.todolist.dto.UsuarioResponseDTO;
import com.example.todolist.model.UsuarioModel;
import com.example.todolist.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Override
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.email())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        if (usuarioRepository.existsByUsername(usuarioDTO.username())) {
            throw new IllegalArgumentException("Username já está em uso");
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setUsername(usuarioDTO.username());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setAtivo(true);

        UsuarioModel usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

}
