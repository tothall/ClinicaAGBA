/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dados.Conexao;
import gui.JFrameLogin;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequiel
 */
public class Agba  implements IAgba {
    public Agba() {
        iniciarSistema();
    }

    @Override
    public void iniciarSistema() throws UnsupportedOperationException {
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
        } catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
         
    }

    @Override
    public void finalizarSistema() throws UnsupportedOperationException {
        
        try {
            System.exit(0);
        } catch (UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
