package br.com.algaworks.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.domain.model.OrdemServico;
import br.com.algaworks.domain.ropository.OrdemServicoRepositorio;
import br.com.algaworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
	
	@Autowired
	GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	OrdemServicoRepositorio ordemServicoRepositorio;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdemServico savar(@RequestBody @Valid OrdemServico ordemServico) {
		return gestaoOrdemServicoService.criar(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return ordemServicoRepositorio.findAll();
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
		
		Optional<OrdemServico> ordemServico = ordemServicoRepositorio.findById(ordemServicoId);
		
		if (ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServico.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}