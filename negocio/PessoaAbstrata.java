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
public abstract class PessoaAbstrata<T> implements IVerificarPessoa<T> {
    protected String nome;
    protected String sobrenome;
    protected int idade;
    protected LocalDate data_nascimento;
    protected String genero;
    protected String telefone;
    protected String email;
    
    public PessoaAbstrata(String nome, String sobrenome, int idade, LocalDate data_nascimento, String genero, String telefone, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
    }
    
    @Override
    public boolean Verificar_duplicata(T id) {
        return "id".equals(id);
    }
    
    @Override
    public String toString() {
        return  "\nNome: " + this.nome + this.sobrenome +
                "\nIdade: " + this.idade +
                "\nNascimento: " + this.data_nascimento +
                "\nGênero: " + this.genero +
                "\nTelefone: " + this.telefone +
                "\nEmail " + this.email;
    }
            
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the data_nascimento
     */
    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
