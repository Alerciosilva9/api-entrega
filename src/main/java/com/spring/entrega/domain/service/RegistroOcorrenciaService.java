package com.spring.entrega.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.entrega.domain.model.Entrega;
import com.spring.entrega.domain.model.Ocorrencia;


@Service
public class RegistroOcorrenciaService {
	@Autowired
	private BuscaEntregaService buscarentregaService;
	 
	@Transactional
	public Ocorrencia registrar(Long entregaid, String descricao) {
		Entrega entrega = buscarentregaService.buscar(entregaid);
		return entrega.adicionaOcorrencia(descricao);
	}
}
