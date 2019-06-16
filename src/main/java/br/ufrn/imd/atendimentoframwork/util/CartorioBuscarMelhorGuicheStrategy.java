package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.BuscarMelhorGuicheStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartorioBuscarMelhorGuicheStrategy implements BuscarMelhorGuicheStrategy {
    private final GuicheRepository guicheRepository;

    @Autowired
    public CartorioBuscarMelhorGuicheStrategy(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
    }

    @Override
    public Guiche buscarMelhorGuiche(TipoServico tipoServico) {
        List<Guiche> guichesAtivos = guicheRepository.findByAtivoTrue();
        Guiche guiche;

        if(guichesAtivos.isEmpty()) {
            throw new GuicheException("Não há guichês ativos");
        } else {
            guiche = guichesAtivos.get(0);
        }

        for(Guiche g : guichesAtivos) {
            if(g.getSenhasAguardando().size() < guiche.getSenhasAguardando().size()) {
                guiche = g;
            }
        }

        return guiche;
    }
}
