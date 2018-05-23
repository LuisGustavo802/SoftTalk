/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.equipe;

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
public class EquipeController implements Initializable {

    @FXML
    public MenuController menuController;

    @FXML
    public JFXTextField txNomeEquipe;
    Equipe equipe = new Equipe();
    DAOEquipe daoEquipe = new DAOEquipe();
    @FXML
    public TableColumn tblNome;
    @FXML
    public TableColumn tblQuant;
    @FXML
    private TableView<Equipe> tabelaEquipe;

    private List<Equipe> listEquipe = new ArrayList<>();
    private ObservableList<Equipe> observableListEquipe;

    Functions functions = new Functions();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void incluirAction(ActionEvent event) throws SQLException {
        incluirEquipe();
    }

    @FXML
    void excluirAction(ActionEvent event) throws SQLException {
        excluirEquipe();
    }

    public void incluirEquipe() throws SQLException {
        if (String.valueOf(txNomeEquipe.getText()).equals("")) {
            Functions.abrirMensagem("Atenção! Informar um nome da equipe.");
        } else {
            equipe.setNome(String.valueOf(txNomeEquipe.getText()));
            equipe.setFlagativo("T");
            daoEquipe.inserirEquipe(equipe);
            inicializarTabela();
            txNomeEquipe.setText("");
        }
    }

    public void inicializarTabela() throws SQLException {
        Statement st = SoftTalk.conexao.createStatement();
        ResultSet rs;
        listEquipe = daoEquipe.listarEquipe();
        for (int i = 0; i < listEquipe.size(); i++) {
            rs = st.executeQuery("SELECT count(*) as total FROM pessoa JOIN equipe ON pessoa.idequipe = equipe.idequipe WHERE equipe.idequipe = " + listEquipe.get(i).getIdequipe());
            if (rs.next()) {
                listEquipe.get(i).setQuant(rs.getInt("total"));
            }
        }

        observableListEquipe = FXCollections.observableArrayList(listEquipe);
        tblNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblQuant.setCellValueFactory(new PropertyValueFactory<>("Quant"));
        tabelaEquipe.setItems(observableListEquipe);
    }

    public int excluirEquipe() throws SQLException {
        Equipe equipeDelete;
        if (tabelaEquipe.getSelectionModel().getSelectedItem() == null) {
            Functions.abrirMensagem("Atenção! Selecionar uma equipe para exclusão.");
            return Functions.FAILURE;
        } else {
            equipeDelete = tabelaEquipe.getSelectionModel().getSelectedItem();
        }
        try {
            if (equipeDelete.getQuant() > 0) {
                Functions.abrirMensagem("Atenção! Equipe possui pessoas cadastradas. Não será possível realizar a exclusão!");
                return Functions.FAILURE;
            } else {
                daoEquipe.excluirEquipe(equipeDelete);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Functions.abrirMensagem("Equipe excluida com sucesso.");
        inicializarTabela();
        return Functions.SUCCESS;
    }
}
