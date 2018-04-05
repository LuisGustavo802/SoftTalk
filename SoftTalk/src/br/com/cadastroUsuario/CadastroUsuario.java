/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroUsuario;

import br.com.dao.DAOUsuario;
import br.com.entidades.Usuario;
import br.com.login.Login;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kaliane Viesseli
 */
public class CadastroUsuario {
    
    @FXML
    private TextField txtEmail, txtSenha, txtCopiaSenha;
    
    public void cadastroUsuario(){
      try{
            Stage cadastroUsuario = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("CadastroUsuario.fxml"));
            AnchorPane page;

            page = (AnchorPane) fxmlLoader.load();

            cadastroUsuario.toFront();
            cadastroUsuario.setScene(new Scene(page));          
            cadastroUsuario.setTitle("SoftTalk");
            cadastroUsuario.setFullScreen(false);
            cadastroUsuario.setResizable(false);
            cadastroUsuario.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cadastrar (ActionEvent event) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException{
        DAOUsuario daoUsuario = new DAOUsuario();
        Usuario usuario = new Usuario();
        usuario.setLogin(String.valueOf(txtEmail.getText()));
        
        if (String.valueOf(txtSenha.getText()).equals(String.valueOf(txtCopiaSenha.getText()))) {
            usuario.setSenha(String.valueOf(txtSenha.getText()));
        }
        usuario.setFlagativo("T");
        daoUsuario.inserirUsuario(usuario);
    }
}
