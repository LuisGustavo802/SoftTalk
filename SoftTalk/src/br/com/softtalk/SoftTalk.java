/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.login.Login;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author Aluno
 */
public class SoftTalk extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        new Login().login();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
      
    }
    
}
