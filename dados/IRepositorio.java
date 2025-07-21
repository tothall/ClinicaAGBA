/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dados;

import java.util.ArrayList;

public interface IRepositorio<T> {
    public void adicionar(T o);

    public void remover(T o);

    public void atualizar(T old_o, T new_o);
    
    public Object buscar(T o);

    public ArrayList<T> listar();
}
