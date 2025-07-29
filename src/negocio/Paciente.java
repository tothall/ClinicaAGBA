/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ezequiel
 */
public class Paciente extends Pessoa {
    protected int id;
    protected String cpf;
    
    public Paciente(int id, String nome, String sobrenome, String data_nascimento, String genero, String telefone, String email, String cpf) {
        super(nome, sobrenome, data_nascimento, genero, telefone, email);
        this.id = id;
        this.cpf = cpf;
    }
    
    public Paciente(String nome, String sobrenome, String data_nascimento, String genero, String telefone, String email, String cpf) {
        super(nome, sobrenome, data_nascimento, genero, telefone, email);
        this.cpf = cpf;
    }
    
    @Override
    public boolean Verificar_duplicata(String id) {
        return "id".equals(id);
    }
    
    @Override
    public String toString() {
        return  "\nNome: " + this.nome + this.sobrenome +
                "\nCPF: " + this.getCpf() +
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
