package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoAtivarStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CidadaoRealocarAoAtivarStrategy implements RealocarAoAtivarStrategy {
    private final GuicheRepository guicheRepository;
    private final SenhaRepository senhaRepository;

    @Autowired
    public CidadaoRealocarAoAtivarStrategy(GuicheRepository guicheRepository, SenhaRepository senhaRepository) {
        this.guicheRepository = guicheRepository;
        this.senhaRepository = senhaRepository;
    }

    @Override
    public void realocarSenhasAoAtivar(Guiche guiche) {
        List<Guiche> guichesAtivosDoTipoDeServico = guicheRepository.findByAtivoTrueAndTipoServico(guiche.getTipoServico());
        if(!guichesAtivosDoTipoDeServico.isEmpty()) {
            List<Senha> senhas = new ArrayList<>();
            for(Guiche g : guichesAtivosDoTipoDeServico) {
                senhas.addAll(g.getSenhas());
            }
            int numParts = senhas.size() / (guichesAtivosDoTipoDeServico.size() + 1);
            for(int i = 0; i < senhas.size(); i++) {
                senhas.get(i).setGuiche(guichesAtivosDoTipoDeServico.get(i % numParts));
            }
            senhaRepository.saveAll(senhas);
        }
    }
}
