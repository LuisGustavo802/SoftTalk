package br.com.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luis_
 */
public class Functions {

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

    public void abrirTela(String arquivoFXML, Class classe) {

    }
}
