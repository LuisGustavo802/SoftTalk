package br.com.requestfeedback;

import br.com.Utils.Functions;
import br.com.pessoa.DAOPessoa;
import br.com.softtalk.SoftTalk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class DAORequestFeedback {

    public int inserirSolicitacaoFeedback(RequestFeedback requestFeedback) {
        try {
            String sql = "INSERT INTO feedback_solicitacao (idfeedback, tiposolicitacao, dtlimite) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstm;

            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, requestFeedback.getIdfeedback());
            pstm.setString(2, requestFeedback.getTipoSolicitacao());
            pstm.setString(3, requestFeedback.getTipoSolicitacao());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
    }

}
