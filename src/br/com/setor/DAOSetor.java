
package br.com.setor;

import br.com.softtalk.SoftTalk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOSetor {

    public int inserirSetor(Setor setor) throws SQLException {
        String sql = "INSERT INTO setor (NOME, FLAGATIVO) "
                + "VALUES ('"
                + setor.getNome() + "','"
                + setor.getFlagativo() + "')";
        PreparedStatement pstm
                = SoftTalk.conexao.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public List<Setor> listarSetor() throws SQLException {
        Setor setor;
        List<Setor> lista = new ArrayList();        
        String sql = "SELECT * FROM setor;";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            setor = new Setor();
            setor.setIdsetor(rs.getInt("IdSetor"));
            setor.setNome(rs.getString("Nome"));
            setor.setFlagativo(rs.getString("FlagAtivo"));
            lista.add(setor);
        }
        return lista;
    }
        public Setor listaSetor(int idSetor) throws SQLException {
        Setor setor;     
        String sql = "SELECT * FROM setor WHERE idsetor = "+ Integer.toString(idSetor) +";";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        setor = new Setor();
        if (rs.next()){
            setor.setIdsetor(rs.getInt("IdSetor"));
            setor.setNome(rs.getString("Nome"));
            setor.setFlagativo(rs.getString("FlagAtivo"));
        }
        return setor;
    }
        
    public int excluirSetor(Setor setor) throws SQLException {
        String sql = "DELETE FROM setor WHERE idsetor ="
                + setor.getIdsetor();
         PreparedStatement pstm
                = SoftTalk.conexao.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
