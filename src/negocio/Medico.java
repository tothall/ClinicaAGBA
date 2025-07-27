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
public class Medico extends Pessoa {
    protected String crm;
    protected String especialidade;
    protected ArrayList<Consulta> consultas;
    
    public Medico(String nome, String sobrenome, int idade, LocalDate data_nascimento, String genero, String telefone, String email, String crm, String especialidade) {
        super(nome, sobrenome, idade, data_nascimento, genero, telefone, email);
        this.crm = crm;
        this.especialidade = especialidade;
        this.consultas = new ArrayList<>();
    }
    
    @Override
    public boolean Verificar_duplicata(String id) {
        return "id".equals(id);
    }
    
    @Override
    public String toString() {
        return  "\nNome: Dr." + this.nome + this.sobrenome +
                "\nCRM: " + this.getCrm() +
                "\nEspecialidade: " + this.getEspecialidade() +
                "\nIdade: " + this.idade +
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email +
                "\nConsultas: " + this.getConsultas();
    }

    /**
     * @return the crm
     */
    public String getCrm() {
        return crm;
    }

    /**
     * @param crm the crm to set
     */
    public void setCrm(String crm) {
        this.crm = crm;
    }

    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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
