package com.spring.entrega.domain.model.input;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
