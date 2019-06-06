package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadaoGuicheService extends GuicheService {

    @Autowired
    public CidadaoGuicheService(GuicheRepository guicheRepository, SenhaRepository senhaRepository) {
        super(guicheRepository, senhaRepository);
    }

    @Override
    protected void realocarSenhasAoAtivar(Guiche guiche) {
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
