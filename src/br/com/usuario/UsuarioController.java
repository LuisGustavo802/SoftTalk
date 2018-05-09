/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usuario;

import br.com.pessoa.Pessoa;
import br.com.pessoa.DAOPessoa;
import br.com.Utils.Functions;
import br.com.login.Login;
import br.com.setor.DAOSetor;
import br.com.setor.Setor;
import br.com.softtalk.SoftTalk;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author free
 */
public class UsuarioController implements Initializable {

    @FXML
    private PasswordField senha;

    @FXML
    private ComboBox<Setor> bxSetor;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha1;

    @FXML
    private TextField txNome;
    
    private List<Setor> listSetor = new ArrayList<>();
    private ObservableList<Setor> observableListSetor;
    
    @FXML
    void cadastrarAction(ActionEvent event){
        cadastrarUsuario();
    }
    
    @FXML
    void voltarAction(ActionEvent event){
        voltarTelaLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBox();

    }
       
    public void carregarComboBox(){
        DAOSetor daoSetor;
        try {
            daoSetor = new DAOSetor();
            listSetor = daoSetor.listarSetor();
            observableListSetor = FXCollections.observableArrayList(listSetor);
            bxSetor.setItems(observableListSetor);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int cadastrarUsuario(){
        Functions functions = new Functions();
        Pessoa pessoa = new Pessoa();
        Usuario user = new Usuario();
        Statement st;
        ResultSet rs;
        Setor setor = bxSetor.getSelectionModel().getSelectedItem(); 
      
        if (txNome.getText().isEmpty() || setor == null || usuario.getText().isEmpty() || senha.getText().isEmpty() ||senha1.getText().isEmpty() ){
            functions.mensagemPadrao("Favor preencher todos os campos!");
            return functions.FAILURE;
        }
        pessoa.setNome(String.valueOf(txNome.getText()));
        pessoa.setIdsetor(setor.getIdsetor());
        DAOPessoa daopessoa;
        daopessoa = new DAOPessoa();
        int codPessoa = daopessoa.inserirPessoa(pessoa);
        if (codPessoa < 0){
            functions.mensagemPadrao("Problemas na gravação!");
            return functions.FAILURE;

        }
        user.setIdpessoa(codPessoa);
        user.setFlagativo("T");
        user.setLogin(String.valueOf(usuario.getText()));
        if(String.valueOf(senha.getText()).equals(String.valueOf(senha1.getText()))){
            user.setSenha(functions.encript(senha.getText()));
        }else{
            functions.mensagemPadrao("Senhas diferentes. Favor corrigir!");
            return functions.FAILURE;
        }
        DAOUsuario daousuario = new DAOUsuario();
        if (daousuario.inserirUsuario(user) < 0){
            functions.mensagemPadrao("Problemas na gravação!");
            return functions.FAILURE;
        }
        user.setIdpessoa(codPessoa);
        user.setFlagativo("T");
        user.setLogin(String.valueOf(usuario.getText()));

        if(String.valueOf(senha.getText()).equals(String.valueOf(senha1.getText()))){
            user.setSenha(functions.encript(senha.getText()));
        }else{
          functions.mensagemPadrao("Senhas diferentes. Favor corrigir!");      
          return functions.FAILURE;
        }
        DAOUsuario daousuario = new DAOUsuario();
        if (daousuario.inserirUsuario(user) < 0){
            functions.mensagemPadrao("Problemas na gravação!");
            return functions.FAILURE;
        }

        functions.mensagemPadrao("Gravado com sucesso!");
        voltarTelaLogin();
        return functions.SUCCESS;
    }
    
    public void voltarTelaLogin(){
        try {
            Parent fxmlLoader = FXMLLoader.load(Login.class.getResource("Login.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}


