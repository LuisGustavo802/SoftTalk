/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import br.com.conexao.Conexao;
import javafx.scene.input.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author luis_
 */
public class LoginController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField senha;

    private Connection conexao;

    @FXML
    protected void LoginAction(KeyEvent event) {

        try {
            conexao = new Conexao().getConnection();
            validaLogin();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void validaLogin() throws SQLException {
        String where;
        Statement st;
        ResultSet rs;

        try {
            where = "idusuario = " + Integer.parseInt(usuario.getText());

        } catch (NumberFormatException ex) {
            where = "upper(login) =  upper('" + usuario.getText() + "')";
        }

        st = conexao.createStatement();
        rs = st.executeQuery("Select idusuario, login, senha, flagativo from usuario where " + where);

        if (rs.next()) {
            if (rs.getString("flagativo").equals("T")) {
                if (rs.getString("senha").equals(senha.getText())) {
                    System.out.println("tela principal");
                } else {
                    // usuario ou senha incorretos
                }
            } else {
                //mensagem usuario inativo
            }
        }

    }

}
