/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastroUsuario;

import br.com.softtalk.SoftTalk;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CadastroUsuarioController implements Initializable {

   //botão para voltar para a tela de Login
     @FXML
    private void botaoVoltaLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Telas/Tela1.fxml"));
        Scene scene = new Scene(root);
        
        SoftTalk.stage.setScene(scene);
        SoftTalk.stage.show();
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
