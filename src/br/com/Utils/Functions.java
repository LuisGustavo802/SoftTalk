package br.com.Utils;

import br.com.Mensagem.MensagemController;
import br.com.login.LoginController;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public static Stage stage = new Stage(StageStyle.TRANSPARENT);

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

    public static void abrirMensagem(String textoMensagem) {
        try {

            FXMLLoader loader = new FXMLLoader(MensagemController.class.getResource("Mensagem.fxml"));
            Parent root = (Parent) loader.load();

            MensagemController ctrl = loader.getController();
            ctrl.setMensagem(textoMensagem);

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirTela(String arquivoFXML, Object classe) {

    }
}
