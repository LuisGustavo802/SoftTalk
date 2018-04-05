/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.login;

import br.com.cadastroUsuario.CadastroUsuario;
import java.awt.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author luis_
 */
public class LoginController {
    
    @FXML
    private void entrarCadastro(ActionEvent event){
        new CadastroUsuario().cadastroUsuario();
    }

}