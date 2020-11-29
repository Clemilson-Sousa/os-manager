package br.com.algaworks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.algaworks.api.model.OrdemServicoInput;
import br.com.algaworks.api.model.OrdemServicoModel;
import org.modelmapper.ModelMapper;
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

	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdemServicoModel criar(@RequestBody @Valid OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return toModel(gestaoOrdemServicoService.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar() {
		return toCollectionModel(ordemServicoRepositorio.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
		
		Optional<OrdemServico> ordemServico = ordemServicoRepositorio.findById(ordemServicoId);
		
		if (ordemServico.isPresent()) {
			OrdemServicoModel ordemServicoModel = toModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoModel);
		}
		
		return ResponseEntity.notFound().build();
	}

	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}

	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServicos) {
		return ordemServicos.stream().map(ordemServico -> toModel(ordemServico)).collect(Collectors.toList());
	}

	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
}