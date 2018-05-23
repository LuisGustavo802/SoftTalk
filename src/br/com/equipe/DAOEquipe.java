
package br.com.equipe;

import br.com.softtalk.SoftTalk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOEquipe {

    public int inserirEquipe(Equipe equipe) throws SQLException {
        String sql = "INSERT INTO equipe (IDEMPRESA, NOME, FLAGATIVO) "
                + "VALUES ("
                + equipe.getIdempresa() +",'"
                + equipe.getNome() + "','"
                + equipe.getFlagativo() + "')";
        PreparedStatement pstm
                = SoftTalk.conexao.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public List<Equipe> listarEquipe() throws SQLException {
        Equipe equipe;
        List<Equipe> lista = new ArrayList();        
        String sql = "SELECT * FROM equipe;";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            equipe = new Equipe();          
            equipe.setIdequipe(rs.getInt("IdEquipe"));
            equipe.setIdempresa(rs.getInt("IdEmpresa"));
            equipe.setNome(rs.getString("Nome"));
            equipe.setFlagativo(rs.getString("FlagAtivo"));
            lista.add(equipe);
        }
        return lista;
    }
        public Equipe listaEquipe(int idEquipe) throws SQLException {
        Equipe equipe;     
        String sql = "SELECT * FROM equipe WHERE idequipe = "+ Integer.toString(idEquipe) +";";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        equipe = new Equipe();
        if (rs.next()){
            equipe.setIdequipe(rs.getInt("IdEquipe"));
            equipe.setIdempresa(rs.getInt("IdEmpresa"));
            equipe.setNome(rs.getString("Nome"));
            equipe.setFlagativo(rs.getString("FlagAtivo"));
        }
        return equipe;
    }
        
    public int excluirEquipe(Equipe equipe) throws SQLException {
        String sql = "DELETE FROM equipe WHERE idequipe ="
                + equipe.getIdequipe();
         PreparedStatement pstm
                = SoftTalk.conexao.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
