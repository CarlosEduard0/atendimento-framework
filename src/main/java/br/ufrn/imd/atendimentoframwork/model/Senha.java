package br.ufrn.imd.atendimentoframwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Senha extends EntidadeAbstrata {
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JsonIgnore
    @ManyToOne(optional = false)
    private Guiche guiche;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Guiche getGuiche() {
        return guiche;
    }

    public void setGuiche(Guiche guiche) {
        this.guiche = guiche;
    }
}
