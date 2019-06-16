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
public class ClinicaBuscarMelhorGuicheStrategy implements BuscarMelhorGuicheStrategy {
    private final GuicheRepository guicheRepository;

    @Autowired
    public ClinicaBuscarMelhorGuicheStrategy(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
    }

    @Override
    public Guiche buscarMelhorGuiche(TipoServico tipoServico) {
        List<Guiche> guichesAtivosMesmoTipo = guicheRepository.findByAtivoTrueAndTipoServico(tipoServico);
        if (guichesAtivosMesmoTipo.isEmpty()) {
            throw new GuicheException("Não há guichês ativos");
        }

        return guichesAtivosMesmoTipo.get(0);
    }
}
