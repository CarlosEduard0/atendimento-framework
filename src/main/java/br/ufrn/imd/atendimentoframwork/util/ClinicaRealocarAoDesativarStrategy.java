package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.SenhaStatus;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoDesativarStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicaRealocarAoDesativarStrategy implements RealocarAoDesativarStrategy {
    private final SenhaRepository senhaRepository;

    @Autowired
    public ClinicaRealocarAoDesativarStrategy(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    @Override
    public void realocarSenhasAoDesativar(Guiche guiche) {
        guiche.getSenhasAguardando().forEach(s -> s.setStatus(SenhaStatus.DESCARTADA));
        senhaRepository.saveAll(guiche.getSenhasAguardando());
    }
}
