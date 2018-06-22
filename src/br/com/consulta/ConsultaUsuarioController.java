package br.com.consulta;
import br.com.pessoa.DAOPessoa;
import br.com.pessoa.Pessoa;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaUsuarioController implements Initializable {

    //Classes FXML
    @FXML
    private TextField txNome;
    
    @FXML
    private TableView tbvListaUsuarios;
    
    @FXML
    private TableColumn tbcLogin;
     
    @FXML
    private TableColumn tbcNome;
    
    //Classes Controller
    DAOPessoa DAOpessoa;
    ArrayList<Pessoa> listaPessoas;
    
    @FXML
    public void selecionarUsuarioAction(){
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            iniciarComponentes();
            carregaUsuarios();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarComponentes(){
        DAOpessoa = new DAOPessoa();
    }   
    private void carregaUsuarios() throws SQLException{
        //Converte para um ArrayList o List
        listaPessoas = DAOpessoa.listarPessoas();
        
        ObservableList<Pessoa> observableListPessoa;
        observableListPessoa = FXCollections.observableArrayList(listaPessoas);
        tbcNome.setCellValueFactory( new PropertyValueFactory<>("Nome"));
        tbcLogin.setCellValueFactory( new PropertyValueFactory<>("Login"));
        tbvListaUsuarios.setItems(observableListPessoa);
        
    }
}
