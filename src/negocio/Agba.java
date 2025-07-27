/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.ArrayList;

/**
 *
 * @author ezequiel
 */
public abstract class Agba<P, ID> extends AgbaAbstrato<P, ID>{
    
    public Agba(P pacientes, P medicos, P consultas) {
        super(pacientes, medicos, consultas);
        
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
    
    
}
