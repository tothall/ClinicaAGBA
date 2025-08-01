/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorios;
import java.util.List;
import negocio.IdAusenteException;
import negocio.IdDuplicadoException;
import negocio.PessoaOcupadoException;
import negocio.SalaOcupadaException;

/**
 *
 * @author ezequiel
 * @param <T>
 */



public interface IRepositorio<T> {
    void adicionar(T objeto)throws IdDuplicadoException, IdAusenteException, SalaOcupadaException, PessoaOcupadoException;
    void atualizar(T objeto);
    void remover(String id);       
    T buscar(String id);           
    List<T> listar();              
}

