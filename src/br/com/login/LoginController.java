/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import br.com.usuario.Usuario;
import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import br.com.telainicial.TelaInicialController;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 *
 * @author luis_
 */
public class LoginController {

    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField senha;
    @FXML
    private Button btnEntrar;

    @FXML
    protected void LoginAction(ActionEvent event) {
        try {
            validaLogin();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*@FXML
    protected void loginKey(KeyEvent e) {
        String teste;
        
        teste = "===";
        /*if (e.get) {
            if (usuario.isFocused()) {
                senha.focusedProperty();
            }
            if (senha.isFocused()) {
                try {
                    validaLogin();
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/

    @FXML
    void CadastroAction(ActionEvent event) {
        abrirCadastro();

    }

    private void abrirCadastro() {
        try {
            Parent fxmlLoader = FXMLLoader.load(Usuario.class.getResource("Usuario.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validaLogin() throws SQLException, IOException {
        String where;
        Statement st;
        ResultSet rs;
        Functions functions = new Functions();

        try {
            where = "idusuario = " + Integer.parseInt(usuario.getText());

        } catch (NumberFormatException ex) {
            where = "upper(login) =  upper('" + usuario.getText() + "')";
        }

        st = SoftTalk.conexao.createStatement();
        rs = st.executeQuery("Select idusuario, login, senha, flagativo from usuario where " + where);

        if (rs.next()) {
            if (rs.getString("flagativo").equals("T")) {
                if (rs.getString("senha").equals(functions.encript(senha.getText()))) {
                    SoftTalk.setIdUsuarioLogado(rs.getInt("idusuario"));//Alimenta com o usuario logado no sistema
                    Parent fxmlLoader = FXMLLoader.load(TelaInicialController.class.getResource("TelaInicial.fxml"));

                    SoftTalk.stage.setScene(new Scene(fxmlLoader));

                } else {
                    // usuario ou senha incorretos
                }
            } else {
                //mensagem usuario inativo
            }
        }
    }
}
