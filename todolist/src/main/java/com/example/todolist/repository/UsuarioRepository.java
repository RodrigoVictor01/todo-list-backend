package com.example.todolist.repository;

import com.example.todolist.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
