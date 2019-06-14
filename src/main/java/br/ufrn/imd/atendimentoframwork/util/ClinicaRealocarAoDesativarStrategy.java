package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoDesativarStrategy;
import org.springframework.stereotype.Component;

@Component
public class ClinicaRealocarAoDesativarStrategy implements RealocarAoDesativarStrategy {
    @Override
    public void realocarSenhasAoDesativar(Guiche guiche) {

    }
}
