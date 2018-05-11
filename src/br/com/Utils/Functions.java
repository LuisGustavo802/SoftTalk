package br.com.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;

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

   
    public void mensagemPadrao(String textoMensagem){
        this.mensagemPadrao(textoMensagem, "SoftTalk");
    }
    
    public void mensagemPadrao(String textoMensagem, String titulo){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(textoMensagem);
        alert.showAndWait();
    }
    
    public void abrirTela(String arquivoFXML, Object classe) {
 
    }
}
