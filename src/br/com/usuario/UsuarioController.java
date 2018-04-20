/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author free
 */
public class UsuarioController implements Initializable {

    @FXML
    private PasswordField senha;

    @FXML
    private ComboBox<?> bxSetor;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha1;

    @FXML
    private TextField txNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
