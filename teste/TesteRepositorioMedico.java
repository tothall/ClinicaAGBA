package teste;
import java.util.ArrayList;
import dados.*;
import negocio.*;
public class TesteRepositorioMedico {
    public static void main(String[] args) {
        Medico med1 = new Medico("123");
        Medico med2 = new Medico("124");
        Medico med3 = new Medico("125");

        RepositorioMedico repositorio_medico = new RepositorioMedico();
        repositorio_medico.adicionar(med1);
        repositorio_medico.adicionar(med2);
        repositorio_medico.adicionar(med3);
        
        repositorio_medico.remover(med3);

        for (Medico medico: repositorio_medico.getMedicos()){
            System.out.println(medico);
        }
        
        ArrayList<Medico> medicos = repositorio_medico.listar();
        for (Medico medico: medicos){
            System.out.println(medico);
        }
        
        System.out.println(repositorio_medico.buscar(med1));
        repositorio_medico.atualizar(med1, med2);
        
        for (Medico medico: medicos){
            System.out.println(medico);
        }
}
}
