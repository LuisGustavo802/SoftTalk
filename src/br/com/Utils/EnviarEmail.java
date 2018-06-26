/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail {

    public void enviandoEmail(String emailDestinatario, int tipo) throws EmailException{
        new Thread() {      
        @Override
        public void run() {
            try {
                SimpleEmail emailSimples = new SimpleEmail();
                emailSimples.setHostName("smtp.gmail.com");
                emailSimples.setSSL(true);
                emailSimples.setSmtpPort(465);
                emailSimples.setAuthentication("softtalk.suporte@gmail.com", "softtalk123");
                emailSimples.setSSLOnConnect(true);
                emailSimples.setFrom("softtalk.suporte@gmail.com", "SoftTalk");
                emailSimples.addTo(emailDestinatario);
                emailSimples.setSubject("Notificação");
                if (tipo == 1 ){
                    emailSimples.setMsg("Você recebeu uma nova solicitação de feedback no SoftTalk!");
                }else{
                    emailSimples.setMsg("Você recebeu um novo feedback no SoftTalk!");
                }
                String x = emailSimples.send();
            } catch (EmailException ex) {
                Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
    }.start();
        
    }
}
