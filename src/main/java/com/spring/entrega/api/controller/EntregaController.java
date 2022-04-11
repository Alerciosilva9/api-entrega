package com.spring.entrega.api.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entrega.api.assembler.EntregaAssembler;
import com.spring.entrega.api.model.EntregaModel;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.input.EntregaInput;
import com.spring.entrega.domain.repository.EntregaRepository;
import com.spring.entrega.domain.service.FinalizacaoEntregaService;
import com.spring.entrega.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService entregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@Autowired
	private FinalizacaoEntregaService finalizarentregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaInput entrega){
		Entrega NovaEntrega = entregaAssembler.toEntity(entrega);
		return entregaAssembler.toModel(entregaService.Solicitar(NovaEntrega));
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
		
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
			return entregaRepository.findById(entregaId).map(entrega ->	 ResponseEntity.ok(entregaAssembler.toModel(entrega))).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizarentregaService.finalizarEntrega(entregaId);
	}
	
}
	
