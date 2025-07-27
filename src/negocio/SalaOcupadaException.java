/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ezequiel
 */
import javax.swing.JOptionPane;

public class SalaOcupadaException extends Exception {
    public SalaOcupadaException(String mensagem) {
        super(mensagem);
        JOptionPane.showMessageDialog(null, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}

