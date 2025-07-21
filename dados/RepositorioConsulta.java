/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;
import java.util.ArrayList;
import negocio.*;


public class RepositorioConsulta implements IRepositorio<Consulta>{
    private ArrayList<Consulta> consultas;

    
    
    public RepositorioConsulta() {
        this.consultas = new ArrayList<Consulta>();
    }

    @Override
    public void adicionar(Consulta c){
        consultas.add(c);
    }

    @Override
    public void remover(Consulta c){
        int index = consultas.indexOf(c);
        consultas.remove(index);
    }

    @Override
    public void atualizar(Consulta consulta_antiga, Consulta consulta_atualizada){
        int index = consultas.indexOf(consulta_antiga);
        consultas.set(index, consulta_atualizada);
    }
    
    @Override
    public Consulta buscar(Consulta c){
        int index = consultas.indexOf(c);
        return consultas.get(index);
    }

    @Override
    public ArrayList<Consulta> listar(){
        return consultas;
    }

    public ArrayList<Consulta> getMedicos() {
        return consultas;
    }

    public void setMedicos(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
}

