package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.GuicheRepository;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaGuicheService extends GuicheService {

    @Autowired
    public ClinicaGuicheService(GuicheRepository guicheRepository , SenhaRepository senhaRepository) {
        super(guicheRepository, senhaRepository);
    }

    @Override
    public Guiche save(Guiche guiche) {
        if(guicheRepository.findByTipoServico(guiche.getTipoServico()).size() > 0) {
            throw new GuicheException("Já existe um guichê com esse tipo de serviço.");
        }
        return super.save(guiche);
    }

    @Override
    protected void realocarSenhasAoAtivar(Guiche guiche) {}

    @Override
    public Guiche getMelhorGuiche(TipoServico tipoServico) {
        return guicheRepository.findByAtivoTrueAndTipoServico(tipoServico).get(0);
    }
}
