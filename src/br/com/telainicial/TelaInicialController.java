/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telainicial;

import br.com.Utils.Functions;
import br.com.feedback.DAOFeedback;
import br.com.feedback.Feedback;
import br.com.pessoa.DAOPessoa;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kaliane Viesseli
 */
public class TelaInicialController implements Initializable{

    @FXML
    public TabPane abasVisualizacao;
    
    @FXML
    public Tab aba0;
    @FXML
    public Tab aba1;
    @FXML
    public ListView<Feedback> listViewFeedbacks;  
    @FXML
    public TextField txtNomeRemetente, txtData;   
    @FXML
    public TextArea txtFeedback;
    
    private List<Feedback> listFeedback = new ArrayList<>();
    private ObservableList<Feedback> observableListFeedback;
    
    Functions functions = new Functions();
    DAOFeedback daoFeedback = new DAOFeedback();
    DAOPessoa daopes = new DAOPessoa();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarListView();
    } 
    
    @FXML
    void detalhesAction(ActionEvent event) throws SQLException, IOException{
        exibirDetalhes();
    }
    @FXML
    void voltarAction(ActionEvent event){
        inicializarListView();
    }
    
    public void inicializarListView(){
        //Desabilitar aba detalhes
        abasVisualizacao.getTabs().get(1).getContent().setVisible(false);
        aba1.setDisable(true);
        //habilitar e selecionar aba lista
        abasVisualizacao.getTabs().get(0).getContent().setVisible(true);
        aba0.setDisable(false);
        abasVisualizacao.getSelectionModel().select(aba0);
        try {
            listFeedback = daoFeedback.listarFeedbacksRecebidos();
            observableListFeedback = FXCollections.observableArrayList(listFeedback);
            listViewFeedbacks.setItems(observableListFeedback);
        } catch (SQLException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public int exibirDetalhes() throws SQLException, IOException{
        Feedback feedbackSelecionado;
        if (listViewFeedbacks.getSelectionModel().getSelectedItem() != null){
            feedbackSelecionado  = listViewFeedbacks.getSelectionModel().getSelectedItem(); 
        }else{
            return Functions.FAILURE;
        }
       //Desabilitar aba lista
        abasVisualizacao.getTabs().get(0).getContent().setVisible(false);
        aba0.setDisable(true);
        //habilitar e selecionar aba detalhes
        abasVisualizacao.getTabs().get(1).getContent().setVisible(true);
        aba1.setDisable(false);
        abasVisualizacao.getSelectionModel().select(aba1);
        txtNomeRemetente.setText(daopes.listaNomePessoa(feedbackSelecionado.getIdUsuRemetente()));
        txtData.setText(formato.format(feedbackSelecionado.getDtMovimento()));
        txtFeedback.setText(feedbackSelecionado.getMensagem());
        return Functions.SUCCESS;
    }
    
}
