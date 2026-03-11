import gestores.GestorMisiones;
import interfaces.InterfazConsola;
import misiones.Dificultad;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        InterfazConsola consola;
        GestorMisiones gestor;

        // creamos el gestor
        gestor = new GestorMisiones();

        // levantamos la interfaz
        consola = new InterfazConsola(gestor);

        int opcionElegida;
        int[] opcionesMenuPrincipal = {0,1,2,3,9};


        do {
            opcionElegida = consola.pedirOpcionMenu(consola.menuPrincipal,opcionesMenuPrincipal);

            switch (opcionElegida) {
                case 0:
                    consola.hacerDespedida();
                    break;
                case 1:
                    consola.imprimir(gestor.listarMisiones());
                    break;
                case 2:
                    consola.agregarMision(gestor);
                    break;
                case 3:
                    //todo: buscar mision por id - interfaz
                    consola.buscarMisionPorId(gestor);
                    break;
                case 9:
                    // eliminarMision
                    consola.borrarMision(gestor);
                    break;
            }

        }while (opcionElegida == 0);

    }

}


