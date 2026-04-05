package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/loja";
        String user = "root";
        String password = "root";

        Connection conexao = null;
        conexao = DriverManager.getConnection(url, user, password);
        return conexao;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e);
            }
        }
    }
}