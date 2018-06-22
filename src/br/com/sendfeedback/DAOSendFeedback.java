/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sendfeedback;

import br.com.Utils.Functions;
import br.com.feedback.DAOFeedback;
import br.com.feedback.Feedback;
import br.com.softtalk.SoftTalk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erasm
 */
public class DAOSendFeedback extends DAOFeedback {

    public int enviaFeedback(Feedback feedback) {
        int ret1, ret2;
        ret1 = gravarFeedBack(feedback);
        feedback.setIdFeedBack(ret1);
        ret2 = gravaEnvioFeedback(feedback);
        if (ret1 > 0 && ret2 > 0) {
            return Functions.SUCCESS;
        } else {
            return Functions.FAILURE;
        }
    }

    public int gravaEnvioFeedback(Feedback feedback) {
        try {
            String sql = "INSERT INTO feedback_envio (idFeedback, statusSend) "
                    + "VALUES ("
                    + feedback.getIdFeedBack() + ", '"
                    + feedback.getStatusSend() + "') ";

            PreparedStatement pstm;
            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
    }

    public List<String> carregaStatus(String list) throws SQLException {
        List<String> lista = new ArrayList();

        if (list.length() > 0) {
            String sql = "SELECT count(*) as count FROM feedback_envio WHERE idfeedback in( " + list + ") and statusSend = 'Que Bom';";
            Statement stm = SoftTalk.conexao.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getString("count"));
            }

            sql = "SELECT count(*) as count FROM feedback_envio WHERE idfeedback in( " + list + ") and statusSend = 'Que Pena';";
            stm = SoftTalk.conexao.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getString("count"));
            }

            sql = "SELECT count(*) as count FROM feedback_envio WHERE idfeedback in( " + list + ") and statusSend = 'Que Tal';";
            stm = SoftTalk.conexao.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getString("count"));
            }
        }
        return lista;

    }
}
