/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Utils;

import br.com.softtalk.SoftTalk;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            Date dtRetorno;
            dtRetorno = new java.sql.Date(rs.getTimestamp("Data").getTime());
           return dtRetorno;
        }
        return null;
    }
    
}
