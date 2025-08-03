/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;
import dados.Conexao;
import negocio.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import negocio.IdAusenteException;
import negocio.IdDuplicadoException;
import negocio.PessoaOcupadoException;
import negocio.SalaOcupadaException;
import dados.RepositorioPaciente;
import dados.RepositorioMedico;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequiel
 */
public class RepositorioConsulta implements IRepositorio<Consulta> {

    public RepositorioConsulta() {
        criarTabela();
    }

    private void criarTabela() {
        String pragmaFK = "PRAGMA foreign_keys = ON;";
        
        String sql = """
            CREATE TABLE IF NOT EXISTS consulta (
                id_consulta INTEGER PRIMARY KEY AUTOINCREMENT,
                codigo_consulta TEXT UNIQUE NOT NULL,
                data_consulta TEXT NOT NULL,
                id_paciente TEXT NOT NULL,
                id_medico TEXT NOT NULL,
                consultorio INTEGER NOT NULL,
                hora_consulta TEXT NOT NULL,
                FOREIGN KEY (id_medico) REFERENCES medico(crm),
                FOREIGN KEY (id_paciente) REFERENCES paciente(cpf)
            );
        """;

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(pragmaFK);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Consulta c) throws IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException, IdAusenteException {
        RepositorioMedico medicos = new RepositorioMedico();
        RepositorioPaciente pacientes = new RepositorioPaciente();
        
        if (c.getCodigo_consulta() == null || c.getCodigo_consulta().trim().isEmpty()) {
            throw new IdAusenteException("O código da consulta não pode ser nulo ou vazio.");
        } else {
            if(medicos.buscar(c.getId_medico()) == null || c.getId_medico().trim().isEmpty()) {
                throw new IdAusenteException("O CRM do(a) médico(a) não pode ser nulo ou vazio.");
            } else {
                if(pacientes.buscar(c.getId_paciente()) == null || c.getId_paciente().trim().isEmpty()) {
                    throw new IdAusenteException("O CPF do(a) paciente não pode ser nulo ou vazio.");
                }
            }
        }

        if (buscar(c.getCodigo_consulta()) != null) {
            throw new IdDuplicadoException("Código de consulta duplicado: " + c.getCodigo_consulta());
        }

        if(verificarConflitoSala(c)){
            throw new SalaOcupadaException("Conflito no uso da sala: " + c.getConsultorio());
        }
        if (verificarPessoaOcupada(c.getId_medico(), c.getId_paciente(), c.getData_consulta(), c.getHora_consulta(), c.getId_consulta())) {
        throw new PessoaOcupadoException("Médico(a) ou paciente possui consulta agendada neste horário");
        }
        String sqlInsert = """
            INSERT INTO consulta (codigo_consulta, data_consulta, id_paciente, id_medico, consultorio, hora_consulta)
            VALUES (?, ?, ?, ?, ?, ?);
        """;


    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        pstmt.setString(1, c.getCodigo_consulta());
        pstmt.setString(2, c.getData_consulta());
        pstmt.setString(3, c.getId_paciente());
        pstmt.setString(4, c.getId_medico());
        pstmt.setString(5, c.getConsultorio());
        pstmt.setString(6, c.getHora_consulta());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int novoId = rs.getInt(1);
            c.setId_consulta(novoId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    /**
     *
     * @param c
     */
  @Override
public void atualizar(Consulta c) throws IdAusenteException, IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
    Consulta dados_previos = buscarConsultaPorId(c.getId_consulta());
    RepositorioMedico medicos = new RepositorioMedico();
    RepositorioPaciente pacientes = new RepositorioPaciente();

    if (c.getCodigo_consulta() == null || c.getCodigo_consulta().trim().isEmpty()) {
        throw new IdAusenteException("O código da consulta não pode ser nulo ou vazio.");
    }

    if (medicos.buscar(c.getId_medico()) == null || c.getId_medico().trim().isEmpty()) {
        throw new IdAusenteException("O CRM do(a) médico(a) não pode ser nulo ou vazio.");
    }

    if (pacientes.buscar(c.getId_paciente()) == null || c.getId_paciente().trim().isEmpty()) {
        throw new IdAusenteException("O CPF do(a) paciente não pode ser nulo ou vazio.");
    }

    // Verifica duplicidade de código de consulta
    Consulta consultaComMesmoCodigo = buscar(c.getCodigo_consulta());
    if (consultaComMesmoCodigo != null && consultaComMesmoCodigo.getId_consulta() != c.getId_consulta()) {
        throw new IdDuplicadoException("Código de consulta duplicado: " + c.getCodigo_consulta());
    }

    if (verificarConflitoSala(c)) {
        throw new SalaOcupadaException("Conflito no uso da sala " + c.getConsultorio());
    }

    if (verificarPessoaOcupada(c.getId_medico(), c.getId_paciente(), c.getData_consulta(), c.getHora_consulta(), c.getId_consulta())) {
        throw new PessoaOcupadoException("Médico(a) ou paciente possui consulta agendada neste horário");
    }

    String sql = """
        UPDATE consulta SET
            codigo_consulta = ?, data_consulta = ?, id_paciente = ?, id_medico = ?, consultorio = ?, hora_consulta = ?
        WHERE id_consulta = ?;
    """;

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, c.getCodigo_consulta());
        pstmt.setString(2, c.getData_consulta());
        pstmt.setString(3, c.getId_paciente());
        pstmt.setString(4, c.getId_medico());
        pstmt.setString(5, c.getConsultorio());
        pstmt.setString(6, c.getHora_consulta());
        pstmt.setInt(7, c.getId_consulta());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public void remover(String codigo_consulta) {
        String sql = "DELETE FROM consulta WHERE codigo_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo_consulta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consulta buscar(String codigo_consulta) {
        String sql = "SELECT * FROM consulta WHERE codigo_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo_consulta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Consulta(
                    rs.getInt("id_consulta"),
                    rs.getString("codigo_consulta"),
                    rs.getString("data_consulta"),
                    rs.getString("id_paciente"),
                    rs.getString("id_medico"),
                    rs.getString("consultorio"),
                    rs.getString("hora_consulta")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Consulta c = new Consulta(
                    rs.getInt("id_consulta"),
                    rs.getString("codigo_consulta"),
                    rs.getString("data_consulta"),
                    rs.getString("id_paciente"),
                    rs.getString("id_medico"),
                    rs.getString("consultorio"),
                    rs.getString("hora_consulta")
                );
                consultas.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;
    }

    public Consulta buscarConsultaPorId(int id_consulta) {
        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_consulta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Consulta(
                    rs.getString("codigo_consulta"),
                    rs.getString("data_consulta"),
                    rs.getString("id_paciente"),
                    rs.getString("id_medico"),
                    rs.getString("consultorio"),
                    rs.getString("hora_consulta")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Consulta> listarPorMedico(String crmMedico) {
    List<Consulta> consultas = new ArrayList<>();
    String sql = "SELECT * FROM consulta WHERE id_medico = ?";

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, crmMedico);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Consulta c = new Consulta(
                rs.getString("id_consulta"),
                rs.getString("data_consulta"),
                rs.getString("id_paciente"),
                rs.getString("id_medico"),
                rs.getString("consultorio"),
                rs.getString("hora_consulta")
            );
            consultas.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return consultas;
}

public List<Consulta> listarPorPaciente(String cpfPaciente) {
    List<Consulta> consultas = new ArrayList<>();
    String sql = "SELECT * FROM consulta WHERE id_paciente = ?";

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, cpfPaciente);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Consulta c = new Consulta(
                rs.getString("id_consulta"),
                rs.getString("data_consulta"),
                rs.getString("id_paciente"),
                rs.getString("id_medico"),
                rs.getString("consultorio"),
                rs.getString("hora_consulta")
            );
            consultas.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return consultas;
}

public boolean verificarConflitoSala(Consulta c) {
    String sql = """
        SELECT 1 FROM consulta 
        WHERE data_consulta = ? AND hora_consulta = ? AND consultorio = ? AND id_consulta != ?;
    """;
    boolean conflito = false;

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, c.getData_consulta());
        stmt.setString(2, c.getHora_consulta());
        stmt.setString(3, c.getConsultorio());
        stmt.setInt(4, c.getId_consulta()); // FALTAVA AQUI

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            conflito = true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return conflito;
}


public boolean verificarPessoaOcupada(String idMedico, String idPaciente, String data, String hora, int idConsultaAtual) {
    boolean ocupada = false;
    String sql = """
        SELECT COUNT(*) 
        FROM consulta 
        WHERE (id_medico = ? OR id_paciente = ?) 
          AND data_consulta = ? 
          AND hora_consulta = ?
          AND id_consulta != ?;
    """;

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idMedico);
        pstmt.setString(2, idPaciente);
        pstmt.setString(3, data);
        pstmt.setString(4, hora);
        pstmt.setInt(5, idConsultaAtual); // FALTAVA AQUI

        ResultSet rs = pstmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            ocupada = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ocupada;
}



}