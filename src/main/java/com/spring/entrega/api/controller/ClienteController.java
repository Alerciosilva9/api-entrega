package com.spring.entrega.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entrega.domain.model.Cliente;
import com.spring.entrega.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repository.findById(clienteId);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente, @PathVariable Long clienteId) {
		if(repository.existsById(clienteId)) {
			cliente.setId(clienteId);
			cliente = repository.save(cliente);
			return ResponseEntity.ok(cliente);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
		if(repository.existsById(clienteId)) {
			repository.deleteById(clienteId);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
