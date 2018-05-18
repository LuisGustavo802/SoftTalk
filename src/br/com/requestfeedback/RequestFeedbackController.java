/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.requestfeedback;

import br.com.pessoa.Pessoa;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author free
 */
public class RequestFeedbackController implements Initializable {

    @FXML
    private JFXTextArea txMensagem;

    @FXML
    private JFXComboBox<Pessoa> bxPessoa;

    @FXML
    private JFXComboBox bxTipo;

    @FXML
    protected void SolicitarFeedbackAction(ActionEvent event) {
        solicitarFeedback();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void solicitarFeedback() {

        if (bxTipo.getSelectionModel().getSelectedItem().equals("")) {

        }

    }

}
