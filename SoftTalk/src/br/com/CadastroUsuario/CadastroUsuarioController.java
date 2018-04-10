/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.CadastroUsuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author free
 */
public class CadastroUsuarioController implements Initializable {
    
    //id para voltar a tela de login
    @FXML
    private Button Voltar;

    //id para botao de cadastro
    @FXML
    private Button Cadastrar;
    
    //id para nome de usuario
    @FXML
    private TextField Nome;

    //id para setor
    @FXML
    private ComboBox<?> Setor;

    //id para email do usuario
    @FXML
    private TextField usuario;

    //id para senha do usuario
    @FXML
    private PasswordField senha;

    //id para confirmar senha do usuario
    @FXML
    private PasswordField senha1;

    //usuario escolhe setor 
    @FXML
    void boxSetor(ActionEvent event) {

    }
    //botao para cadastrar no banco de dados
    @FXML
    void btnCadastrar(ActionEvent event) {

    }
    //botao para voltar para tela de login
    @FXML
    void btnVoltar(ActionEvent event) {

    }
    //email do usuario
    @FXML
    void textNome(ActionEvent event) {

    }
    //pegar a senha do usuario
    @FXML
    void textSenha(ActionEvent event) {

    }
    //verificar a senha do usuario
    @FXML
    void textSenhaRepete(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
