/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dados.Conexao;
import negocio.Paciente;

import java.sql.*;
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
                data_nascimento TEXT,
                genero TEXT,
                cpf TEXT PRIMARY KEY,
                email TEXT,
                telefone TEXT
                
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
            INSERT INTO paciente (nome, sobrenome, data_nascimento, genero, cpf, email, telefone)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setString(3, p.getData_nascimento());
            pstmt.setString(4, p.getGenero());
            pstmt.setString(5, p.getCpf());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(7, p.getTelefone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Paciente p) {
        String sql = """
            UPDATE paciente SET
                nome = ?, sobrenome = ?, data_nascimento = ?, genero = ?,
                email = ?, telefone = ?
            WHERE cpf = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setString(3, p.getData_nascimento());
            pstmt.setString(4, p.getGenero());
            pstmt.setString(5, p.getCpf());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(7, p.getTelefone());
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
                    rs.getString("data_nascimento"),
                    rs.getString("genero"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone")
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
                    rs.getString("data_nascimento"),
                    rs.getString("genero"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone")
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
