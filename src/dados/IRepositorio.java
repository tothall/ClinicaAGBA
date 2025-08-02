/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dados;
import java.util.List;
import excecoes.IdAusenteException;
import excecoes.IdDuplicadoException;
import excecoes.PessoaOcupadoException;
import excecoes.SalaOcupadaException;

/**
 *
 * @author ezequiel
 * @param <T>
 */



public interface IRepositorio<T> {
    void adicionar(T objeto)throws Exception;
    void atualizar(T objeto)throws Exception;
    void remover(String id);       
    T buscar(String id);           
    List<T> listar();              
}

