/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensagem;

import br.com.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author luis_
 */
public class MensagemController implements Initializable {

    @FXML
    private Button btOk;

    @FXML
    private Label lblMensagem;

    private String mensagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.lblMensagem.setText(mensagem);
    }

    public void abrirMensagem() {
        /*try {

            Stage stage = new Stage(StageStyle.TRANSPARENT);

            Parent fxmlLoader = FXMLLoader.load(MensagemController.class.getResource("Mensagem.fxml"));
            stage.setScene(new Scene(fxmlLoader));
            lblMensagem.setText(mensagem);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
