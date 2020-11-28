package br.com.algaworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.domain.exception.NegocioException;
import br.com.algaworks.domain.model.Cliente;
import br.com.algaworks.domain.ropository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email!");
		}

		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(Long clienteId, Cliente cliente) {
		cliente.setId(clienteId);
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
