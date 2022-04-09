package com.spring.entrega.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entrega.api.model.EntregaModel;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.repository.EntregaRepository;
import com.spring.entrega.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService entregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega){
		return entregaService.Solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
			return entregaRepository.findById(entregaId).map(entrega ->	 {
				EntregaModel entregamodel = new EntregaModel();
				entregamodel.setId(entregaId);
				entregamodel.setNomeCliente(entrega.getCliente().getNome());
				
				entregamodel.setTaxa(entrega.getTaxa());
				
				entregamodel.getDestinatario().setNome(entrega.getDestinatario().getNome());
				entregamodel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
				entregamodel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
				entregamodel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
				entregamodel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
				entregamodel.setStatus(entrega.getStatus());
				entregamodel.setDataPedido(entrega.getData_pedido());
				entregamodel.setDataFinalizacao(entrega.getData_finalizacao());
				return ResponseEntity.ok(entregamodel);			
			}
			).orElse(ResponseEntity.notFound().build());
		}
	
}
	
