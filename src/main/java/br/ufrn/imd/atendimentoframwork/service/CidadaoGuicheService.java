package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadaoGuicheService implements GuicheService {
    private final GuicheRepository guicheRepository;

    public CidadaoGuicheService(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
    }

    @Override
    public List<Guiche> findAll() {
        return guicheRepository.findAll();
    }

    @Override
    public Guiche findById(Long id) {
        return guicheRepository.findById(id).get();
    }

    @Override
    public Guiche save(Guiche guiche) {
        return guicheRepository.save(guiche);
    }

    @Override
    public void update(Guiche guiche) {
        guicheRepository.save(guiche);
    }

    @Override
    public void delete(Guiche guiche) {
        guicheRepository.delete(guiche);
    }

    @Override
    public void deleteById(Long id) {
        guicheRepository.deleteById(id);
    }

    @Override
    public void ativar(Long id) {
        Guiche guiche = findById(id);
        guiche.setAtivo(true);
        update(guiche);
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
