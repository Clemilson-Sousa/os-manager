package br.com.algaworks.controller;

import br.com.algaworks.api.model.ComentarioInput;
import br.com.algaworks.api.model.ComentarioModel;
import br.com.algaworks.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.domain.model.Comentario;
import br.com.algaworks.domain.model.OrdemServico;
import br.com.algaworks.domain.ropository.OrdemServicoRepositorio;
import br.com.algaworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordem-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepositorio ordemServicoRepositorio;

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepositorio.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada!"));
        return toCollectionModel(ordemServico.getComentarios());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@Valid @PathVariable Long ordemServicoId, @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
            return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
    }
}
