package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadaoGuicheService extends GuicheService {

    @Autowired
    public CidadaoGuicheService(GuicheRepository guicheRepository) {
        super(guicheRepository);
    }

    @Override
    protected void realocarSenhasAoAtivar(Guiche guiche) {
        // TODO
    }

    @Override
    public Guiche getMelhorGuiche(TipoServico tipoServico) {
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
