package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.model.SenhaStatus;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoAtivarStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartorioRealocarAoAtivarStrategy implements RealocarAoAtivarStrategy {
    private final GuicheRepository guicheRepository;
    private final SenhaRepository senhaRepository;

    public CartorioRealocarAoAtivarStrategy(GuicheRepository guicheRepository, SenhaRepository senhaRepository) {
        this.guicheRepository = guicheRepository;
        this.senhaRepository = senhaRepository;
    }

    @Override
    public void realocarSenhasAoAtivar(Guiche guiche) {
        List<Guiche> guichesAtivos = guicheRepository.findByAtivoTrue();
        if(!guichesAtivos.isEmpty()) {
            List<Senha> senhas = new ArrayList<>();
            for(Guiche g : guichesAtivos) {
                senhas.addAll(g.getSenhasAguardando().subList(g.getSenhasAguardando().size() - (int) (g.getSenhasAguardando().size() * 0.2), g.getSenhas().size()));
            }
            for(Senha s : senhas) {
                s.setGuiche(guiche);
            }
            senhaRepository.saveAll(senhas);
        }
    }
}
