package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuicheServiceImpl implements GuicheService {
    private final GuicheRepository guicheRepository;

    @Autowired
    public GuicheServiceImpl(GuicheRepository guicheRepository) {
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
        Guiche guiche = guicheRepository.findById(id).get();
        guiche.setAtivo(true);
        update(guiche);
    }

    @Override
    public Guiche getMelhorGuiche() {
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
