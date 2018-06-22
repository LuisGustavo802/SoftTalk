/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feedback;

import br.com.pessoa.DAOPessoa;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Feedback {

    private int idFeedBack;
    private int idUsuarioRemetente;
    private int idempresa;
    private int idUsuarioDestino;
    private String tipoFeedback;
    private Date dtMovimento;
    private String status;
    private String descricao;
    private String statusSend;
    private String emailDestinatario;

    public String getStatusSend() {
        return statusSend;
    }

    public void setStatusSend(String statusSend) {
        this.statusSend = statusSend;
    }
  
     public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;  
  
    public int getIdFeedBack() {
        return idFeedBack;
    }

    public void setIdFeedBack(int idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    public int getIdUsuarioRemetente() {
        return idUsuarioRemetente;
    }

    public void setIdUsuarioRemetente(int idUsuarioRemetente) {
        this.idUsuarioRemetente = idUsuarioRemetente;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public void setIdUsuarioDestino(int idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    public String getTipoFeedback() {
        return tipoFeedback;
    }

    public void setTipoFeedback(String tipoFeedback) {
        this.tipoFeedback = tipoFeedback;
    }

    public Date getDtMovimento() {
        return dtMovimento;
    }

    public void setDtMovimento(Date dtMovimento) {
        this.dtMovimento = dtMovimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        DAOPessoa daoPessoa = new DAOPessoa();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Quando lista traz já algumas informações para exibir na recepção de feedback
        try {
            if (this.tipoFeedback.equals("S")) {

                return "Feedback requisitado de: " + daoPessoa.listaNomePessoa(idUsuarioRemetente)
                        + ". Envio:" + formato.format(dtMovimento) + "";

            } else {
                return "Feedback recebido de: " + daoPessoa.listaNomePessoa(idUsuarioRemetente)
                        + " [Envio:" + formato.format(dtMovimento) + "]";
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
