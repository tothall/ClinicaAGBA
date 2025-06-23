/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.ArrayList;

/**
 *
 * @author ezequiel
 * @param <P>
 * @param <ID>
 */
public abstract class AgbaAbstrato<P, ID> implements IAgba<P, ID>{
    protected P pacientes;
    protected P medicos;
    protected P consultas;
    
    public AgbaAbstrato(P pacientes, P medicos, P consultas) {
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.consultas = consultas;
        
    }
    
    @Override
    public abstract void iniciarSistema();
    
    @Override
    public abstract P cadastrar(P pessoa);
    
    @Override
    public abstract P atualizar(ID id, P pessoaAtualizada);
    
    @Override
    public abstract P buscar(ID id);
    
    @Override
    public abstract P deletar(P pessoa);
    
    @Override
    public abstract void listar(ArrayList<P> pessoas);
    
    @Override
    public abstract void finalizarSistema();
    
    
    public void setPacientes(P pacientes) {
        this.pacientes = pacientes;
    }
    
    public P getPacientes() {
        return this.pacientes;
    }
    
    public void setMedicos(P medicos) {
        this.medicos = medicos;
    }
    
    public P getMedicos() {
        return this.medicos;
    }
    
    public void setConsultas(P consultas) {
        this.consultas = consultas;
    }
    
    public P getConsultas() {
        return this.consultas;
    }
    
}
