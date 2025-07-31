package principal;

import dados.Conexao;
import gui.JFrameLogin;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        try {
            Connection conexao = Conexao.conectar();
            System.out.println("Conexão estabelecida com sucesso!");

            Conexao.verificarEstrutura(conexao);
            Conexao.inicializarTabelas();

            java.awt.EventQueue.invokeLater(() -> {
                new JFrameLogin().setVisible(true);
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println(new java.io.File("agba.db").getAbsolutePath());
            System.exit(1);
        }
    }
}

