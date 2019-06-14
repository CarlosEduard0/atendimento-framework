package br.ufrn.imd.atendimentoframwork.util;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.BuscarMelhorGuicheStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicaBuscarMelhorGuicheStrategy implements BuscarMelhorGuicheStrategy {
    private final GuicheRepository guicheRepository;

    @Autowired
    public ClinicaBuscarMelhorGuicheStrategy(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
    }

    @Override
    public Guiche buscarMelhorGuiche(TipoServico tipoServico) {
        return guicheRepository.findByAtivoTrueAndTipoServico(tipoServico).get(0);
    }
}
