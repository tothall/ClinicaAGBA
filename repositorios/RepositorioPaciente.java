/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dados.Conexao;
import negocio.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import negocio.IdDuplicadoException;

/**
 *
 * @author ezequiel
 */
public class RepositorioPaciente implements IRepositorio<Paciente> {
    public RepositorioPaciente() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS paciente (
                nome TEXT NOT NULL,
                sobrenome TEXT NOT NULL,
                idade INTEGER,
                data_nascimento TEXT,
                genero TEXT,
                telefone TEXT,
                email TEXT,
                cpf TEXT PRIMARY KEY
            );
        """;

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Paciente p) throws IdDuplicadoException {
        if (buscar(p.getCpf()) != null) {
        throw new IdDuplicadoException("CPF já cadastrado: " + p.getCpf());
    }
        String sql = """
            INSERT INTO paciente (nome, sobrenome, idade, data_nascimento, genero, telefone, email, cpf)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setInt(3, p.getIdade());
            pstmt.setString(4, p.getData_nascimento().toString());
            pstmt.setString(5, p.getGenero());
            pstmt.setString(6, p.getTelefone());
            pstmt.setString(7, p.getEmail());
            pstmt.setString(8, p.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Paciente p) {
        String sql = """
            UPDATE paciente SET
                nome = ?, sobrenome = ?, idade = ?, data_nascimento = ?, genero = ?,
                telefone = ?, email = ?
            WHERE cpf = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setInt(3, p.getIdade());
            pstmt.setString(4, p.getData_nascimento().toString());
            pstmt.setString(5, p.getGenero());
            pstmt.setString(6, p.getTelefone());
            pstmt.setString(7, p.getEmail());
            pstmt.setString(8, p.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente buscar(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Paciente(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getInt("idade"),
                    LocalDate.parse(rs.getString("data_nascimento")),
                    rs.getString("genero"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getInt("idade"),
                    LocalDate.parse(rs.getString("data_nascimento")),
                    rs.getString("genero"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                pacientes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public void removerPacientePorCPF(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
