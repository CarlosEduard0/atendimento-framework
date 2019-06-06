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
public class CartorioGuicheService extends GuicheService {
    private final SenhaRepository senhaRepository;

    @Autowired
    public CartorioGuicheService(GuicheRepository guicheRepository, SenhaRepository senhaRepository) {
        super(guicheRepository);
        this.senhaRepository = senhaRepository;
    }

    @Override
    protected void realocarSenhasAoAtivar(Guiche guiche) {
        List<Guiche> guichesAtivos = guicheRepository.findByAtivoTrue();
        if(!guichesAtivos.isEmpty()) {
            List<Senha> senhas = new ArrayList<>();
            for(Guiche g : guichesAtivos) {
                senhas.addAll(g.getSenhas().subList(g.getSenhas().size() - (int) (g.getSenhas().size() * 0.2), g.getSenhas().size()));
            }
            for(Senha s : senhas) {
                s.setGuiche(guiche);
            }
            senhaRepository.saveAll(senhas);
        }
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
