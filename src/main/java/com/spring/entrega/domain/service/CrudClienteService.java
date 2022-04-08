package com.spring.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entrega.domain.exception.NegocioException;
import com.spring.entrega.domain.model.Cliente;
import com.spring.entrega.domain.repository.ClienteRepository;


@Service
public class CrudClienteService{

	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Boolean emailEmUso = repository.findByEmail(cliente.getEmail())
				.stream().anyMatch(clienteExistente-> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Ja existe cliente cadastrado com o email");
		}
		return repository.save(cliente);
	}
	
	public Cliente buscar(Long clienteId) {
		return repository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("cliente nao encontrado"));
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
