
package br.com.usuario;

import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuario {

    public int inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (IDPESSOA, LOGIN, SENHA, FLAGATIVO) "
                + "VALUES ("
                + usuario.getIdpessoa() + ", '"
                + usuario.getLogin() + "', '"
                + usuario.getSenha() + "','"
                + usuario.getFlagativo() + "')";
        PreparedStatement pstm;
        try {
            pstm = SoftTalk.conexao.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
        
        
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        Usuario usuario;
        List<Usuario> lista = new ArrayList();        
        String sql = "SELECT * FROM usuario;";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setIdpessoa(rs.getInt("IdPessoa"));
            usuario.setIdusuario(rs.getInt("IdUsuario"));
            usuario.setLogin(rs.getString("Login"));
            usuario.setSenha(rs.getString("Senha"));
            usuario.setFlagativo(rs.getString("FlagAtivo"));
            usuario.setIdusuario(rs.getInt("idEmpresa"));
            lista.add(usuario);
        }
        return lista;
    }
    public List<Usuario> listarUsuariosCondicao(int idPessoa) throws SQLException {
        return listarUsuariosCondicao("idPessoa = " + Integer.toString(idPessoa));
    }
    
    public List<Usuario> listarUsuariosCondicao(String condicao) throws SQLException {
        Usuario usuario;
        List<Usuario> lista = new ArrayList();        
        String sql = "SELECT * FROM usuario WHERE " + condicao + " ; ";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setIdpessoa(rs.getInt("IdPessoa"));
            usuario.setIdusuario(rs.getInt("IdUsuario"));
            usuario.setLogin(rs.getString("Login"));
            usuario.setSenha(rs.getString("Senha"));
            usuario.setFlagativo(rs.getString("FlagAtivo"));
            usuario.setIdusuario(rs.getInt("idEmpresa"));
            lista.add(usuario);
        }
        return lista;
    }
        
    public Usuario listarUsuario(int idUsuario) throws SQLException {
        Usuario usuario = new Usuario();    
        String sql = "SELECT * FROM usuario WHERE idusuario = " + Integer.toString(idUsuario) + ";";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        if (rs.next()){
            usuario.setIdpessoa(rs.getInt("IdPessoa"));
            usuario.setIdusuario(rs.getInt("IdUsuario"));
            usuario.setLogin(rs.getString("Login"));
            usuario.setSenha(rs.getString("Senha"));
            usuario.setFlagativo(rs.getString("FlagAtivo"));
            usuario.setIdusuario(rs.getInt("idEmpresa"));
        }
        return usuario;
    }

}
