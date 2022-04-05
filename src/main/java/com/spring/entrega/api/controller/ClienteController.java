package com.spring.entrega.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entrega.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1l);
		cliente1.setEmail("antonio@gmail");
		cliente1.setNome("antonio");
		cliente1.setTelefone("400289");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(1l);
		cliente2.setEmail("maria@gmail");
		cliente2.setNome("maria");
		cliente2.setTelefone("400289");
		
		return Arrays.asList(cliente1,cliente2);
	}
}
