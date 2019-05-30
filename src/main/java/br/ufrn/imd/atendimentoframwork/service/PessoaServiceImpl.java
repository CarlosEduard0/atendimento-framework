package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Pessoa;
import br.ufrn.imd.atendimentoframwork.repository.PessoaRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).get();
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void update(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    @Override
    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }
}
