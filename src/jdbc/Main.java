package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	
	public static void main (String[] args) {
		listar();
	}
	
    // INSERIR um novo funcionário
    public static void inserir(String nome, String sobrenome, int idade, double salario) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.createConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO funcionario (nome, sobrenome, idade, salario) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);
            ps.setInt(3, idade);
            ps.setDouble(4, salario);
            ps.executeUpdate();
            conn.commit();
            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("Erro ao inserir: " + e);
            } catch (SQLException e2) {
                System.out.println("Erro no rollback: " + e2);
            }
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.setAutoCommit(true);
                ConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e);
            }
        }
    }

    // LISTAR todos os funcionários
    public static void listar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.createConnection();
            String sql = "SELECT codigo, nome, sobrenome, idade, salario FROM funcionario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString(2);
                String sobrenome = rs.getString("sobrenome");
                int idade = rs.getInt("idade");
                double salario = rs.getDouble("salario");
                System.out.printf("Código %d: %s %s - %d anos | Salário: %.2f%n",
                    codigo, nome, sobrenome, idade, salario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                ConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e);
            }
        }
    }

    // ATUALIZAR dados de um funcionário
    public static void atualizar(int codigo, String nome, String sobrenome, int idade, double salario) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.createConnection();
            String sql = "UPDATE funcionario SET nome=?, sobrenome=?, idade=?, salario=? WHERE codigo=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);
            ps.setInt(3, idade);
            ps.setDouble(4, salario);
            ps.setInt(5, codigo);
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e);
        } finally {
            try {
                if (ps != null) ps.close();
                ConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e);
            }
        }
    }

    // EXCLUIR um funcionário pelo código
    public static void excluir(int codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionFactory.createConnection();
            String sql = "DELETE FROM funcionario WHERE codigo=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Funcionário excluído com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e);
        } finally {
            try {
                if (ps != null) ps.close();
                ConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e);
            }
        }
    }
}