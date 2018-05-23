package br.com.usuario;

import br.com.pessoa.Pessoa;
import br.com.pessoa.DAOPessoa;
import br.com.Utils.Functions;
import br.com.login.Login;
import br.com.equipe.DAOEquipe;
import br.com.equipe.Equipe;
import br.com.softtalk.SoftTalk;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class UsuarioController implements Initializable {
    @FXML
    private JFXComboBox<Equipe> bxEquipe;

    @FXML
    private JFXTextField txNome;
    
    @FXML
    private JFXTextField txUsuario;

     @FXML
    private JFXPasswordField pfSenha;
    
    @FXML
    private JFXPasswordField pfSenha1;

    @FXML
    private JFXTextField txEmail;
    
    private List<Equipe> listEquipe = new ArrayList<>();
    private ObservableList<Equipe> observableListEquipe;
    
    @FXML
    void cadastrarAction(ActionEvent event){
        cadastrarUsuario();
    }
    
    @FXML
    void voltarAction(ActionEvent event){
        voltarTelaLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarComboBox();

    }
       
    public void carregarComboBox(){
        DAOEquipe daoEquipe;
        try {
            daoEquipe = new DAOEquipe();
            listEquipe = daoEquipe.listarEquipe();
            observableListEquipe = FXCollections.observableArrayList(listEquipe);
            bxEquipe.setItems(observableListEquipe);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int cadastrarUsuario(){
        Functions functions = new Functions();
        Pessoa pessoa = new Pessoa();
        Usuario user = new Usuario();
        Statement st;
        ResultSet rs;
        Equipe equipe = bxEquipe.getSelectionModel().getSelectedItem(); 
        
        //Validações
        if (txNome.getText().isEmpty() || equipe == null || txEmail.getText().isEmpty() || txUsuario.getText().isEmpty() || pfSenha.getText().isEmpty() || pfSenha1.getText().isEmpty() ){
            Functions.abrirMensagem("Favor preencher todos os campos!");
            return Functions.FAILURE;
        }
        
        if (validarEmail(String.valueOf(txEmail.getText()))){
            user.setEmail(String.valueOf(txEmail.getText()));
        }else{
           return Functions.FAILURE; 
        }
         
        if(String.valueOf(pfSenha.getText()).equals(String.valueOf(pfSenha1.getText()))){
            user.setSenha(functions.encript(pfSenha.getText()));
        }else{
            Functions.abrirMensagem("Senhas diferentes. Favor corrigir!");
            return Functions.FAILURE;
        }
        
        pessoa.setNome(String.valueOf(txNome.getText()));
        pessoa.setIdequipe(equipe.getIdequipe());
        DAOPessoa daopessoa;
        daopessoa = new DAOPessoa();
        int codPessoa = daopessoa.inserirPessoa(pessoa);
        if (codPessoa < 0){
            Functions.abrirMensagem("Problemas na gravação!");
            return Functions.FAILURE;
        }
        user.setIdpessoa(codPessoa);
        user.setFlagativo("T");
        user.setTipo("F");
        user.setLogin(String.valueOf(txUsuario.getText()));
       
        DAOUsuario daousuario = new DAOUsuario();
        if (daousuario.inserirUsuario(user) < 0){
            Functions.abrirMensagem("Problemas na gravação!");
            return Functions.FAILURE;
        }
        Functions.abrirMensagem("Gravado com sucesso!");
        voltarTelaLogin();
        return Functions.SUCCESS;
    }
    
    public void voltarTelaLogin(){
        try {
            Parent fxmlLoader = FXMLLoader.load(Login.class.getResource("Login.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarEmail(String email){
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
        Matcher m = p.matcher(email); 
        if (!m.find()){
           Functions.abrirMensagem("E-mail inválido!");
           return false;
        }
        return true;
    }
    
}


