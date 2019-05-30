package br.ufrn.imd.atendimentoframwork.repository;

import br.ufrn.imd.atendimentoframwork.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> { }
