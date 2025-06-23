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
    protected String cpf;
    protected ArrayList<Consulta> consultas;
    
    public Paciente(String nome, String sobrenome, int idade, LocalDate data_nascimento, String genero, String telefone, String email, String cpf, ArrayList<Consulta> consultas) {
        super(nome, sobrenome, idade, data_nascimento, genero, telefone, email);
        this.cpf = cpf;
        this.consultas = consultas;
    }
    
    @Override
    public boolean Verificar_duplicata(String id) {
        return "id".equals(id);
    }
    
    @Override
    public String toString() {
        return  "\nNome: " + this.nome + this.sobrenome +
                "\nCPF: " + this.getCpf() +
                "\nIdade: " + this.idade +
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email +
                "\nConsultas: " + this.getConsultas();
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
     * @return the consultas
     */
    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    /**
     * @param consultas the consultas to set
     */
    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    
}
