package br.ufrn.imd.atendimentoframwork.erro;

public class ErroDetalhes {
    private String mensagem;

    public ErroDetalhes(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
