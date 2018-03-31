
package br.com.dao;

import br.com.conexao.Conexao;
import br.com.entidades.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoa {
    private Conexao conexao;
    
    public DAOPessoa() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{
        conexao = new Conexao();
    }

    public int inserirPessoa(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (IDPESSOA, IDSETOR, NOME, FLAGATIVO) "
                + "VALUES ('"
                + pessoa.getIdpessoa() + "', '"
                + pessoa.getIdsetor() + "',"
                + pessoa.getNome() + ", "
                + pessoa.getFlagativo() + ")";
        PreparedStatement pstm
                = conexao.getConnection().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        Pessoa pessoa;
        List<Pessoa> lista = new ArrayList();        
        String sql = "SELECT * FROM pessoa;";
        Statement stm = conexao.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setIdpessoa(rs.getInt("IdPessoa"));
            pessoa.setIdsetor(rs.getInt("IdSetor"));
            pessoa.setNome(rs.getString("Nome"));
            pessoa.setFlagativo(rs.getString("FlagAtivo"));
            lista.add(pessoa);
        }
        return lista;
    }
}
