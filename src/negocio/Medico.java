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
    protected int id;
    protected String crm;
    protected String especialidade;
    
    public Medico(int id, String nome, String sobrenome, String data_nascimento, String genero, String crm, String especialidade, String email, String telefone) {
        super(nome, sobrenome, data_nascimento, genero, telefone, email);
        this.id = id;
        this.crm = crm;
        this.especialidade = especialidade;
    }
    
    public Medico(String nome, String sobrenome, String data_nascimento, String genero, String crm, String especialidade, String email, String telefone) {
        super(nome, sobrenome, data_nascimento, genero, telefone, email);
        this.crm = crm;
        this.especialidade = especialidade;
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
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email;
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

    public void setId(int idGerado) {
        this.id = idGerado;
    }
    
    public int getId() {
        return id;
    }

    
}
