/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequiel
 */

public class Conexao {
    private static final String URL = "jdbc:sqlite:agba.db";
    private static Connection conn = null;

    public static Connection conectar() throws SQLException {
        try {
            // Força o carregamento do driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver SQLite não encontrado!", e);
        }

        // Mostra o caminho absoluto do arquivo .db (ajuda a debugar)
        File dbFile = new File("agba.db");
        System.out.println("Caminho do banco: " + dbFile.getAbsolutePath());

        return conn;
    }

    public static void verificarEstrutura(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuario ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "login TEXT NOT NULL,"
                   + "senha TEXT NOT NULL"
                   + ");";
        conn.createStatement().execute(sql);
    }
    
    public static void encerrarAplicacao(Connection conn) {
    try {
        if (conn != null && !conn.isClosed()) {
            conn.close(); 
            System.out.println("Conexão com o banco encerrada com sucesso.");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao fechar o banco: " + e.getMessage());
    } finally {
        System.exit(0); 
    }
    }
    
    public static Connection getConexaoAtual() {
        return conn;
    }
    
    public static void inicializarTabelas() {
        try {
            new RepositorioMedico();
            new RepositorioPaciente();
            new RepositorioConsulta();
        } catch (Exception e) {
            e.printStackTrace(); // Log no console
            JOptionPane.showMessageDialog(null, "Erro ao inicializar o banco de dados: " + e.getMessage(),
                    "Erro de Inicialização", JOptionPane.ERROR_MESSAGE);
        }
    }
}



