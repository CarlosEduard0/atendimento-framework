package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Atendimento;
import br.ufrn.imd.atendimentoframwork.repository.AtendimentoRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    @Autowired
    public AtendimentoServiceImpl(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    @Override
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    @Override
    public Atendimento findById(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    @Override
    public Atendimento save(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    @Override
    public void update(Atendimento atendimento) {
        atendimentoRepository.save(atendimento);
    }

    @Override
    public void delete(Atendimento atendimento) {
        atendimentoRepository.delete(atendimento);
    }

    @Override
    public void deleteById(Long id) {
        atendimentoRepository.deleteById(id);
    }
}
