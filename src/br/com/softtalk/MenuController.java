/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.pessoa.Pessoa;
import br.com.setor.Setor;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author erasm
 */
public class MenuController implements Initializable {

    @FXML
    Button btnSetor;
    @FXML
    ImageView imgSetor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = SoftTalk.conexao.createStatement();
            rs = st.executeQuery("Select usuario_admin from usuario where idusuario = " + Integer.toString(SoftTalk.getIdUsuarioLogado()));
            if (rs.next()) {
                if (rs.getString("usuario_admin").equals("F")) {
                    btnSetor.setVisible(false);
                    imgSetor.setVisible(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void perfilAction(ActionEvent event) {
        Parent fxmlLoader;
        try {
            fxmlLoader = FXMLLoader.load(Pessoa.class.getResource("Pessoa.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void setorAction(ActionEvent event) {
       try {
            Parent fxmlLoader = FXMLLoader.load(Setor.class.getResource("Setor.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

}
