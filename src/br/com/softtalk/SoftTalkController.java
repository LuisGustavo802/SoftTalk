/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.pessoa.Pessoa;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SoftTalkController {

    @FXML
    private MenuController menuController;

    @FXML
    void perfilAction(ActionEvent event) {
        abrirPerfil();
    }

    private void abrirPerfil() {
        Parent fxmlLoader;
        try {
            fxmlLoader = FXMLLoader.load(Pessoa.class.getResource("Pessoa.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(SoftTalkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
