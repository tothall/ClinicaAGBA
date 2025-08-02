/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

import dados.Conexao;
import negocio.Medico;

import java.sql.*;
import java.time.LocalDate;
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


public class RepositorioMedico implements IRepositorio<Medico> {

    public RepositorioMedico() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS medico (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                sobrenome TEXT NOT NULL,
                data_nascimento TEXT,
                genero TEXT,
                crm TEXT UNIQUE NOT NULL,
                especialidade TEXT,
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
    public void adicionar(Medico m) throws IdDuplicadoException, IdAusenteException {
        if (m.getCrm() == null || m.getCrm().trim().isEmpty()) {
            throw new IdAusenteException("CRM não pode ser nulo ou vazio.");
        }
        if (buscar(m.getCrm()) != null) {
        throw new IdDuplicadoException("CRM já cadastrado: " + m.getCrm());
        }
        String sql = """
            INSERT INTO medico (nome, sobrenome, data_nascimento, genero, crm, especialidade, email, telefone)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

           pstmt.setString(1, m.getNome());
           pstmt.setString(2, m.getSobrenome());
           pstmt.setString(3, m.getData_nascimento());
           pstmt.setString(4, m.getGenero());
           pstmt.setString(5, m.getCrm());
           pstmt.setString(6, m.getEspecialidade());
           pstmt.setString(7, m.getEmail());
           pstmt.setString(8, m.getTelefone());

           pstmt.executeUpdate();


           try (ResultSet rs = pstmt.getGeneratedKeys()) {
               if (rs.next()) {
                   int idGerado = rs.getInt(1);
                   m.setId(idGerado);
                   System.out.println("ID gerado: " + idGerado);
               }
    }

} catch (SQLException e) {
    e.printStackTrace();
}

    }

    /**
     *
     * @param m
     * @throws excecoes.IdAusenteException
     * @throws excecoes.IdDuplicadoException
     */
    @Override
    public void atualizar(Medico m) throws IdAusenteException, IdDuplicadoException {
        Medico mPrevio = m;
        if (m.getCrm() == null || m.getCrm().trim().isEmpty()) {
                throw new IdAusenteException("CRM não pode ser nulo ou vazio.");
            }
        
        if (buscar(m.getCrm()) != null && mPrevio.getCrm() == m.getCrm()) {
                
        } else {
            throw new IdDuplicadoException("CRM já cadastrado: " + m.getCrm()); 
        }
        String sql = """
            UPDATE medico SET
                nome = ?, sobrenome = ?, data_nascimento = ?, genero = ?,
                crm = ?, especialidade = ?, email = ?, telefone = ?
            WHERE id = ?;
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setString(2, m.getSobrenome());
            pstmt.setString(3, m.getData_nascimento());
            pstmt.setString(4, m.getGenero());
            pstmt.setString(5, m.getCrm());
            pstmt.setString(6, m.getEspecialidade());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getTelefone());
            pstmt.setInt(9, m.getId());
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
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("data_nascimento"),
                    rs.getString("genero"),
                    rs.getString("crm"),
                    rs.getString("especialidade"),
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
    public List<Medico> listar() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medico m = new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    rs.getString("data_nascimento"),
                    rs.getString("genero"),
                    rs.getString("crm"),
                    rs.getString("especialidade"),
                    rs.getString("email"),
                    rs.getString("telefone")
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

