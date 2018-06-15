package br.com.conexao;

import br.com.softtalk.SoftTalk;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author luis_
 */
public class ConexaoController implements Initializable {

    @FXML
    private JFXTextField txIp, txPorta, txUser, txSenha;

    @FXML
    private Button btConfirma;

    @FXML
    protected void ConexaoAction(ActionEvent event) {
        confirmaConexao();
    }

    private void confirmaConexao() {
        SoftTalk.conexao = new Conexao(txIp.getText(), txPorta.getText(), txUser.getText(), txSenha.getText()).getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
