package br.com.requestfeedback;

import br.com.Utils.DAOUtils;
import br.com.Utils.Functions;
import br.com.feedback.DAOFeedback;
import br.com.feedback.Feedback;
import br.com.pessoa.DAOPessoa;
import br.com.pessoa.Pessoa;
import br.com.equipe.DAOEquipe;
import br.com.equipe.Equipe;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RequestFeedbackController implements Initializable {

    @FXML
    private JFXTextArea txMensagem;

    @FXML
    private JFXComboBox<Pessoa> bxPessoa;

    @FXML
    private JFXComboBox bxTipo;

    @FXML
    private JFXComboBox<Equipe> bxEquipe;

    /*@FXML
    private JFXDatePicker dtLimite;*/
    @FXML
    protected void SolicitarFeedbackAction(ActionEvent event) {
        solicitarFeedback();
    }

    @FXML
    protected void CarregaPessoasAction(ActionEvent event) {
        try {
            DAOPessoa pessoa = new DAOPessoa();
            List<Pessoa> listaPessoas;

            Equipe equipe = bxEquipe.getSelectionModel().getSelectedItem();

            listaPessoas = pessoa.listarPessoasCondicao(equipe.getIdequipe());

            ObservableList<Pessoa> observableListPessoas = FXCollections.observableArrayList(listaPessoas);
            bxPessoa.setItems(observableListPessoas);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEquipes();
    }

    private void solicitarFeedback() {
        try {
            if (validacao() != Functions.SUCCESS) {
                return;
            }
            DAOUsuario daoUsuario = new DAOUsuario();
            Feedback feedback = new Feedback();
            RequestFeedback requestFeedback = new RequestFeedback();
            DAORequestFeedback daoRequestFeedback = new DAORequestFeedback();
            DAOFeedback daoFeedback = new DAOFeedback();
            DAOUtils daoUtils = new DAOUtils();
            int id;

            Pessoa pessoa = bxPessoa.getSelectionModel().getSelectedItem();

            List<Usuario> listaUsuarios = daoUsuario.listarUsuariosCondicao(pessoa.getIdpessoa());

            feedback.setIdUsuarioRemetente(SoftTalk.getIdUsuarioLogado());
            feedback.setIdempresa(listaUsuarios.get(0).getIdEmpresa());
            feedback.setIdUsuarioDestino(listaUsuarios.get(0).getIdusuario());
            feedback.setTipoFeedback("S");
            feedback.setDtMovimento(daoUtils.carregaDataServidor());
            feedback.setStatus("I");
            feedback.setDescricao(txMensagem.getText());

            id = daoFeedback.gravarFeedBack(feedback);

            if (id < 0) {
                Functions.abrirMensagem("Problema ao salvar os dados.");
                return;
            }

            requestFeedback.setIdfeedback(id);
            requestFeedback.setTipoSolicitacao(bxTipo.getSelectionModel().getSelectedItem().toString());
            requestFeedback.setDtLimite(daoUtils.carregaDataServidor());

            daoRequestFeedback.inserirSolicitacaoFeedback(requestFeedback);
                        
            //Functions.abrirMensagem("Solicitação enviada com sucesso.");
            limparDados();

        } catch (SQLException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void carregaEquipes() {
        try {
            List<Equipe> listaEquipe;
            DAOEquipe daoEquipe;
            daoEquipe = new DAOEquipe();

            listaEquipe = daoEquipe.listarEquipe();

            ObservableList<Equipe> observableListEquipe = FXCollections.observableArrayList(listaEquipe);
            bxEquipe.setItems(observableListEquipe);
        } catch (SQLException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int validacao() {
        if (bxEquipe.getSelectionModel().getSelectedItem() == null) {
            Functions.abrirMensagem("Equipe não informada.");
            return Functions.FAILURE;
        }

        if (bxPessoa.getSelectionModel().getSelectedItem() == null) {
            Functions.abrirMensagem("Usuario de destino não selecionado.");
            return Functions.FAILURE;
        }

        if (bxTipo.getSelectionModel().getSelectedItem() == null) {
            Functions.abrirMensagem("Tipo de aplicação não informado.");
            return Functions.FAILURE;
        }

        return Functions.SUCCESS;
    }
    
    public void limparDados(){
       //ss txMensagem.setText("");
        
    }

}
