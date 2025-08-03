/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excecoes;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequiel
 */
public class PessoaOcupadoException extends Exception {
    public PessoaOcupadoException(String mensagem) {
        super(mensagem);
        JOptionPane.showMessageDialog(null, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}
