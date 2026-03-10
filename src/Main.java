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


    }
    /*
    interfaz
    listar misiones
    crear mision
    buscar mision
    9 eliminar mision (solicita confirmacion
    0. salir)

     */

}


