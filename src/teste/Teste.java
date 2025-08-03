/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import excecoes.LoginIncorretoException;
import java.util.ArrayList;
import java.util.Scanner;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;
import negocio.Usuario;

/**
 *
 * @author ezequiel
 */
public class Teste {
    
    public static void main(String[] args) throws LoginIncorretoException{
        Scanner input = new Scanner(System.in);
        int op = 1;
        
        //TESTE DE LOGIN DO ADMINISTRADOR
        Usuario user = new Usuario("admin", "123");
        while(op == 1){
            System.out.println("AGBA CLÍNICA MÉDICA\n--------------\n");
            System.out.println("LOGIN: ");
            String login = input.nextLine();
            System.out.println("SENHA: ");
            String senha = input.nextLine();
 
            user.Login(login, senha);    
        }
        
        //TESTE CRUD MÉDICO
        //cadastrar
        String nome = "Robson";
        String sobrenome = "Clebson";
        int idade = 55;
        String data_nascimento = "11/11/1969" ;
        String genero = "Masculino";
        String telefone = "(81)99999-9999";
        String email = "doutordogera@email.com";
        String crm = "4543-66";
        String especialidade = "Gastroenterologia";
        
        Medico novoMedico = new Medico(nome, sobrenome, data_nascimento, genero, crm, especialidade, email, telefone);
        
        novoMedico.toString();
        
        ArrayList<Medico> medicos = new ArrayList<>();
        medicos.add(novoMedico);
        
        novoMedico = new Medico("Carlo", "Ancelotti", "11/03/1992", "Masculino", "1234-56", "Cardiologia", "carlao@email.com", "(81)9999-9765");
        medicos.add(novoMedico);
        
        
        
        //TESTE CRUD PACIENTE
        //cadastrar
        nome = "Edgleibson";
        sobrenome = "Hudson";
        idade = 25;
        data_nascimento = "11/05/2000";
        genero = "Masculino";
        telefone = "(81)9876-9999";
        email = "edhud@email.com";
        String cpf = "027.467.153-65";
        Paciente novoRegistro = new Paciente(nome, sobrenome, data_nascimento, genero, telefone, email, cpf);
        
        novoRegistro.toString();
        
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(novoRegistro);
        
        novoRegistro = new Paciente("Esmirgulina", "Virgulina", "11/09/2001", "Feminina", "(81)9432-8765", "esmirgulinda@email.com", "012.536.784-77");
        pacientes.add(novoRegistro);
        
        
        
        //TESTE CRUD CONSULTA
        //cadastrar
        String id_consulta = "001";
        String data_consulta = "17/07/2025";
        String id_paciente = "027.467.153-65";
        String id_medico = "4543-66";
        String consultorio = "1";
        String hora_consulta = "08:30";
        Consulta nova = new Consulta(id_consulta, data_consulta, id_paciente, id_medico, consultorio, hora_consulta);
        
        nova.toString();
        
        ArrayList<Consulta> consultas = new ArrayList<>();
        consultas.add(nova);
        
        nova = new Consulta("002", "11/05/2025", "034.714.911-55", "1234-56", "2", "14:30");
        consultas.add(nova);
        
        
        
        
    }
}