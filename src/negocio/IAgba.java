/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import java.util.ArrayList;

/**
 *
 * @author ezequiel
 * @param <P>
 * @param <ID>
 */
public interface IAgba<P, ID> {
    void iniciarSistema();
    P cadastrar(P pessoa);
    P atualizar(ID id, P pessoaAtualizada);
    P buscar(ID id);
    P deletar(P pessoa);
    void listar(ArrayList<P> pessoas);
    void finalizarSistema();
}
