/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setor;

import br.com.Utils.Functions;
import br.com.softtalk.MenuController;
import br.com.softtalk.SoftTalk;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author free
 */
public class SetorController implements Initializable {

    @FXML
    public MenuController menuController;
    
    @FXML
    public JFXTextField txNomeSetor;
    Setor setor = new Setor();
    DAOSetor daoSetor = new DAOSetor();
    @FXML
    public TableColumn tblNome;
    @FXML
    public TableColumn tblQuant;
    @FXML
    private TableView<Setor> tabelaSetor;
    
    private List<Setor> listSetor = new ArrayList<>();
    private ObservableList<Setor> observableListSetor;
    
    Functions functions = new Functions();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(SetorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    void incluirAction(ActionEvent event) throws SQLException{
        incluirSetor();
    }
    
    @FXML
    void excluirAction(ActionEvent event) throws SQLException{
        excluirSetor();
    }
    
    public void incluirSetor() throws SQLException{
        setor.setNome(String.valueOf(txNomeSetor.getText()));
        setor.setFlagativo("T"); 
        daoSetor.inserirSetor(setor);
        inicializarTabela();
        txNomeSetor.setText("");
    }
    
    public void inicializarTabela() throws SQLException{
        Statement st = SoftTalk.conexao.createStatement();
        ResultSet rs;
        listSetor = daoSetor.listarSetor();
        for (int i=0; i<listSetor.size(); i++){
            rs = st.executeQuery("SELECT count(*) as total FROM pessoa JOIN setor ON pessoa.idsetor = setor.idsetor WHERE setor.idsetor = "+ listSetor.get(i).getIdsetor());
            if (rs.next()){
                listSetor.get(i).setQuant(rs.getInt("total"));
            }
        }
        
        observableListSetor = FXCollections.observableArrayList(listSetor);
        tblNome.setCellValueFactory( new PropertyValueFactory<>("Nome"));
        tblQuant.setCellValueFactory( new PropertyValueFactory<>("Quant"));
        tabelaSetor.setItems(observableListSetor);
    }
    
    public int excluirSetor() throws SQLException{
        Setor setorDelete = tabelaSetor.getSelectionModel().getSelectedItem();
        try {
            if (setorDelete.getQuant()>0 ){
                functions.mensagemPadrao("Setor possui pessoas cadastradas. Não será possível realizar a exclusão!");
                return Functions.FAILURE;
            }else{
                daoSetor.excluirSetor(setorDelete);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        functions.mensagemPadrao("Setor excluído com sucesso!");
        inicializarTabela();
        return Functions.SUCCESS;
    }
}
