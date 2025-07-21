/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import negocio.*;


/**
 *
 * @author ezequiel
 */
public class Teste {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int op = 1;
        
        //TESTE DE LOGIN DO ADMINISTRADOR
        Usuario user = new Usuario("admin", "123", true, "admin@email.com");
        while(op == 1){
            System.out.println("AGBA CLÍNICA MÉDICA\n--------------\n");
            System.out.println("LOGIN: ");
            String login = input.nextLine();
            System.out.println("SENHA: ");
            String senha = input.nextLine();
 
            user.Login(login, senha, true);    
        }
        
        //TESTE CRUD MÉDICO
        //cadastrar
        String nome = "Robson";
        String sobrenome = "Clebson";
        int idade = 55;
        LocalDate data_nascimento = LocalDate.of(1970, Month.JANUARY, 23) ;
        String genero = "Masculino";
        String telefone = "(81)99999-9999";
        String email = "doutordogera@email.com";
        String crm = "4543-66";
        String especialidade = "Gastroenterologia";
        ArrayList<Consulta> consulta = null;
        Medico novoMedico = new Medico(nome, sobrenome, idade, data_nascimento, genero, telefone, email, crm, especialidade, consulta);
        
        novoMedico.toString();
        
        ArrayList<Medico> medicos = new ArrayList<>();
        medicos.add(novoMedico);
        
        novoMedico = new Medico("Carlo", "Ancelotti", 33, LocalDate.of(1992, Month.MARCH, 11), "Masculino", "(81)9999-8765", "carlao@email.com", "1234-56", "Cardiologia", null);
        medicos.add(novoMedico);
        
        
        
        //TESTE CRUD PACIENTE
        //cadastrar
        nome = "Edgleibson";
        sobrenome = "Hudson";
        idade = 25;
        data_nascimento = LocalDate.of(2000, Month.MAY, 11) ;
        genero = "Masculino";
        telefone = "(81)9876-9999";
        email = "edhud@email.com";
        String cpf = "027.467.153-65";
        consulta = null;
        Paciente novoRegistro = new Paciente(nome, sobrenome, idade, data_nascimento, genero, telefone, email, cpf, consulta);
        
        novoRegistro.toString();
        
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(novoRegistro);
        
        novoRegistro = new Paciente("Esmirgulina", "Virgulina", 45, LocalDate.of(1980, Month.SEPTEMBER, 11), "Feminina", "(81)9432-8765", "esmirgulinda@email.com", "012.536.784-77", null);
        pacientes.add(novoRegistro);
        
        
        
        //TESTE CRUD CONSULTA
        //cadastrar
        String id_consulta = "001";
        LocalDate data_consulta = LocalDate.of(2025, Month.JULY, 17);
        String id_paciente = "027.467.153-65";
        String id_medico = "4543-66";
        int consultorio = 1;
        LocalTime hora_consulta = LocalTime.of(8, 30);
        Consulta nova = new Consulta(id_consulta, data_consulta, id_paciente, id_medico, consultorio, hora_consulta);
        
        nova.toString();
        
        ArrayList<Consulta> consultas = new ArrayList<>();
        consultas.add(nova);
        
        nova = new Consulta("002", LocalDate.of(2025, Month.AUGUST, 17), "034.714.911-55", "1234-56", 2, LocalTime.of(14, 30));
        consultas.add(nova);
        input.close();
        
        
        
    }
}
