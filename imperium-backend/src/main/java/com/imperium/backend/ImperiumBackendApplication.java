package com.imperium.backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ApiController {
	
	
	private List<String> cadastros = new ArrayList<>();
	
	private ObjectMapper objectMapper;
	
	public ApiController(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@GetMapping(path = "/cadastros")
	public ResponseEntity<String> cadastroCliente() throws JsonProcessingException{
	
		return ResponseEntity.ok(objectMapper.writeValueAsString(cadastros));
	}
	
	@PostMapping(path = "/cadastros")
	public ResponseEntity<Void> createClient(@RequestBody String cadastro){
		cadastros.add(cadastro);
		return ResponseEntity.ok().build();
				
	}
	
	public ApiController(List<String> cadastro) {
		super();
		this.cadastros = cadastro;
	}

}
