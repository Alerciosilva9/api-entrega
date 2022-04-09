package com.spring.entrega.domain.service;


import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.spring.entrega.domain.model.Cliente;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.StatusEntrega;
import com.spring.entrega.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	@Autowired
	private EntregaRepository repository;
	
	@Autowired
	private CrudClienteService clienteService;
	
	
	@Transactional
	public Entrega Solicitar(Entrega entrega){
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setData_pedido(OffsetDateTime.now());
		System.out.print(entrega.toString());
		return repository.save(entrega);
	}
}
