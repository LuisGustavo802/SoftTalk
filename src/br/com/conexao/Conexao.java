package br.com.conexao;

//Importações
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection connection = null;

    public Conexao() throws ClassNotFoundException, InstantiationException,
        IllegalAccessException, SQLException {
        String databaseURL = "jdbc:postgresql://localhost/postgres";
        String usuario = "postgres";
        String senha = "postgres";
        String driverName = "org.postgresql.Driver";
        //Carrega o driver
        Class.forName(driverName).newInstance();
        //Conecta o BD
        connection = DriverManager.getConnection(databaseURL, usuario, senha);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
