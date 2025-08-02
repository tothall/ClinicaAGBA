/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dados.Conexao;
import dados.RepositorioConsulta;
import dados.RepositorioMedico;
import dados.RepositorioPaciente;
import excecoes.IdAusenteException;
import excecoes.IdDuplicadoException;
import excecoes.LoginIncorretoException;
import excecoes.PessoaOcupadoException;
import excecoes.SalaOcupadaException;
import gui.JFrameLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ezequiel
 */
public class Agba  implements IAgba {
    private static Agba instancia;
    
    private RepositorioConsulta repoConsulta;
    private RepositorioPaciente repoPaciente;
    private RepositorioMedico repoMedico;
    private Consulta c;
    private Medico m;
    private Paciente p;
    
    private Agba() {
        this.repoConsulta = new RepositorioConsulta();
        this.repoPaciente = new RepositorioPaciente();
        this.repoMedico = new RepositorioMedico();
        this.c = null;
        this.m = null;
        this.p = null;
        
    }
    
    public static Agba getInstancia() {
        if (instancia == null) {
            instancia = new Agba();
        }
        
        return instancia;
    }

    @Override
    public void iniciarSistema() throws UnsupportedOperationException {
        try {
            Connection conexao = Conexao.conectar();
            System.out.println("Conexão estabelecida com sucesso!");

            Conexao.verificarEstrutura(conexao);
            

            java.awt.EventQueue.invokeLater(() -> {
                new JFrameLogin().setVisible(true);
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println(new java.io.File("agba.db").getAbsolutePath());
            System.exit(1);
        } catch (UnsupportedOperationException e){
            JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
         
    }

    @Override
    public void finalizarSistema() throws UnsupportedOperationException {
        
        try {
            System.exit(0);
        } catch (UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //MÉTODOS USUÁRIO
    public void loginUsuario(String login, String senha) throws LoginIncorretoException {
        Usuario usuario = new Usuario(login, senha);
        usuario.Login(login, senha);
    }
    
    //MÉTODOS CRUD CONSULTA
    public Consulta criarConsulta (String codigo_consulta, String data_consulta, String id_paciente, String id_medico, String consultorio, String hora_consulta) {
        c = new Consulta(codigo_consulta, data_consulta, id_paciente, id_medico, consultorio, hora_consulta);
        return c;
    }
    
    public void adicionarConsulta (Consulta C) throws IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException, IdAusenteException {
        repoConsulta.adicionar(C);
    }
    
    public void atualizarConsulta (Consulta C) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoConsulta.atualizar(C);
    }
    
    public void removerConsulta (String codigo_consulta) {
        repoConsulta.remover(codigo_consulta);
  
    }
    
    public Consulta buscarConsulta (String codigo_consulta) {
        c = repoConsulta.buscar(codigo_consulta);
        return c;
        
    }
    
    public List<Consulta> listarConsultas () {
        
        List<Consulta> consultas = repoConsulta.listar();
        return consultas;
    }
    
    public Consulta buscarConsultaPorId (int id_consulta) {
        c = repoConsulta.buscarConsultaPorId(id_consulta);
        return c;
    }
    
    public boolean verificarConflitoSala(Consulta c) {
        boolean conflito = repoConsulta.verificarConflitoSala(c);
        return conflito;
    }
    
    public boolean verificarPessoaOcupada(String idMedico, String idPaciente, String data, String hora, int idConsultaAtual) {
        boolean ocupada = repoConsulta.verificarPessoaOcupada(idMedico, idPaciente, data, hora, idConsultaAtual);
        return ocupada;
    }
    
    //MÉTODOS CRUD PACIENTE
    
    public Paciente criarPaciente(String nome, String sobrenome, String data_nascimento, String genero, String telefone, String email, String cpf) {
        p = new Paciente(nome, sobrenome, data_nascimento, genero, telefone, email, cpf);
        return p;
    }
    
    public void adicionarPaciente(Paciente p) throws IdDuplicadoException, IdAusenteException {
        repoPaciente.adicionar(p);
    }
    
    public void atualizarPaciente (Paciente p) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoPaciente.atualizar(p);
    }
    
    public void removerPaciente (String cpf) {
        repoPaciente.remover(cpf);
    }
    
    public Paciente buscarPaciente (String cpf) {
        p = repoPaciente.buscar(cpf);
        return p;
    }
    
    public Paciente buscarPacientePorId (int id_paciente) {
        p = repoPaciente.buscarPorId(id_paciente);
        return p;
    }
    
    public List<Paciente> listarPacientes () {
        
        List<Paciente> pacientes = repoPaciente.listar();
        return pacientes;
    }
    
    //MÉTODOS CRUD MÉDICO
    
    public Medico criarMedico(String nome, String sobrenome, String data_nascimento, String genero, String crm, String especialidade, String email, String telefone) {
        m = new Medico(nome, sobrenome, data_nascimento, genero, crm, especialidade, email, telefone);
        return m;
    }
    
    public void adicionarMedico(Medico m) throws IdDuplicadoException, IdAusenteException {
        repoMedico.adicionar(m);
    }
    
    public void atualizarMedico (Medico m) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoMedico.atualizar(m);
    }
    
    public void removerMedico (String crm) {
        repoMedico.remover(crm);
    }
    
    public Medico buscarMedico (String crm) {
        m = repoMedico.buscar(crm);
        return m;
    }
    
    public Medico buscarMedicoPorId (int id_medico) {
        m = repoMedico.buscarPorId(id_medico);
        return m;
    }
    
    public List<Medico> listarMedicos () {
        
        List<Medico> medicos = repoMedico.listar();
        return medicos;
    }
    
    
    
    
    
    
    
    
    
    
    
}
