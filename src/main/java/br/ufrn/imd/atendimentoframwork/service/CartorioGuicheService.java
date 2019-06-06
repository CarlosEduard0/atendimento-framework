package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import br.ufrn.imd.atendimentoframwork.service.interfaces.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartorioGuicheService extends GuicheService {
    @Autowired
    public CartorioGuicheService(GuicheRepository guicheRepository) {
        super(guicheRepository);
    }

    @Override
    protected void realocarSenhasAoAtivar(Guiche guiche) {
        // TODO
    }

    @Override
    public Guiche getMelhorGuiche(TipoServico tipoServico) {
        List<Guiche> guichesAtivos = guicheRepository.findByAtivoTrue();
        Guiche guiche = guichesAtivos.get(0);
        for(Guiche g : guichesAtivos) {
            if(g.getSenhas().size() < guiche.getSenhas().size()) {
                guiche = g;
            }
        }
        return guiche;
    }
}
