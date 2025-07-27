package com.example.todolist.controller;

import com.example.todolist.dto.UsuarioRequestDTO;
import com.example.todolist.dto.UsuarioResponseDTO;
import com.example.todolist.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        try {
            UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
            return ResponseEntity.status(201).body(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
