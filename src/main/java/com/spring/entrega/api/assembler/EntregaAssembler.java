package com.spring.entrega.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.entrega.api.model.EntregaModel;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.input.EntregaInput;

@Component
public class EntregaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
