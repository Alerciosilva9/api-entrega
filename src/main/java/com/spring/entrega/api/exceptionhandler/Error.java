package com.spring.entrega.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Error{

	private Integer Status;
	private OffsetDateTime dataHora;
	private List<Campo> campos; 
	
	public static class Campo{
		private String nome;
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		private String mensagem;
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
	}
	
	
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	private String titulo;
}
