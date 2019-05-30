package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.model.Pessoa;

import java.util.List;

public interface PessoaService {
    List<Pessoa> findAll();

    Pessoa findById(Long id);

    Pessoa save(Pessoa pessoa);

    void update(Pessoa pessoa);

    void delete(Pessoa pessoa);

    void deleteById(Long id);
}
