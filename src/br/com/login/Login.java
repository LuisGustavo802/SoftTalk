/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import br.com.softtalk.SoftTalk;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import br.com.Imagens.Imagens;


/**
 *
 * @author luis_
 */
public class Login {

    public void login() {
        try {
            Parent fxmlLoader = FXMLLoader.load(Login.class.getResource("Login.fxml"));

            SoftTalk.stage.getIcons().add(new Image( Imagens.class.getResource("icon.png").toExternalForm())); 

            SoftTalk.stage.toFront();
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
            SoftTalk.stage.setTitle("SoftTalk");
            SoftTalk.stage.setFullScreen(false);
            SoftTalk.stage.setResizable(false);
            SoftTalk.stage.showAndWait();
            
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
