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
import excecoes.PessoaOcupadoException;
import excecoes.SalaOcupadaException;
import gui.JFrameLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequiel
 */
public class Agba  implements IAgba {
    private static Agba instancia;
    
    private RepositorioConsulta repoConsulta;
    private RepositorioPaciente repoPaciente;
    private RepositorioMedico repoMedico;
    
    private Agba() {
        this.repoConsulta = new RepositorioConsulta();
        this.repoPaciente = new RepositorioPaciente();
        this.repoMedico = new RepositorioMedico();
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
            Conexao.inicializarTabelas();

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
    
    //MÉTODOS CRUD CONSULTA
    
    public void adicionarConsulta (Consulta C) throws IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException, IdAusenteException {
        repoConsulta.adicionar(C);
    }
    
    public void atualizarConsulta (Consulta C) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoConsulta.atualizar(C);
    }
    
    public void removerConsulta (String codigo_consulta) {
        repoConsulta.remover(codigo_consulta);
    }
    
    public void buscarConsulta (String codigo_consulta) {
        repoConsulta.buscar(codigo_consulta);
    }
    
    public List<Consulta> listarConsultas () {
        
        List<Consulta> consultas = repoConsulta.listar();
        return consultas;
    }
    
    public Consulta buscarConsultaPorId (int id_consulta) {
        Consulta c = repoConsulta.buscarConsultaPorId(id_consulta);
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
    
    public void adicionarPaciente(Paciente p) throws IdDuplicadoException, IdAusenteException {
        repoPaciente.adicionar(p);
    }
    
    public void atualizarPaciente (Paciente p) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoPaciente.atualizar(p);
    }
    
    public void removerPaciente (String cpf) {
        repoPaciente.remover(cpf);
    }
    
    public void buscarPaciente (String cpf) {
        repoPaciente.buscar(cpf);
    }
    
    public List<Paciente> listarPacientes () {
        
        List<Paciente> pacientes = repoPaciente.listar();
        return pacientes;
    }
    
    //MÉTODOS CRUD MÉDICO
    
    public void adicionarMedico(Medico m) throws IdDuplicadoException, IdAusenteException {
        repoMedico.adicionar(m);
    }
    
    public void atualizarMedico (Medico m) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        repoMedico.atualizar(m);
    }
    
    public void removerMedico (String crm) {
        repoMedico.remover(crm);
    }
    
    public void buscarMedico (String crm) {
        repoMedico.buscar(crm);
    }
    
    public List<Medico> listarMedicos () {
        
        List<Medico> medicos = repoMedico.listar();
        return medicos;
    }
    
    
    
    
    
    
    
    
    
    
    
}
