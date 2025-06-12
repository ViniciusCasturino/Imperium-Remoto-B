package com.imperium.backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imperium.backend.dto.EnderecoRequest;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final List<EnderecoRequest> enderecos = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody EnderecoRequest request) {

        if (request.getCep() == null || request.getRua() == null ||
            request.getBairro() == null || request.getNumero() == null) {
            return ResponseEntity.badRequest().body("Todos os campos obrigatórios devem ser preenchidos.");
        }

        enderecos.add(request);
        return ResponseEntity.ok("Endereço cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<EnderecoRequest>> listarEnderecos() {
        return ResponseEntity.ok(enderecos);
    }
}
