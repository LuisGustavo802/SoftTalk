/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.usuario.PerfilController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SoftTalkController implements Initializable {
@FXML
private MenuController menuController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        @FXML
        void perfilAction(ActionEvent event) throws IOException{
            abrirPerfil();
        }
        private void abrirPerfil() throws IOException {
            Parent fxmlLoader = FXMLLoader.load(PerfilController.class.getResource("Perfil.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        }
    
}
