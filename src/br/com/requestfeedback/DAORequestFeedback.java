package br.com.requestfeedback;

import br.com.Utils.Functions;
import br.com.feedback.Feedback;
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

    public int inserirSolicitacaoFeedback(Feedback feedback) {
        try {
            String sql = "INSERT INTO solicitacao_feedback (TIPOFEEDBACK, IDPESSOA, MENSAGEM) "
                    + "VALUES ('"
                    + feedback.getTipoFeedback() + "',"
                    + feedback.getIdUsuarioRemetente() + ", '"
                    + feedback.getDescricao() + "')";

            PreparedStatement pstm;
            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
