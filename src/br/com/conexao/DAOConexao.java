/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.conexao;

import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import static br.com.softtalk.SoftTalk.conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class DAOConexao {

    public int inserirConexao(Conexao conexao) {
        try {
            String sql = "INSERT INTO config_conexao (ipconexao, portaconexao, user, password) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstm;

            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, conexao.getIpConnection());
            pstm.setString(2, conexao.getPortaConnection());
            pstm.setString(3, conexao.getUser());
            pstm.setString(4, conexao.getPassword());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOConexao.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
    }

    public int AtualizarConexao(Conexao conexao) {
        try {
            String sql = "UPDATE config_conexao SET ipconexao = ?, portaconexao = ?, user = ?, password = ?;";

            PreparedStatement pstm;

            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.KEEP_CURRENT_RESULT);

            pstm.setString(1, conexao.getIpConnection());
            pstm.setString(2, conexao.getPortaConnection());
            pstm.setString(3, conexao.getUser());
            pstm.setString(4, conexao.getPassword());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOConexao.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
    }

    public ArrayList<Conexao> BuscaConexao() {
        try {
            Conexao pessoa;
            ArrayList<Conexao> lista = new ArrayList();
            String sql = "SELECT * FROM config_conexao;";

            Statement stm;

            stm = SoftTalk.conexao.createStatement();

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                conexao = new Conexao();
                conexao.setdpessoa(rs.getInt("Idpessoa"));
                conexao.setIdequipe(rs.getInt("IdEquipe"));
                conexao.setNome(rs.getString("Nome"));
                lista.add(conexao);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(DAOConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
