package com.imperium.backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/cadastros/{email}")
    public ResponseEntity<?> listarCadastroPorEmail(@PathVariable String email) {
        return cadastros.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Cadastro com o email informado não foi encontrado."));
    }

    
    @DeleteMapping("/cadastros/{email}")
    public ResponseEntity<String> deletarCadastro(@PathVariable String email) {
        boolean removido = cadastros.removeIf(c -> c.getEmail().equalsIgnoreCase(email));

        if (removido) {
            return ResponseEntity.ok("Cadastro removido com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Cadastro com o email informado não foi encontrado.");
        }
    }

}
