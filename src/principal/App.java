package principal;

import controller.Agba;


public class App {
    public static void main(String[] args) {
        Agba instancia = Agba.getInstancia();
        instancia.iniciarSistema();
    }

    
}

