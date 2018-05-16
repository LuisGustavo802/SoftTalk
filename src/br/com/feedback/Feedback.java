package br.com.feedback;

import br.com.pessoa.DAOPessoa;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class Feedback {
    private int IdPessoa;
    private String tipoFeedBack;
    private String mensagem;
    
    private int idFeedback, idUsuRemetente;
    private String status;
    private Date dtMovimento;
    private String descLista;
    
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

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public int getIdUsuRemetente() {
        return idUsuRemetente;
    }

    public void setIdUsuRemetente(int idUsuRemetente) {
        this.idUsuRemetente = idUsuRemetente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDtMovimento() {
        return dtMovimento;
    }

    public void setDtMovimento(Date dtMovimento) {
        this.dtMovimento = dtMovimento;
    }

    @Override
    public String toString() {
        DAOPessoa daopes = new DAOPessoa();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            descLista= "De: " + daopes.listaNomePessoa(idUsuRemetente) + ", Data: " + formato.format(dtMovimento);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descLista;
    }
  
    
}
