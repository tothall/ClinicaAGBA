/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import excecoes.LoginIncorretoException;

/**
 *
 * @author ezequiel
 */
public interface IAutenticacao {
    
    void Login(String username, String senha) throws LoginIncorretoException;
    void EsqueciSenha(String username, String senha);
    void Logout(String username, String senha);
}
