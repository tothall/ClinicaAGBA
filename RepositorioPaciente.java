/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booktour;
import java.util.ArrayList;

public class RepositorioPaciente implements IRepositorio<Paciente>{
    private ArrayList<Paciente> pacientes;

    
    
    public RepositorioPaciente() {
        this.pacientes = new ArrayList<Paciente>();
    }

    @Override
    public void adicionar(Paciente p){
        pacientes.add(p);
    }

    @Override
    public void remover(Paciente p){
        int index = pacientes.indexOf(p);
        pacientes.remove(index);
    }

    @Override
    public void atualizar(Paciente paciente_antigo, Paciente paciente_atualizado){
        int index = pacientes.indexOf(p);
        pacientes.set(index, paciente_atualizado);
    }
    
    @Override
    public Paciente buscar(Paciente p){
        int index = pacientes.indexOf(p);
        return pacientes.get(index);
    }

    @Override
    public ArrayList<Paciente> listar(){
        return pacientes;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}

