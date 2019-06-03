package br.ufrn.imd.atendimentoframwork.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Guiche extends EntidadeAbstrata {
    private String nome;
    private boolean ativo;
    @OneToMany(mappedBy = "guiche")
    private List<Senha> senhas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Senha> getSenhas() {
        return senhas;
    }

    public void setSenhas(List<Senha> senhas) {
        this.senhas = senhas;
    }
}
