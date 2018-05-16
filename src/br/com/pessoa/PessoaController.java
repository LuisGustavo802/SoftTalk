
package br.com.pessoa;

import br.com.Utils.Functions;
import br.com.setor.DAOSetor;
import br.com.setor.Setor;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
import br.com.usuario.UsuarioController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

public class PessoaController implements Initializable {

    @FXML
    private ComboBox<Setor> bxSetor;
    
    @FXML
    private ComboBox<Setor> bxSetor1;

    @FXML
    private TextField txNome;
    
    @FXML
    private TextField txNome1;
     
    @FXML
    private ImageView ivImagem;
    
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
    private List<Setor> listSetor = new ArrayList<>();
    private ObservableList<Setor> observableListSetor;
    private Functions functions;
    private Usuario usuario;
    private Pessoa pessoa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Incializar();
            try {
                carregarPessoa();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
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

    private void carregarPessoa() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        DAOPessoa daopessoa = new DAOPessoa();
        carregarComboBox();
     
        Setor setor = new Setor();
        DAOSetor daoSetor = new DAOSetor();
        
        pessoa = daopessoa.listaPessoa(usuario.getIdpessoa());
        
        //se não existir imagem para pessoa seta uma de forma manual
        if (pessoa.getImagem() == null ){
            //imagem padrão da tela
            pessoa.setImagem( SwingFXUtils.fromFXImage(ivImagem.getImage() , null));
        }

        this.txNome.setText(pessoa.getNome());
        this.ivImagem.setImage(SwingFXUtils.toFXImage(pessoa.getImagem(), null));
        
        //Pegando valores e setando que ele nao pode ser editado.
        this.txNome1.setText(pessoa.getNome());
        txNome1.setEditable(false);
        
        if (pessoa.getIdsetor() != null){
            setor = daoSetor.listaSetor(pessoa.getIdsetor());
            bxSetor.getSelectionModel().select(setor);
           
            //Pegando valores e setando que ele nao pode ser editado.
            bxSetor1.getSelectionModel().select(setor);
            bxSetor1.setEditable(false);
        }
    }

    public void carregarComboBox() {
        DAOSetor daoSetor;
        try {
            daoSetor = new DAOSetor();
            listSetor = daoSetor.listarSetor();
            observableListSetor = FXCollections.observableArrayList(listSetor);
            bxSetor.setItems(observableListSetor);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int gravarPerfil() {
        
        Setor setor = bxSetor.getSelectionModel().getSelectedItem();

        pessoa.setIdpessoa(usuario.getIdpessoa());
        pessoa.setNome(String.valueOf(txNome.getText()));
        pessoa.setIdsetor(setor.getIdsetor());
        DAOPessoa daopessoa;
        daopessoa = new DAOPessoa();

        if (daopessoa.atualizarPessoa(pessoa) < 0) {
            functions.mensagemPadrao("Problemas na gravação!");
            return Functions.FAILURE;
        }

        functions.mensagemPadrao("Gravado com sucesso!");
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

}