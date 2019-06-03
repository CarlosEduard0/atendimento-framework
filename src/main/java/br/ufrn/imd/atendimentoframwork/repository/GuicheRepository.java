package br.ufrn.imd.atendimentoframwork.repository;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuicheRepository extends JpaRepository<Guiche, Long> {
    List<Guiche> findByAtivoTrue();
}
