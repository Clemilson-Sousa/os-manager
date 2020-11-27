package br.com.algaworks.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return repositorio();
	}

	public static List<Cliente> repositorio() {

		var c1 = new Cliente();
		c1.setId(1L);
		c1.setNome("Clemilson");
		c1.setEmail("clemilson@gmail.com");
		c1.setTelefone("99 99999-9999");

		var c2 = new Cliente();
		c2.setId(2L);
		c2.setNome("Kamila");
		c2.setEmail("kamila@gmail.com");
		c2.setTelefone("99 88888-8888");

		var c3 = new Cliente();
		c3.setId(3L);
		c3.setNome("Socorro");
		c3.setEmail("socorro@gmail.com");
		c3.setTelefone("99 77777-7777");

		return Arrays.asList(c1, c2, c3);
	}
}
