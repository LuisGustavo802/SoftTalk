/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softtalk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author erasm
 */
public class SoftTalkController implements Initializable {
    
    //id botao de configurações
    @FXML
    private MenuItem Configurações;
    
    //id sair e ir pro login
    @FXML
    private MenuItem Sair;
    
    //id button para acessar menu
    @FXML
    private Button Menu;
    
    //id para pesquisa "NAO MEXER"
    @FXML
    private TextField Pesquise;
    
    //id solicita feedback "NAO MEXER"
    @FXML
    private MenuItem SolicitaFeedback;
    
    //id notificações "NAO MEXER"
    @FXML
    private Button Notificações; 
    
    //botao para acessar as configurações
    @FXML
    void btnConfigurações(ActionEvent event) {

    }
    
    //botao para acessar a tela do menu 
    @FXML
    void btnMenu(ActionEvent event) {

    }
    
    //botao para acessar a tela de Notificações "NAO MEXER AINDA"
    @FXML
    void btnNotificações(ActionEvent event) {

    }
    
    //botao para sair e fechar a conta do usuario
    @FXML
    void btnSair(ActionEvent event) {

    }
    
    //botao para solicitar feedback "NAO MEXER AINDA"
    @FXML
    void btnSolicitaFeedback(ActionEvent event) {

    }
    
    //botao para pesquisar "NAO MEXER AINDA"
    @FXML
    void textPesquise(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
