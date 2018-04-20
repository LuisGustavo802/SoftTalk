
package br.com.usuario;

import br.com.conexao.Conexao;
import br.com.usuario.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPessoa {
    private Conexao conexao;
    
    public DAOPessoa() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{
        conexao = new Conexao();
    }

    public int inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (IDSETOR, NOME) "
                + "VALUES ("
                + pessoa.getIdsetor() + ",'"
                + pessoa.getNome() + "')";
        PreparedStatement pstm;
        try {
            pstm = conexao.getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }  
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        Pessoa pessoa;
        List<Pessoa> lista = new ArrayList();        
        String sql = "SELECT * FROM pessoa;";
        Statement stm = conexao.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setIdsetor(rs.getInt("IdSetor"));
            pessoa.setNome(rs.getString("Nome"));
            lista.add(pessoa);
        }
        return lista;
    }
}
