/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booktour;
import java.util.ArrayList;

public class RepositorioMedico implements IRepositorio<Medico>{
    private ArrayList<Medico> medicos;

    
    
    public RepositorioMedico() {
        this.medicos = new ArrayList<Medico>();
    }

    @Override
    public void adicionar(Medico m){
        medicos.add(m);
    }

    @Override
    public void remover(Medico m){
        int index = medicos.indexOf(m);
        medicos.remove(index);
    }

    @Override
    public void atualizar(Medico medico_antigo, Medico medico_atualizado){
        int index = medicos.indexOf(medico_antigo);
        medicos.set(index, medico_atualizado);
    }
    
    @Override
    public Medico buscar(Medico m){
        int index = medicos.indexOf(m);
        return medicos.get(index);
    }

    @Override
    public ArrayList<Medico> listar(){
        return medicos;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos) {
        this.medicos = medicos;
    }
}

