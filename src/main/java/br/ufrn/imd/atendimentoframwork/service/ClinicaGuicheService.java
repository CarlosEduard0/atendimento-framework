package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.exception.GuicheJaExisteException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaGuicheService implements GuicheService {
    private final GuicheRepository guicheRepository;

    public ClinicaGuicheService(GuicheRepository guicheRepository) {
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
        if(guicheRepository.findByTipoServico(guiche.getTipoServico()).size() > 0) {
            throw new GuicheJaExisteException("Já existe um guichê com esse tipo de serviço.");
        }
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
        return guicheRepository.findByAtivoTrueAndTipoServico(tipoServico).get(0);
    }
}
