/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author luis_
 */
public class Login {

    public void login() {
        try {
            Stage login = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
            AnchorPane page;

            page = (AnchorPane) fxmlLoader.load();

            login.toFront();
            login.setScene(new Scene(page));
            //login.getIcons().add(new Image("/imagens/logo2.png"));            
            login.setTitle("SoftTalk");
            login.setFullScreen(false);
            login.setResizable(false);
            login.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
