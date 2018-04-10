/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Usuario;

import br.com.dao.DAOUsuario;
import br.com.softtalk.SoftTalk;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UsuarioController {

    @FXML
    private TextField txtEmail, txtSenha, txtCopiaSenha;

    @FXML
    private void voltarAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Telas/Tela1.fxml"));
        Scene scene = new Scene(root);

        SoftTalk.stage.setScene(scene);
        SoftTalk.stage.show();
    }

    @FXML
    public void cadastrarUsuario(ActionEvent event) {
        try {
            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario usuario = new Usuario();
            
            usuario.setLogin(String.valueOf(txtEmail.getText()));

            if (String.valueOf(txtSenha.getText()).equals(String.valueOf(txtCopiaSenha.getText()))) {
                usuario.setSenha(String.valueOf(txtSenha.getText()));
            }
            usuario.setFlagativo("T");
            daoUsuario.inserirUsuario(usuario);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
