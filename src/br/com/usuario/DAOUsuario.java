
package br.com.usuario;

import br.com.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuario {
    private Conexao conexao;
    
    public DAOUsuario() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{
        conexao = new Conexao();
    }

    public int inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (IDPESSOA, LOGIN, SENHA, FLAGATIVO) "
                + "VALUES ("
                + usuario.getIdpessoa() + ", '"
                + usuario.getLogin() + "', '"
                + usuario.getSenha() + "','"
                + usuario.getFlagativo() + "')";
        PreparedStatement pstm;
        try {
            pstm = conexao.getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        
    }

    public List<Usuario> listarUsuario() throws SQLException {
        Usuario usuario;
        List<Usuario> lista = new ArrayList();        
        String sql = "SELECT * FROM usuario;";
        Statement stm = conexao.getConnection().createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setIdpessoa(rs.getInt("IdPessoa"));
            usuario.setIdusuario(rs.getInt("IdUsuario"));
            usuario.setLogin(rs.getString("Login"));
            usuario.setSenha(rs.getString("Senha"));
            usuario.setFlagativo(rs.getString("FlagAtivo"));
            lista.add(usuario);
        }
        return lista;
    }
}
