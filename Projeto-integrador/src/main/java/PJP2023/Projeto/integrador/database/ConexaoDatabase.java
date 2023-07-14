package PJP2023.Projeto.integrador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDatabase {

    private Connection conn = null;

    public synchronized Connection getConexao() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto_integrador", "postgres", "postgres");

        }

        return conn;
    }
}
