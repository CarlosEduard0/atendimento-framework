package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.model.Atendimento;

import java.util.List;

public interface AtendimentoService {
    List<Atendimento> findAll();
    
    Atendimento findById(Long id);
    
    Atendimento save(Atendimento atendimento);
    
    void update(Atendimento atendimento);
    
    void delete(Atendimento atendimento);
    
    void deleteById(Long id);
}
