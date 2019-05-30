package br.ufrn.imd.atendimentoframwork.model;

import javax.persistence.Entity;

@Entity
public class Pessoa extends EntidadeAbstrata {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
