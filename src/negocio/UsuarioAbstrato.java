/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequiel
 */
public abstract class UsuarioAbstrato implements IAutenticacao {
    protected String username;
    protected String senha;
    
    public UsuarioAbstrato(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }
    
    @Override
    public void Login(String username, String senha) throws LoginIncorretoException {
        if(!("admin".equals(this.username) && "123".equals(this.senha))){
            throw new LoginIncorretoException("Login ou senha incorretos!");
        }
        
    }
    
    @Override
    public void EsqueciSenha(String username, String senha) {
        JOptionPane.showMessageDialog(null, "USUÁRIO: admin\nSENHA: 123", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void Logout(String username, String senha){
        
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
