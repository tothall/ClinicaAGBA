/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booktour;
import java.util.ArrayList;

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
        int index = consultas.indexOf(c);
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

    public void setMedicos(ArrayList<Conulta> consultas) {
        this.consultas = consultas;
    }
}

