/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author luis_
 */
public class LoginController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField senha;
    @FXML
    private Button btnEntrar;

    @FXML
    protected void LoginAction(ActionEvent event) throws SQLException, IOException {
        validaLogin();
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
                    Parent fxmlLoader =  FXMLLoader.load(SoftTalk.class.getResource("Menu.fxml"));

                    //SoftTalk.stage.toFront();
                    SoftTalk.stage.setScene(new Scene(fxmlLoader));
                    SoftTalk.stage.showAndWait();
                } else {
                    // usuario ou senha incorretos
                }
            } else {
                //mensagem usuario inativo
            }
        }

    }
    
    //Botao para acessar tela de cadastro
       @FXML
    void btnAcessarCadastro(ActionEvent event) {

    }
    
    //NAO MEXER AINDA
    @FXML
    void btnEsqueceuSenha(ActionEvent event) {

    }
    
    //NAO MEXER AINDA
    @FXML
    void btnSobreNos(ActionEvent event) {

    }

}
