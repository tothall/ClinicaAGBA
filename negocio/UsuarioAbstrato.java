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
    protected Boolean isAdmin;
    
    public UsuarioAbstrato(String username, String senha, Boolean isAdmin) {
        this.username = username;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }
    
    @Override
    public void Login(String username, String senha, boolean isAdmin) throws LoginIncorretoException {
        if(!(this.isAdmin == true && "admin".equals(this.username) && "123".equals(this.senha))){
            throw new LoginIncorretoException("Login ou senha incorretos!");
        } else {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    @Override
    public void EsqueciSenha(String username, String senha) {
        
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

    /**
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
