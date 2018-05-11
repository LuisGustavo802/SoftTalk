package br.com.feedback;

/**
 *
 * @author luis_
 */
public class Feedback {
    private int IdPessoa;
    private String tipoFeedBack;
    private String mensagem;

    public int getIdPessoa() {
        return IdPessoa;
    }

    public void setIdPessoa(int IdPessoa) {
        this.IdPessoa = IdPessoa;
    }

    public String getTipoFeedBack() {
        return tipoFeedBack;
    }

    public void setTipoFeedBack(String tipoFeedBack) {
        this.tipoFeedBack = tipoFeedBack;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
