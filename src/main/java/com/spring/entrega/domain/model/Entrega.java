package com.spring.entrega.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.spring.entrega.domain.exception.NegocioException;

@Entity
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne
	private Cliente cliente;

	@Embedded
	private Destinatario destinatario;
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	

	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	

	private OffsetDateTime data_pedido; 
	private OffsetDateTime data_finalizacao;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Destinatario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	public StatusEntrega getStatus() {
		return status;
	}
	public void setStatus(StatusEntrega status) {
		this.status = status;
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
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public Ocorrencia adicionaOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		this.getOcorrencias().add(ocorrencia);
		return ocorrencia;
	}
	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	public void finalizar() {
		if(!podeserfinalizada()) {
			throw new NegocioException("Entrega nao pode ser finalizada");
		}
		setStatus(StatusEntrega.FINALIZADA);
		setData_finalizacao(OffsetDateTime.now());
	}
	
	public boolean podeserfinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	
}
