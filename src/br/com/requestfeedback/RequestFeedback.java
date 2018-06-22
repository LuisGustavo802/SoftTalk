package br.com.requestfeedback;

import java.sql.Date;

/**
 *
 * @author luis_
 */
public class RequestFeedback {

    private int idfeedback;
    private String tipoSolicitacao;
    private Date dtLimite;

    public int getIdfeedback() {
        return idfeedback;
    }

    public void setIdfeedback(int idfeedback) {
        this.idfeedback = idfeedback;
    }

    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public Date getDtLimite() {
        return dtLimite;
    }

    public void setDtLimite(Date dtLimite) {
        this.dtLimite = dtLimite;
    }

}
