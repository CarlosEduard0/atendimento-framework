package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;

import java.util.List;

public interface GuicheService {
    List<Guiche> findAll();

    Guiche findById(Long id);

    Guiche save(Guiche guiche);

    void update(Guiche guiche);

    void delete(Guiche guiche);

    void deleteById(Long id);

    void ativar(Long id);

    void desativar(Long id);

    Guiche buscarMelhorGuiche(TipoServico tipoServico);
}
