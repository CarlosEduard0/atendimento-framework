package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.repository.TipoServicoRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.TipoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicoServiceImpl implements TipoServicoService {
    private final TipoServicoRepository tipoServicoRepository;

    @Autowired
    public TipoServicoServiceImpl(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    @Override
    public List<TipoServico> findAll() {
        return tipoServicoRepository.findAll();
    }

    @Override
    public TipoServico findById(Long id) {
        return tipoServicoRepository.findById(id).get();
    }

    @Override
    public TipoServico save(TipoServico tipoServico) {
        return tipoServicoRepository.save(tipoServico);
    }

    @Override
    public void update(TipoServico tipoServico) {
        tipoServicoRepository.save(tipoServico);
    }

    @Override
    public void delete(TipoServico tipoServico) {
        tipoServicoRepository.delete(tipoServico);
    }

    @Override
    public void deleteById(Long id) {
        tipoServicoRepository.deleteById(id);
    }
}
