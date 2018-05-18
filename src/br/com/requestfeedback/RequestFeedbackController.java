package br.com.requestfeedback;

import br.com.Utils.DAOUtils;
import br.com.Utils.Functions;
import br.com.feedback.DAOFeedback;
import br.com.feedback.Feedback;
import br.com.pessoa.DAOPessoa;
import br.com.pessoa.Pessoa;
import br.com.setor.DAOSetor;
import br.com.setor.Setor;
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
    private JFXComboBox<Setor> bxSetor;
    
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

            Setor setor = bxSetor.getSelectionModel().getSelectedItem();

            listaPessoas = pessoa.listarPessoasCondicao(setor.getIdsetor());

            ObservableList<Pessoa> observableListPessoas = FXCollections.observableArrayList(listaPessoas);
            bxPessoa.setItems(observableListPessoas);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaSetores();
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
            
            if (id < 0){
              return;  
            }
            
            requestFeedback.setIdfeedback(id);
            requestFeedback.setTipoSolicitacao(bxTipo.getSelectionModel().getSelectedItem().toString());
            requestFeedback.setDtLimite(daoUtils.carregaDataServidor());
            
            daoRequestFeedback.inserirSolicitacaoFeedback(requestFeedback);
            
        } catch (SQLException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void carregaSetores() {
        try {
            List<Setor> listaSetor;
            DAOSetor daoSetor;
            daoSetor = new DAOSetor();

            listaSetor = daoSetor.listarSetor();

            ObservableList<Setor> observableListSetor = FXCollections.observableArrayList(listaSetor);
            bxSetor.setItems(observableListSetor);
        } catch (SQLException ex) {
            Logger.getLogger(RequestFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int validacao() {
        if (bxSetor.getSelectionModel().getSelectedItem() == null) {
            Functions.abrirMensagem("Setor não informado.");
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

}
