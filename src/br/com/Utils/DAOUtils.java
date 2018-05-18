/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import br.com.feedback.Feedback;
import br.com.softtalk.SoftTalk;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erasm
 */
public class DAOUtils {
    public Date carregaDataServidor() throws SQLException{
       
        String sql = "SELECT now()as Data;";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
           return rs.getDate("Data");
        }
        return null;
    }
    
}
