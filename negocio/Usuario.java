/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author ezequiel
 */
public class Usuario extends UsuarioAbstrato {
    protected String email;
    
    public Usuario(String username, String senha, boolean isAdmin, String email) {
        super(username, senha, isAdmin);
        this.email = email;      
    }
    
    

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
