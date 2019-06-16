package br.ufrn.imd.atendimentoframwork.repository;

import br.ufrn.imd.atendimentoframwork.model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SenhaRepository extends JpaRepository<Senha, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Senha s SET s.status = br.ufrn.imd.atendimentoframwork.model.SenhaStatus.DESCARTADA WHERE s IN ?1")
    void descartarSenhas(List<Senha> senhas);
}
