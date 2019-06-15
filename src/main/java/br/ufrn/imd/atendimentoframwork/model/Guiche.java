package br.ufrn.imd.atendimentoframwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Guiche extends EntidadeAbstrata {
    private String nome;
    private boolean ativo;
    @ManyToOne
    private TipoServico tipoServico;
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

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public List<Senha> getSenhas() {
        return senhas;
    }

    @JsonIgnore
    public List<Senha> getSenhasAguardando() {
        return senhas.stream().filter(s -> s.getStatus() == SenhaStatus.AGUARDANDO).collect(Collectors.toList());
    }

    public void setSenhas(List<Senha> senhas) {
        this.senhas = senhas;
    }
}
