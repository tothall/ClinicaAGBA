/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import dados.Conexao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;


/**
 *
 * @author ezequiel
 */
public class TabelaUtilitariaBD {

    /**
     * Lista geral de qualquer entidade.
     * @param tabela Nome da tabela no banco (ex: "medico", "paciente", "consulta")
     */
    public static void exibirTodos(String tabela) {
        String sql = "SELECT * FROM " + tabela;
        exibirPorConsulta(sql, "Lista de " + capitalize(tabela));
    }

    /**
     * Exibe resultados filtrados por até 3 campos.
     * Prioriza o primeiro campo não nulo e não vazio.
     */
    public static void exibirFiltrado(String tabela,
                                      String campo1, String valor1,
                                      String campo2, String valor2,
                                      String campo3, String valor3) {

        String sql = "SELECT * FROM " + tabela + " WHERE ";
        String filtro = "";

        if (valor1 != null && !valor1.isBlank()) {
            filtro = campo1 + " LIKE '%" + valor1 + "%'";
        } else if (valor2 != null && !valor2.isBlank()) {
            filtro = campo2 + " LIKE '%" + valor2 + "%'";
        } else if (valor3 != null && !valor3.isBlank()) {
            filtro = campo3 + " LIKE '%" + valor3 + "%'";
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum filtro preenchido.");
            return;
        }

        sql += filtro;
        exibirPorConsulta(sql, "Busca em " + capitalize(tabela));
    }

    /**
     * Executa uma consulta SQL e exibe o resultado em uma JTable com total de registros.
     */
    public static void exibirPorConsulta(String sql, String titulo) {
        try (Connection conexao = Conexao.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs == null || !rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Resultado vazio ou nulo.");
                return;
            }

            ResultSetMetaData metaData = rs.getMetaData();
            int colunas = metaData.getColumnCount();

            Vector<String> nomesColunas = new Vector<>();
            for (int i = 1; i <= colunas; i++) {
                nomesColunas.add(metaData.getColumnName(i));
            }

            Vector<Vector<Object>> dados = new Vector<>();
            while (rs.next()) {
                Vector<Object> linha = new Vector<>();
                for (int i = 1; i <= colunas; i++) {
                    linha.add(rs.getObject(i));
                }
                dados.add(linha);
            }

            DefaultTableModel modelo = new DefaultTableModel(dados, nomesColunas);
            JTable tabela = new JTable(modelo);
            JScrollPane scroll = new JScrollPane(tabela);

            JLabel totalLabel = new JLabel("Total de registros: " + dados.size());
            totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

            JPanel painel = new JPanel(new BorderLayout(10, 10));
            painel.add(scroll, BorderLayout.CENTER);
            painel.add(totalLabel, BorderLayout.SOUTH);

            JFrame frame = new JFrame(titulo);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setLocationRelativeTo(null);
            frame.add(painel);
            frame.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String capitalize(String texto) {
        if (texto == null || texto.isBlank()) return "";
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}