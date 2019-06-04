package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.model.TipoServico;

import java.util.List;

public interface TipoServicoService {
    List<TipoServico> findAll();

    TipoServico findById(Long id);

    TipoServico save(TipoServico tipoServico);

    void update(TipoServico tipoServico);

    void delete(TipoServico tipoServico);

    void deleteById(Long id);
}
