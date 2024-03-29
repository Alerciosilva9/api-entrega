package com.spring.entrega.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.entrega.api.model.OcorrenciaModel;
import com.spring.entrega.domain.model.Ocorrencia;

@Component
public class OcorrenciaAssembler {

	@Autowired
	 private ModelMapper modelMapper;
	
	 public OcorrenciaModel toModel(Ocorrencia ocorrencia){
		 return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	 }
	 
	 public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
		 return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	 }
}
