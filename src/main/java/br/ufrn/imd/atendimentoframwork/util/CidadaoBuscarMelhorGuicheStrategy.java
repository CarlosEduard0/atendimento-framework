package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.BuscarMelhorGuicheStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadaoBuscarMelhorGuicheStrategy implements BuscarMelhorGuicheStrategy {
    private final GuicheRepository guicheRepository;

    @Autowired
    public CidadaoBuscarMelhorGuicheStrategy(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
    }

    @Override
    public Guiche buscarMelhorGuiche(TipoServico tipoServico) {
        List<Guiche> guichesAtivosEMesmoTipoServico = guicheRepository.findByAtivoTrueAndTipoServico(tipoServico);
        Guiche guiche = guichesAtivosEMesmoTipoServico.get(0);
        for(Guiche g : guichesAtivosEMesmoTipoServico) {
            if(g.getSenhas().size() < guiche.getSenhas().size()) {
                guiche = g;
            }
        }
        return guiche;
    }
}
