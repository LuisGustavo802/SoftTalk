/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import br.com.conexao.Conexao;
import br.com.login.Login;
import br.com.login.LoginController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SoftTalk extends Application {
    private static int idUsuarioLogado;//Mantem o usuario em que esta rodando a aplicação

    public static Connection conexao;
    public final static Stage stage = new Stage(StageStyle.DECORATED);
    
    
    public static void setIdUsuarioLogado(int idUsuarioLogado) {
        SoftTalk.idUsuarioLogado = idUsuarioLogado;
    }
 
    public static int getIdUsuarioLogado() {
        return idUsuarioLogado;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            this.conexao = new Conexao().getConnection();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Login().login();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
