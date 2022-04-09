 package com.spring.entrega.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.spring.entrega.domain.model.StatusEntrega;

public class EntregaModel {

	private Long id;
	private String nomeCliente;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime data_pedido;
	private OffsetDateTime data_finalizacao;
	private DestinatarioModel destinatario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public StatusEntrega getStatus() {
		return status;
	}
	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
	
	public DestinatarioModel getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(DestinatarioModel destinatario) {
		this.destinatario = destinatario;
	}
	public OffsetDateTime getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(OffsetDateTime data_pedido) {
		this.data_pedido = data_pedido;
	}
	public OffsetDateTime getData_finalizacao() {
		return data_finalizacao;
	}
	public void setData_finalizacao(OffsetDateTime data_finalizacao) {
		this.data_finalizacao = data_finalizacao;
	}
	
	
	
}
