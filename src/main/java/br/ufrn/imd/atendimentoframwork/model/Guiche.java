package br.ufrn.imd.atendimentoframwork.model;

import javax.persistence.Entity;

@Entity
public class Guiche extends EntidadeAbstrata {
    private String nome;
    //TODO adicionar lista de pessoas

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
