/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dados.Conexao;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import negocio.IdDuplicadoException;
import negocio.PessoaOcupadoException;
import negocio.SalaOcupadaException;

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
                id_consulta TEXT PRIMARY KEY,
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
    public void adicionar(Consulta c) throws IdDuplicadoException, SalaOcupadaException, PessoaOcupadoException {
        verificarConflitoSala(c);
        verificarPessoaOcupada(c.getId_medico(), c.getId_paciente(), c.getData_consulta(), c.getHora_consulta());
        String sqlInsert = """
            INSERT INTO consulta (id_consulta, data_consulta, id_paciente, id_medico, consultorio, hora_consulta)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        String sql = """
        INSERT INTO consulta (id_consulta, data_consulta, hora_consulta, id_paciente, id_medico, consultorio)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        pstmt.setString(1, c.getId_consulta());
        pstmt.setString(2, c.getData_consulta());
        pstmt.setString(3, c.getHora_consulta());
        pstmt.setString(4, c.getId_paciente());
        pstmt.setString(5, c.getId_medico());
        pstmt.setString(6, c.getConsultorio());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public void atualizar(Consulta c) {
        String sql = """
            UPDATE consulta SET
                data_consulta = ?, id_paciente = ?, id_medico = ?, consultorio = ?, hora_consulta = ?
            WHERE id_consulta = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getData_consulta());
            pstmt.setString(2, c.getId_paciente());
            pstmt.setString(3, c.getId_medico());
            pstmt.setString(4, c.getConsultorio());
            pstmt.setString(5, c.getHora_consulta());
            pstmt.setString(6, c.getId_consulta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(String id_consulta) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id_consulta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consulta buscar(String id_consulta) {
        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id_consulta);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Consulta(
                    rs.getString("id_consulta"),
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

    public void removerConsultaPorId(String id_consulta) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id_consulta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

public void verificarConflitoSala(Consulta c) throws SalaOcupadaException {
    String sql = """
        SELECT 1 FROM consulta 
        WHERE data_consulta = ? AND hora_consulta = ? AND consultorio = ?;
    """;

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, c.getData_consulta().toString());
        stmt.setString(2, c.getHora_consulta().toString());
        stmt.setString(3, c.getConsultorio());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            throw new SalaOcupadaException("Já existe uma consulta agendada para essa sala, data e hora.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean verificarPessoaOcupada(String idMedico, String idPaciente, String data, String hora) throws PessoaOcupadoException {
    String sql = """
        SELECT COUNT(*) 
        FROM consulta 
        WHERE (id_medico = ? OR id_paciente = ?) 
          AND data_consulta = ? 
          AND hora_consulta = ?
    """;

    try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idMedico);
        pstmt.setString(2, idPaciente);
        pstmt.setString(3, data);
        pstmt.setString(4, hora);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            throw new PessoaOcupadoException("Médico ou paciente já possuem consulta neste horário.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}


}