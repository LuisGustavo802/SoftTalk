/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Mensagem;

import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMensagem(String mensagem) {
        this.lblMensagem.setText(mensagem);
    }

    @FXML
    protected void OkAction(ActionEvent event) {
        Functions.stage.close();
    }

}
