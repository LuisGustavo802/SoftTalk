package br.com.feedback;

import br.com.Utils.Functions;
import br.com.softtalk.SoftTalk;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFeedback {

    public int gravarFeedBack(Feedback feedback) {
        try {
            String sql = "INSERT INTO feedback (idusuarioremetente, idempresa, idusuariodestinatario, tipofeedback,"
                    + "dtmovimento, status, observacao) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm;
            pstm = SoftTalk.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, feedback.getIdUsuarioRemetente());
            pstm.setInt(2, feedback.getIdempresa());
            pstm.setInt(3, feedback.getIdUsuarioDestino());
            pstm.setString(4, feedback.getTipoFeedback());
            pstm.setDate(5, feedback.getDtMovimento());
            pstm.setString(6, feedback.getStatus());
            pstm.setString(7, feedback.getDescricao());

            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
            return Functions.FAILURE;
        }
    }

    public Feedback listaFeedback(int idFeedbackEnviar) throws SQLException, IOException {
        Feedback feedback;
        String sql = "SELECT * FROM feedback WHERE idfeedback = " + Integer.toString(idFeedbackEnviar) + ";";

        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        feedback = new Feedback();

        if (rs.next()) {
            feedback.setIdFeedBack(rs.getInt("idfeedback"));
            feedback.setIdUsuarioRemetente(rs.getInt("idusuarioremetente"));
            feedback.setIdempresa(rs.getInt("idempresa"));
            feedback.setIdUsuarioDestino(rs.getInt("idusuariodestinatario"));
            feedback.setTipoFeedback(rs.getString("tipofeedback"));
            feedback.setDtMovimento(rs.getDate("dtmovimento"));
            feedback.setStatus(rs.getString("status"));
            feedback.setDescricao(rs.getString("observacao"));

        }
        return feedback;
    }

    public List<Feedback> listarFeedbacks() throws SQLException {
        Feedback feedback;
        List<Feedback> lista = new ArrayList();
        String sql = "SELECT * FROM feedback WHERE idusuariodestinatario = " + Integer.toString(SoftTalk.getIdUsuarioLogado()) + ";";
        Statement stm = SoftTalk.conexao.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            feedback = new Feedback();
            feedback.setIdFeedBack(rs.getInt("idfeedback"));
            feedback.setIdUsuarioRemetente(rs.getInt("idusuarioremetente"));
            feedback.setTipoFeedback(rs.getString("tipofeedback"));
            feedback.setDtMovimento(rs.getDate("dtmovimento"));
            feedback.setStatus(rs.getString("status"));
            feedback.setDescricao(rs.getString("observacao"));
            lista.add(feedback);
        }
        return lista;
    }
}
