package br.ufrn.imd.atendimentoframwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class TipoServico extends EntidadeAbstrata {
    @NotEmpty
    @Column(unique = true)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
