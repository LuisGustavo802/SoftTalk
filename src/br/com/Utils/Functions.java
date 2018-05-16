package br.com.Utils;

import Mensagem.MensagemController;
import br.com.login.LoginController;
import br.com.notificacoes.NotificacaoController;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author luis_
 */
public class Functions {

    public static int SUCCESS = 1;
    public static int FAILURE = -1;
    public static int NOACTION = 0;

    public String encript(String key) {
        String secured = null;
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(key.getBytes(), 0, key.length());

            secured = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return key;
        }
        return secured;
    }

    public void abrirMensagem(String textoMensagem) {
        try {

            Stage stage = new Stage(StageStyle.TRANSPARENT);

            Parent fxmlLoader = FXMLLoader.load(NotificacaoController.class.getResource("Mensagem.fxml"));

            FXMLLoader loader = new FXMLLoader(NotificacaoController.class.getResource(
                    "Mensagem.fxml"));
            Parent root = (Parent) loader.load();
            
            
            MensagemController ctrl = loader.getController();
            ctrl.setMensagem(textoMensagem);

            stage.setScene(new Scene(fxmlLoader));
            //lblMensagem.setText(mensagem);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirTela(String arquivoFXML, Object classe) {

    }

    public void mensagemPadrao(String teste) {

    }
}
