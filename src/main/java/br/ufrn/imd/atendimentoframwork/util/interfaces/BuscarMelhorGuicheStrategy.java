package br.ufrn.imd.atendimentoframwork.util.interfaces;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.model.TipoServico;

public interface BuscarMelhorGuicheStrategy {
    Guiche buscarMelhorGuiche(TipoServico tipoServico);
}
