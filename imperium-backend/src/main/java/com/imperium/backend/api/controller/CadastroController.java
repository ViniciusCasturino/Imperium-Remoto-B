package com.imperium.backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imperium.backend.dto.CadastroRequest;

@RestController
@RequestMapping("/api")
public class CadastroController {

    private final ObjectMapper objectMapper;
    private final List<CadastroRequest> cadastros = new ArrayList<>();

    public CadastroController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/cadastros")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastroRequest request) {
        if (!request.getSenha().equals(request.getConfirmarSenha())) {
            return ResponseEntity.badRequest().body("As senhas não coincidem.");
        }
      
        boolean emailJaExiste = cadastros.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));

        if (emailJaExiste) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }
       
        cadastros.add(request);
        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }

    @GetMapping("/cadastros")
    public ResponseEntity<List<CadastroRequest>> listarCadastros() {
        return ResponseEntity.ok(cadastros);
    }
}
