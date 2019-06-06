package br.ufrn.imd.atendimentoframwork.service.interfaces;

import br.ufrn.imd.atendimentoframwork.model.Senha;

import java.util.List;

public interface SenhaService {
    Senha save(Senha senha);

    void updateAll(List<Senha> senhas);
}
