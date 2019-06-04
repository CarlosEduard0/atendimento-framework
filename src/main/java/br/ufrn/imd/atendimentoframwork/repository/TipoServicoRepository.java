package br.ufrn.imd.atendimentoframwork.repository;

import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
}
