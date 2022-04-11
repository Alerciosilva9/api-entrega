package com.spring.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.StatusEntrega;
import com.spring.entrega.domain.repository.EntregaRepository;

@Service
public class FinalizacaoEntregaService {
	
	@Autowired
	private BuscaEntregaService buscaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizarEntrega(Long entregaId) {
		Entrega entrega = buscaService.buscar(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
