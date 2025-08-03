/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;



/**
 *
 * @author ezequiel
 */
public abstract class Agba_abstrato implements IAgba{
    public Agba_abstrato() {
        iniciarSistema();
    }
    
    @Override
    public abstract void iniciarSistema();
    
    @Override
    public abstract void finalizarSistema();
    
}
