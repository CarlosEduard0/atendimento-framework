package br.ufrn.imd.atendimentoframwork.repository;

import br.ufrn.imd.atendimentoframwork.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
