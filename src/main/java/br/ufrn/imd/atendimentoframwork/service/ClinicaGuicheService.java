package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.util.interfaces.BuscarMelhorGuicheStrategy;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoAtivarStrategy;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoDesativarStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClinicaGuicheService extends GuicheServiceImpl {

    @Autowired
    public ClinicaGuicheService(GuicheRepository guicheRepository,
                                @Qualifier("clinicaBuscarMelhorGuicheStrategy") BuscarMelhorGuicheStrategy buscarMelhorGuicheStrategy,
                                @Qualifier("clinicaRealocarAoAtivarStrategy") RealocarAoAtivarStrategy realocarAoAtivarStrategy,
                                @Qualifier("clinicaRealocarAoDesativarStrategy") RealocarAoDesativarStrategy realocarAoDesativarStrategy) {
        super(guicheRepository, buscarMelhorGuicheStrategy, realocarAoAtivarStrategy, realocarAoDesativarStrategy);
    }

    @Override
    public Guiche save(Guiche guiche) {
        if (!guicheRepository.findByTipoServico(guiche.getTipoServico()).isEmpty()) {
            throw new GuicheException("Já existe um guichê com esse tipo de serviço.");
        }
        return super.save(guiche);
    }
}
