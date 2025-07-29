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
     * @param nomeTabela
     */
    public static DefaultTableModel listar(String nomeTabela) {
    String sql = "SELECT * FROM " + nomeTabela;

    try (Connection conexao = Conexao.conectar();
         Statement stmt = conexao.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

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

        DefaultTableModel modelo = new DefaultTableModel(dados, nomesColunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return modelo;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}
    
    public static DefaultTableModel filtrarID(String nomeTabela, String id) {
        String busca = "";
    
    switch (nomeTabela) {
        case "medico":
            busca = "SELECT * FROM " + nomeTabela + " WHERE crm = ?";
            break;
        case "paciente":
            busca = "SELECT * FROM " + nomeTabela + " WHERE cpf = ?";
            break;
        case "consulta":
            busca = "SELECT * FROM " + nomeTabela + " WHERE id_consulta = ?";
            break;
            
    }
    

    try (Connection conexao = Conexao.conectar();
         PreparedStatement pstmt = conexao.prepareStatement(busca)) {

        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

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

        DefaultTableModel modelo = new DefaultTableModel(dados, nomesColunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return modelo;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro na busca: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}


}