package br.com.algaworks.controller;

import br.com.algaworks.api.model.ComentarioInput;
import br.com.algaworks.api.model.ComentarioModel;
import br.com.algaworks.domain.model.Comentario;
import br.com.algaworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordem-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@Valid @PathVariable Long ordemServicoId, @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }
}
