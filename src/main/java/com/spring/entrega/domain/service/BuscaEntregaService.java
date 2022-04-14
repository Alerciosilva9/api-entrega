package com.spring.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entrega.domain.exception.EntidadeNaoEncontradaException;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaid) {
		return 	entregaRepository.findById(entregaid).orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega nao encontrada") );

	}
}
