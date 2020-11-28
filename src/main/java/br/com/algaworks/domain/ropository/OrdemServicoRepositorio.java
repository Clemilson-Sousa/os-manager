package br.com.algaworks.domain.ropository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.domain.model.OrdemServico;

@Repository
public interface OrdemServicoRepositorio extends JpaRepository<OrdemServico, Long> {

}
