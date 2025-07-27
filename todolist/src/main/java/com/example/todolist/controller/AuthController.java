package com.example.todolist.controller;

import com.example.todolist.dto.AuthResponseDTO;
import com.example.todolist.dto.LoginRequestDTO;
import com.example.todolist.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        AuthResponseDTO authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

}
