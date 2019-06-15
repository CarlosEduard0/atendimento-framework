package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import br.ufrn.imd.atendimentoframwork.util.interfaces.BuscarMelhorGuicheStrategy;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoAtivarStrategy;
import br.ufrn.imd.atendimentoframwork.util.interfaces.RealocarAoDesativarStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuicheServiceImpl implements GuicheService {
    final GuicheRepository guicheRepository;
    private final BuscarMelhorGuicheStrategy buscarMelhorGuicheStrategy;
    private final RealocarAoAtivarStrategy realocarAoAtivarStrategy;
    private final RealocarAoDesativarStrategy realocarAoDesativarStrategy;

    public GuicheServiceImpl(GuicheRepository guicheRepository, @Qualifier("clinicaBuscarMelhorGuicheStrategy") BuscarMelhorGuicheStrategy buscarMelhorGuicheStrategy, @Qualifier("clinicaRealocarAoAtivarStrategy") RealocarAoAtivarStrategy realocarAoAtivarStrategy, @Qualifier("clinicaRealocarAoDesativarStrategy") RealocarAoDesativarStrategy realocarAoDesativarStrategy) {
        this.guicheRepository = guicheRepository;
        this.buscarMelhorGuicheStrategy = buscarMelhorGuicheStrategy;
        this.realocarAoAtivarStrategy = realocarAoAtivarStrategy;
        this.realocarAoDesativarStrategy = realocarAoDesativarStrategy;
    }

    public List<Guiche> findAll() {
        return guicheRepository.findAll();
    }

    public Guiche findById(Long id) {
        verificarSeGuicheExiste(id);
        return guicheRepository.findById(id).get();
    }

    public Guiche save(Guiche guiche) {
        return guicheRepository.save(guiche);
    }

    public void update(Guiche guiche) {
        guicheRepository.save(guiche);
    }

    public void delete(Guiche guiche) {
        guicheRepository.delete(guiche);
    }

    public void deleteById(Long id) {
        guicheRepository.deleteById(id);
    }

    public void ativar(Long id) {
        Guiche guiche = findById(id);
        verificarSeGuicheEstaAtivo(guiche);
        realocarAoAtivarStrategy.realocarSenhasAoAtivar(guiche);
        guiche.setAtivo(true);
        update(guiche);
    }

    public void desativar(Long id) {
        Guiche guiche = findById(id);
        verificarSeGuicheEstaDesativado(guiche);
        guiche.setAtivo(false);
        update(guiche);
        realocarAoDesativarStrategy.realocarSenhasAoDesativar(guiche);
    }

    @Override
    public Guiche buscarMelhorGuiche(TipoServico tipoServico) {
        return buscarMelhorGuicheStrategy.buscarMelhorGuiche(tipoServico);
    }

    private void verificarSeGuicheEstaAtivo(Guiche guiche) {
        if (guiche.isAtivo()) {
            throw new GuicheException("Esse guichê já está ativo.");
        }
    }

    private void verificarSeGuicheEstaDesativado(Guiche guiche) {
        if (!guiche.isAtivo()) {
            throw new GuicheException("Esse guichê já está desativado.");
        }
    }

    private void verificarSeGuicheExiste(Long id) {
        if (!guicheRepository.findById(id).isPresent()) {
            throw new GuicheException("Esse guichê não existe.");
        }
    }
}
