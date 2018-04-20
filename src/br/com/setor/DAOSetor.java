
package br.com.setor;

import br.com.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOSetor {
    private Conexao conexao;
    
    public DAOSetor() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{
        conexao = new Conexao();
    }

    public int inserirSetor(Setor setor) throws SQLException {
        String sql = "INSERT INTO setor (IDSETOR, NOME, FLAGATIVO) "
                + "VALUES ('"
                + setor.getIdsetor() + "', '"
                + setor.getNome() + "',"
                + setor.getFlagativo() + ")";
        PreparedStatement pstm
                = conexao.getConnection().prepareStatement(sql,
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
        Statement stm = conexao.getConnection().createStatement();
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
}
