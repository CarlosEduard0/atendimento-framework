package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.model.SenhaStatus;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoDesativarStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartorioRealocarAoDesativarStrategy implements RealocarAoDesativarStrategy {
    private final GuicheRepository guicheRepository;
    private final SenhaRepository senhaRepository;

    public CartorioRealocarAoDesativarStrategy(GuicheRepository guicheRepository, SenhaRepository senhaRepository) {
        this.guicheRepository = guicheRepository;
        this.senhaRepository = senhaRepository;
    }

    @Override
    public void realocarSenhasAoDesativar(Guiche guiche) {
        List<Guiche> guichesAtivos = guicheRepository.findByAtivoTrue();
        if(guichesAtivos.isEmpty()) {
            senhaRepository.descartarSenhas(guiche.getSenhasAguardando());
            throw new GuicheException("Senhas descartadas pois não existe nenhum guichê ativo.");
        }

        List<Senha> senhas = guiche.getSenhasAguardando();
        int senhasPorGuiche = senhas.size() / guichesAtivos.size();
        for(int i = 0; i < senhas.size(); i++) {
            senhas.get(i).setGuiche(guichesAtivos.get(i / senhasPorGuiche));
        }
        senhaRepository.saveAll(senhas);
    }
}
