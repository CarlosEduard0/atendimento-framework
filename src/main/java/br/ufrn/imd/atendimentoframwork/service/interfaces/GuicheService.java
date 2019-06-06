package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;

import java.util.List;

public abstract class GuicheService {
    protected final GuicheRepository guicheRepository;

    protected GuicheService(GuicheRepository guicheRepository) {
        this.guicheRepository = guicheRepository;
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

    public final void ativar(Long id) {
        Guiche guiche = findById(id);
        verificarSeGuicheEstaAtivo(guiche);
        realocarSenhasAoAtivar(guiche);
        guiche.setAtivo(true);
        update(guiche);
    }

    protected abstract void realocarSenhasAoAtivar(Guiche guiche);

    public abstract Guiche getMelhorGuiche(TipoServico tipoServico);

    private void verificarSeGuicheEstaAtivo(Guiche guiche) {
        if(guiche.isAtivo()) {
            throw new GuicheException("Esse guichê já está ativo.");
        }
    }

    private void verificarSeGuicheExiste(Long id) {
        if(!guicheRepository.findById(id).isPresent()) {
            throw new GuicheException("Esse guichê não existe.");
        }
    }
}
