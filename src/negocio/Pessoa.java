/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.time.LocalDate;

/**
 *
 * @author ezequiel
 */
public class Pessoa extends PessoaAbstrata<String> {
    
    public Pessoa(String nome, String sobrenome, String data_nascimento, String genero, String telefone, String email) {
        super(nome, sobrenome, data_nascimento, genero, telefone, email);
    }
    
    @Override
    public boolean Verificar_duplicata(String id) {
        return "id".equals(id);
    }
    
    @Override
    public String toString() {
        return  "\nNome: " + this.nome + this.sobrenome +
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email;
    }   
   
}

