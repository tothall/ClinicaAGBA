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
public class LoginIncorretoException extends Exception {
    public LoginIncorretoException(String mensagem){
        super(mensagem);
        JOptionPane.showMessageDialog(null, mensagem, "Erro de Login", JOptionPane.ERROR_MESSAGE);
    }
}
