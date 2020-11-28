package br.com.algaworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.domain.exception.NegocioException;
import br.com.algaworks.domain.model.Cliente;
import br.com.algaworks.domain.model.OrdemServico;
import br.com.algaworks.domain.model.StatusOrdemServico;
import br.com.algaworks.domain.ropository.ClienteRepository;
import br.com.algaworks.domain.ropository.OrdemServicoRepositorio;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepositorio ordemServicoRepositorio;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepositorio.save(ordemServico);
	}

}
