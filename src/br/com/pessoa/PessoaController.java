
package br.com.pessoa;

import br.com.Utils.Functions;
import br.com.equipe.DAOEquipe;
import br.com.equipe.Equipe;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
import br.com.usuario.UsuarioController;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

public class PessoaController implements Initializable {

    @FXML
    private ComboBox<Equipe> bxEquipe;

    @FXML
    private TextField txNome;
     
    @FXML
    private ImageView ivImagem;
    
    @FXML
    private JFXTextField txEmail;
    
    @FXML
    void salvarAction(ActionEvent event) {
        gravarPerfil();
    }

    @FXML
    void novaImagemAction(ActionEvent event) throws IOException {
        adicionarImagem();
    }

    @FXML
    void voltarAction(ActionEvent event) {
        voltarTela();
    }
    private List<Equipe> listEquipe = new ArrayList<>();
    private ObservableList<Equipe> observableListEquipe;
    private Functions functions;
    private Usuario usuario;
    private Pessoa pessoa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Incializar();
            try {
                carregarPessoa();
                carregarUsuario();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adicionarImagem() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar ");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(SoftTalk.stage);
        
        if (file != null ){
            pessoa.setImagem(ImageIO.read(file));
            Image img = SwingFXUtils.toFXImage(pessoa.getImagem(), null);
            if (img != null){
                this.ivImagem.setImage(img);
                    
            }
        }
    }

    private void Incializar() throws SQLException {

        functions = new Functions();
        usuario = new Usuario();
        pessoa = new Pessoa();
        DAOUsuario daoUsuario;
        daoUsuario = new DAOUsuario();
        usuario = daoUsuario.listarUsuario(SoftTalk.getIdUsuarioLogado());
        ivImagem.setPreserveRatio(true);
    }
    private void carregarUsuario() throws SQLException {
        txEmail.setText(usuario.getEmail());
    }

    private void carregarPessoa() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        DAOPessoa daopessoa = new DAOPessoa();
        carregarComboBox();
     
        Equipe equipe = new Equipe();
        DAOEquipe daoEquipe = new DAOEquipe();
        
        pessoa = daopessoa.listaPessoa(usuario.getIdpessoa());
        
        //se não existir imagem para pessoa seta uma de forma manual
        if (pessoa.getImagem() == null ){
            //imagem padrão da tela
            pessoa.setImagem( SwingFXUtils.fromFXImage(ivImagem.getImage() , null));
        }

        this.txNome.setText(pessoa.getNome());
        this.ivImagem.setImage(SwingFXUtils.toFXImage(pessoa.getImagem(), null));
        
        if (pessoa.getIdequipe() != null){
            equipe = daoEquipe.listaEquipe(pessoa.getIdequipe());
            bxEquipe.getSelectionModel().select(equipe);
        }
    }

    public void carregarComboBox() {
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

    public int gravarPerfil() {
        
        if (!validarEmail(txEmail.getText())){
            //Caso de falha nas validações
            return Functions.FAILURE;
        }
        
        Equipe equipe = bxEquipe.getSelectionModel().getSelectedItem();

        pessoa.setIdpessoa(usuario.getIdpessoa());
        pessoa.setNome(String.valueOf(txNome.getText()));
        pessoa.setIdequipe(equipe.getIdequipe());
        usuario.setEmail(txEmail.getText());
        DAOPessoa daopessoa;
        DAOUsuario daousuario;
        
        daousuario = new DAOUsuario();
        daopessoa = new DAOPessoa();

        if (daopessoa.atualizarPessoa(pessoa) < 0) {
            Functions.abrirMensagem("Problemas na gravação!");
            return Functions.FAILURE;
        }
        if (daousuario.atualizarUsuario(usuario) < 0) {
            Functions.abrirMensagem("Problemas na gravação!");
            return Functions.FAILURE;
        }

        Functions.abrirMensagem("Gravado com sucesso!");
        return Functions.SUCCESS;
    }

    public void voltarTela() {
        try {
            Parent fxmlLoader = FXMLLoader.load(Pessoa.class.getResource("Pessoa.fxml"));
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