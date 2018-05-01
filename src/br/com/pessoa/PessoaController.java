package br.com.pessoa;

import br.com.Utils.Functions;
import br.com.setor.DAOSetor;
import br.com.setor.Setor;
import br.com.softtalk.SoftTalk;
import br.com.usuario.DAOUsuario;
import br.com.usuario.Usuario;
import br.com.usuario.UsuarioController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PessoaController implements Initializable {

    @FXML
    private ComboBox<Setor> bxSetor;

    @FXML
    private TextField txNome;

    @FXML
    private ImageView ivImagem;

    @FXML
    void salvarAction(ActionEvent event) {
        gravarPerfil();
    }

    @FXML
    void novaImagemAction(ActionEvent event) {
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
        Incializar();
        try {
            DAOUsuario daoUsuario;
            daoUsuario = new DAOUsuario();
            usuario = daoUsuario.listarUsuario(SoftTalk.getIdUsuarioLogado());
            carregarPessoa();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adicionarImagem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        String caminho = fileChooser.showOpenDialog(SoftTalk.stage).getPath();
        pessoa.setImagem(caminho, 447, 140);

        ivImagem.setImage(SwingFXUtils.toFXImage(pessoa.getImagem(), null));
    }

    private void Incializar() {

        functions = new Functions();
        usuario = new Usuario();
        pessoa = new Pessoa();
    }

    private void carregarPessoa() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DAOPessoa daopessoa = new DAOPessoa();
        //DAOSetor daosetor   = new DAOSetor();
        //Setor setor         = new Setor();

        carregarComboBox();

        pessoa = daopessoa.listaPessoa(usuario.getIdpessoa());

        txNome.setText(pessoa.getNome());

    }

    public void carregarComboBox() {
        DAOSetor daoSetor;
        try {
            daoSetor = new DAOSetor();
            listSetor = daoSetor.listarSetor();
            observableListSetor = FXCollections.observableArrayList(listSetor);
            bxSetor.setItems(observableListSetor);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
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
        voltarTela();
        return Functions.SUCCESS;
    }

    public void voltarTela() {
        try {
            Parent fxmlLoader = FXMLLoader.load(SoftTalk.class.getResource("SoftTalk.fxml"));
            SoftTalk.stage.setScene(new Scene(fxmlLoader));
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
