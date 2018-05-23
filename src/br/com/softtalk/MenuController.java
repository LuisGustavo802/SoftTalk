/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.pessoa.Pessoa;
import br.com.sendfeedback.SendFeedbackController;
import br.com.equipe.Equipe;
import br.com.telainicial.TelaInicialController;
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
import br.com.requestfeedback.RequestFeedbackController;

/**
 * FXML Controller class
 *
 * @author erasm
 */
public class MenuController implements Initializable {

    @FXML
    Button btnEquipe;
    @FXML
    ImageView imgEquipe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = SoftTalk.conexao.createStatement();
            rs = st.executeQuery("Select tipo from usuario where idusuario = " + Integer.toString(SoftTalk.getIdUsuarioLogado()));
            if (rs.next()) {
                if (!rs.getString("tipo").equals("A")) {
                    btnEquipe.setVisible(false);
                    imgEquipe.setVisible(false);
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
    void equipeAction(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(Equipe.class.getResource("Equipe.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void inicialAction(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(TelaInicialController.class.getResource("TelaInicial.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void solicitaAction(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(RequestFeedbackController.class.getResource("RequestFeedback.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void enviarFeedback(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(SendFeedbackController.class.getResource("SendFeedback.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
