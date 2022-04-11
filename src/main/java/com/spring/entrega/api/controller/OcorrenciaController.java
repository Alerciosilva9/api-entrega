package com.spring.entrega.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entrega.api.assembler.OcorrenciaAssembler;
import com.spring.entrega.api.model.OcorrenciaModel;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.Ocorrencia;
import com.spring.entrega.domain.model.input.OcorrenciaInput;
import com.spring.entrega.domain.service.BuscaEntregaService;
import com.spring.entrega.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	@Autowired
	private RegistroOcorrenciaService ocorrenciaService;
	
	@Autowired
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@Autowired
	private BuscaEntregaService entregaService;
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
		Entrega entrega = entregaService.buscar(entregaId);
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciainput) {
		Ocorrencia ocorrenciaRegistrada =  ocorrenciaService
				.registrar(entregaId, ocorrenciainput.getDescricao());
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}
}
