/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sendfeedback;

import br.com.Utils.DAOUtils;
import br.com.Utils.EnviarEmail;
import br.com.feedback.Feedback;
import br.com.Utils.Functions;
import br.com.pessoa.DAOPessoa;
import br.com.pessoa.Pessoa;
import br.com.equipe.DAOEquipe;
import br.com.equipe.Equipe;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
import java.awt.Color;
import static java.awt.Color.black;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import org.apache.commons.mail.EmailException;

public class SendFeedbackController implements Initializable {

    //Objetos do FXML
    @FXML
    private ComboBox<Equipe> cbxEquipes;
    
    @FXML
    private Button btnQueBom;
    
    @FXML
    private Button btnQueTal;
    
    @FXML
    private Button btnQuePena;

    @FXML
    private ComboBox<Pessoa> cbxPessoas;

    @FXML
    private TextArea txaDescricao;
    
    DropShadow shadow = new DropShadow();

    //Objetos do controller
    private ObservableList<Equipe> observableListEquipe;
    private ObservableList<Pessoa> observableListPessoas;
    private Feedback feedback;
    private Functions functions;

    //Função de Ação to XMML
    @FXML
    void selecionarUsuarioAction(ActionEvent event) throws IOException, SQLException {
        selecionarUsuario();
    }

    @FXML
    void selecionarEquipeAction(ActionEvent event) throws SQLException, IOException {
        selecionarEquipe();
    }

    @FXML
    void enviarAction(ActionEvent event) throws SQLException, IOException, EmailException {
        enviar();
    }

    //Funções do controller
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializaComponentes();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SendFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviar() throws SQLException, IOException, EmailException {
        if (validacoes() == functions.SUCCESS) {
            DAOUtils daoUtils = new DAOUtils();
            EnviarEmail email = new EnviarEmail();
            
            feedback.setIdUsuarioRemetente(SoftTalk.getIdUsuarioLogado());
            feedback.setTipoFeedback("E");
            feedback.setStatus("P");
            feedback.setDtMovimento(daoUtils.carregaDataServidor());
            feedback.setDescricao(txaDescricao.getText());
            

            email.enviandoEmail(feedback.getEmailDestinatario() ,2);
            DAOSendFeedback gravaFeedback = new DAOSendFeedback();
            if (gravaFeedback.enviaFeedback(feedback) > 0) {
                
                email.enviandoEmail(feedback.getEmailDestinatario() ,2);
                inicializaComponentes();
                functions.abrirMensagem("Gravado com sucesso.");
            } else {
                functions.abrirMensagem("Falha ao Gravar.");
            }

        }
    }

    private void selecionarUsuario() throws SQLException {
        Pessoa pessoa = cbxPessoas.getSelectionModel().getSelectedItem();
        DAOUsuario daoUsuario = new DAOUsuario();
        List<Usuario> listaUsuarios = daoUsuario.listarUsuariosCondicao(pessoa.getIdpessoa());
        //Carrega as infomações do usuario e alimente o idUsuario e a empresa para a classe de feedback
        feedback.setIdUsuarioDestino(listaUsuarios.get(0).getIdusuario());
        feedback.setEmailDestinatario(listaUsuarios.get(0).getEmail());
        feedback.setIdempresa(listaUsuarios.get(0).getIdEmpresa());
    }

    private void selecionarEquipe() throws SQLException, IOException {
        //Captura e envia para a classe de gravação o id de usuario da pessoa
        Equipe equipe = cbxEquipes.getSelectionModel().getSelectedItem();
        carregaPessoas(equipe.getIdequipe());
    }

    private void inicializaComponentes() throws SQLException, IOException {
        //incializa ou limpa os componetes da tela preparando para um atransação
        feedback = new Feedback();
        functions = new Functions();
        //Carrega todos as equipes e pessoas para os combos box
        carregaEquipes();
        carregaPessoas();
        txaDescricao.setText("");
        feedback.setStatusSend("");
        btnQueBom.setEffect(null);
        btnQuePena.setEffect(null);
        btnQueTal.setEffect(null);
        
    }

    private void carregaEquipes() throws SQLException {
        List<Equipe> listaEquipe;
        DAOEquipe daoEquipe;
        daoEquipe = new DAOEquipe();
        listaEquipe = daoEquipe.listarEquipe();
        observableListEquipe = FXCollections.observableArrayList(listaEquipe);
        cbxEquipes.setItems(observableListEquipe);
    }

    private void carregaPessoas() throws SQLException, IOException {
        carregaPessoas(0);
    }

    private void carregaPessoas(int idEquipe) throws SQLException, IOException {
        List<Pessoa> listaPessoas;
        DAOPessoa daoPessoa;
        daoPessoa = new DAOPessoa();
        if (idEquipe == 0) {
            listaPessoas = daoPessoa.listarPessoas();
        } else {
            listaPessoas = daoPessoa.listarPessoasCondicao(idEquipe);
        }

        observableListPessoas = FXCollections.observableArrayList(listaPessoas);
        cbxPessoas.setItems(observableListPessoas);
    }

    private int validacoes() {
        /*if (cbxEquipes.getSelectionModel().getSelectedItem() == null) {
            functions.abrirMensagem("Seção não informada.");
            return functions.FAILURE;
        }*/

        if (cbxPessoas.getSelectionModel().getSelectedItem() == null) {
            functions.abrirMensagem("Usuario não selecionado.");
            return functions.FAILURE;
        }

        if (txaDescricao.getText().equals("")) {
            functions.abrirMensagem("Feedback não informado.");
            return functions.FAILURE;
        }

        return functions.SUCCESS;
    }

    @FXML
    void statusSendQueBomAction(ActionEvent event) {
        feedback.setStatusSend("Que Bom");
        btnQueBom.setEffect(shadow);
        btnQuePena.setEffect(null);
        btnQueTal.setEffect(null);
    }
    
    @FXML
    void statusSendQuePenaAction(ActionEvent event) {
        feedback.setStatusSend("Que Pena");
        btnQuePena.setEffect(shadow);
        btnQueBom.setEffect(null);
        btnQueTal.setEffect(null);
    }

    @FXML
    void statusSendQueTalAction(ActionEvent event) {
        feedback.setStatusSend("Que Tal");
        btnQueTal.setEffect(shadow);
        btnQuePena.setEffect(null);
        btnQueBom.setEffect(null);
    }

}
