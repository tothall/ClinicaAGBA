/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;
import dados.Conexao;
import negocio.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import excecoes.IdAusenteException;
import excecoes.IdDuplicadoException;

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
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                sobrenome TEXT NOT NULL,
                data_nascimento TEXT,
                genero TEXT,
                telefone TEXT,
                email TEXT,
                cpf TEXT UNIQUE NOT NULL
                
                
                
            );
        """;

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Paciente p) throws IdDuplicadoException, IdAusenteException {
        if (buscar(p.getCpf()) != null) {
            throw new IdDuplicadoException("CPF já cadastrado: " + p.getCpf());
        }
        if (p.getCpf() == null || p.getCpf().trim().isEmpty()) {
            throw new IdAusenteException("CPF não pode ser nulo ou vazio.");
        }
        String sql = """
            INSERT INTO paciente (nome, sobrenome, data_nascimento, genero, telefone, email, cpf)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setString(3, p.getData_nascimento());
            pstmt.setString(4, p.getGenero());
            pstmt.setString(5, p.getTelefone());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(7, p.getCpf());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
               if (rs.next()) {
                   int idGerado = rs.getInt(1);
                   p.setId(idGerado);
                   System.out.println("ID gerado: " + idGerado);
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Paciente p) throws IdDuplicadoException, IdAusenteException {
        if (buscar(p.getCpf()) != null && buscar(p.getCpf()).getId() == p.getId()) {
            
        } else {
            throw new IdDuplicadoException("CPF já cadastrado: " + p.getCpf());
        }
        if (p.getCpf() == null || p.getCpf().trim().isEmpty()) {
            throw new IdAusenteException("CPF não pode ser nulo ou vazio.");
        }
        String sql = """
            UPDATE paciente SET
                nome = ?, sobrenome = ?, data_nascimento = ?, genero = ?,
                telefone = ?, email = ?, cpf = ?
            WHERE id = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getSobrenome());
            pstmt.setString(3, p.getData_nascimento());
            pstmt.setString(4, p.getGenero());
            pstmt.setString(7, p.getCpf());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(5, p.getTelefone());
            pstmt.setInt(8, p.getId());
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
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("data_nascimento"),
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
    
    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Paciente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("data_nascimento"),
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
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("data_nascimento"),
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
