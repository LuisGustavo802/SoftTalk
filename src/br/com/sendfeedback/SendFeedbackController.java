/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sendfeedback;

import br.com.Utils.DAOUtils;
import br.com.feedback.Feedback;
import br.com.sendfeedback.DAOSendFeedback;
import br.com.Utils.Functions;
import br.com.pessoa.DAOPessoa;
import br.com.pessoa.Pessoa;
import br.com.setor.DAOSetor;
import br.com.setor.Setor;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SendFeedbackController implements Initializable {

    //Objetos do FXML
    @FXML
    private ComboBox<Setor> cbxSetores;

    @FXML
    private ComboBox<Pessoa> cbxPessoas;

    @FXML
    private TextArea txaDescricao;

    //Objetos do controller
    private ObservableList<Setor> observableListSetor;
    private ObservableList<Pessoa> observableListPessoas;
    private Feedback feedback;
    private Functions functions;

    //Função de Ação to XMML
    @FXML
    void selecionarUsuarioAction(ActionEvent event) throws IOException, SQLException {
        selecionarUsuario();
    }

    @FXML
    void selecionarSetorAction(ActionEvent event) throws SQLException, IOException {
        selecionarSetor();
    }

    @FXML
    void enviarAction(ActionEvent event) throws SQLException, IOException {
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

    private void enviar() throws SQLException, IOException {
        if (validacoes() == functions.SUCCESS) {
            DAOUtils daoUtils = new DAOUtils();
                    
            feedback.setIdUsuarioRemetente(SoftTalk.getIdUsuarioLogado());
            feedback.setTipoFeedback("E");
            feedback.setStatus("P");
            feedback.setDtMovimento(daoUtils.carregaDataServidor());
            feedback.setDescricao(txaDescricao.getText());

            DAOSendFeedback gravaFeedback = new DAOSendFeedback();
            if (gravaFeedback.enviaFeedback(feedback) > 0){
                inicializaComponentes();
                functions.abrirMensagem("Gravado com sucesso.");
            }else{
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
        feedback.setIdempresa(listaUsuarios.get(0).getIdEmpresa());
    }

    private void selecionarSetor() throws SQLException, IOException {
        //Captura e envia para a classe de gravação o id de usuario da pessoa
        Setor setor = cbxSetores.getSelectionModel().getSelectedItem();
        carregaPessoas(setor.getIdsetor());
    }

    private void inicializaComponentes() throws SQLException, IOException {
        //incializa ou limpa os componetes da tela preparando para um atransação
        feedback = new Feedback();
        functions = new Functions();
        //Carrega todos os setores e pessoas para os combos box
        carregaSetores();
        carregaPessoas();
        txaDescricao.setText("");
    }

    private void carregaSetores() throws SQLException {
        List<Setor> listaSetor;
        DAOSetor daoSetor;
        daoSetor = new DAOSetor();
        listaSetor = daoSetor.listarSetor();
        observableListSetor = FXCollections.observableArrayList(listaSetor);
        cbxSetores.setItems(observableListSetor);
    }

    private void carregaPessoas() throws SQLException, IOException {
        carregaPessoas(0);
    }

    private void carregaPessoas(int idSetor) throws SQLException, IOException {
        List<Pessoa> listaPessoas;
        DAOPessoa daoPessoa;
        daoPessoa = new DAOPessoa();
        if (idSetor == 0) {
            listaPessoas = daoPessoa.listarPessoas();
        } else {
            listaPessoas = daoPessoa.listarPessoasCondicao(idSetor);
        }

        observableListPessoas = FXCollections.observableArrayList(listaPessoas);
        cbxPessoas.setItems(observableListPessoas);
    }

    private int validacoes() {
        /*if (cbxSetores.getSelectionModel().getSelectedItem() == null) {
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
    
}
