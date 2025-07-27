package com.example.todolist.service;

import com.example.todolist.dto.AuthResponseDTO;
import com.example.todolist.dto.LoginRequestDTO;
import com.example.todolist.dto.UsuarioRequestDTO;
import com.example.todolist.dto.UsuarioResponseDTO;
import com.example.todolist.model.UsuarioModel;
import com.example.todolist.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByUsername(loginRequest.username());

        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        UsuarioModel usuario = usuarioOpt.get();

        if (!usuario.getAtivo()) {
            throw new IllegalArgumentException("Usuário inativo");
        }
        if (!passwordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        String token = jwtService.generateToken(usuario.getUsername());

        return new AuthResponseDTO(token, usuario.getUsername(), 86400000L);
    }

    public UsuarioResponseDTO register(UsuarioRequestDTO usuarioRequest) {
        return usuarioService.criarUsuario(usuarioRequest);
    }

    public boolean validateToken(String token) {
        try {
            String username = jwtService.extractUsername(token);
            Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByUsername(username);

            if (usuarioOpt.isEmpty()) {
                return false;
            }

            return jwtService.isTokenValid(token, username) && usuarioOpt.get().getAtivo();
        } catch (Exception e) {
            return false;
        }
    }
}
