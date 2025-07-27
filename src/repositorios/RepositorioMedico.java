/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import dados.Conexao;
import negocio.Medico;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import negocio.IdDuplicadoException;

/**
 *
 * @author ezequiel
 */


public class RepositorioMedico implements IRepositorio<Medico> {

    public RepositorioMedico() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS medico (
                nome TEXT NOT NULL,
                sobrenome TEXT NOT NULL,
                idade INTEGER,
                data_nascimento TEXT,
                genero TEXT,
                telefone TEXT,
                email TEXT,
                crm TEXT PRIMARY KEY,
                especialidade TEXT
            );
        """;

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Medico m) throws IdDuplicadoException {
        if (buscar(m.getCrm()) != null) {
        throw new IdDuplicadoException("CRM já cadastrado: " + m.getCrm());
    }
        String sql = """
            INSERT INTO medico (nome, sobrenome, idade, data_nascimento, genero, telefone, email, crm, especialidade)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setString(2, m.getSobrenome());
            pstmt.setInt(3, m.getIdade());
            pstmt.setString(4, m.getData_nascimento().toString());
            pstmt.setString(5, m.getGenero());
            pstmt.setString(6, m.getTelefone());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getCrm());
            pstmt.setString(9, m.getEspecialidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Medico m) {
        String sql = """
            UPDATE medico SET
                nome = ?, sobrenome = ?, idade = ?, data_nascimento = ?, genero = ?,
                telefone = ?, email = ?, especialidade = ?
            WHERE crm = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setString(2, m.getSobrenome());
            pstmt.setInt(3, m.getIdade());
            pstmt.setString(4, m.getData_nascimento().toString());
            pstmt.setString(5, m.getGenero());
            pstmt.setString(6, m.getTelefone());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getEspecialidade());
            pstmt.setString(9, m.getCrm()); // Onde crm = ?
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(String crm) {
        String sql = "DELETE FROM medico WHERE crm = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, crm);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medico buscar(String crm) {
        String sql = "SELECT * FROM medico WHERE crm = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, crm);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Medico(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getInt("idade"),
                    LocalDate.parse(rs.getString("data_nascimento")),
                    rs.getString("genero"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("crm"),
                    rs.getString("especialidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Medico> listar() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medico m = new Medico(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getInt("idade"),
                    LocalDate.parse(rs.getString("data_nascimento")),
                    rs.getString("genero"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("crm"),
                    rs.getString("especialidade")
                );
                medicos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }

    public void removerMedicoPorCRM(String crm) {
        String sql = "DELETE FROM medico WHERE crm = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, crm);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

