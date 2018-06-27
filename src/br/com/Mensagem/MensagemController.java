/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Mensagem;

import br.com.Utils.Functions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author luis_
 */
public class MensagemController implements Initializable {

    @FXML
    private Button btOk;

    @FXML
    private TextArea txMensagem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setMensagem(String mensagem) {
        this.txMensagem.setText(mensagem);
    }

    @FXML
    protected void OkAction(ActionEvent event) {
        Functions.stage.close();
    }
    
    @FXML
    protected void FechaAction(ActionEvent event) {
        Functions.stage.close();
    }


}
