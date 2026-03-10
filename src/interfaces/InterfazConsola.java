package interfaces;

import gestores.GestorMisiones;
import misiones.Dificultad;

import java.util.Scanner;

public class InterfazConsola {
    GestorMisiones gestor;

    public InterfazConsola(GestorMisiones gestor) {
        this.gestor = gestor;
    }

    public String menuPrincipal =
        "1. Listar misiones" +
        "\n"+
        "2. Crear nueva misión"+
        "\n"+
        "3. Buscar misión"+
        "\n"+
        "9. Eliminar misión"+
        "\n"+
        "0. Salir";


    public String menuDificultades =
            "Introduce el número de la dificultad de la misión"+
            "\n" +
            "1. Fácil" +
            "\n"+
            "2. Medio"+
            "\n"+
            "3. Difícil";

    public int pedirOpcionMenu(String menu, int[] opcionesDisponibles){
        int opcionElegida;
        System.out.println("Ingresa una opción (elección mediante número): ");
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(menu);
            opcionElegida = sc.nextInt();
        } while (validarOpcionMenu(opcionElegida, opcionesDisponibles));
        System.out.println(menu);
        return  opcionElegida;
    }


    public boolean validarOpcionMenu(int opcionElegida, int[] opcionesDisponibles){
        boolean opcionValida = false;
        for (int i = 0 ; opcionValida == false && i  < opcionesDisponibles.length ; i++){
            if (opcionesDisponibles[i] == opcionElegida){
                opcionValida = true;
            }
        }
        return opcionValida;
    }

    public int agregarMision (GestorMisiones gestor){
        int id;
        String nombre;
        Dificultad dificultad = null;
        int numeroDificultad;
        int nivelRecomendado;
        int recompensaExperiencia;
        int posicionNuevaMision;

        id = pedirId();
        nombre = pedirNombre();
        numeroDificultad = pedirDificultad();
        switch (numeroDificultad){
            case 1 :
                dificultad = Dificultad.FACIL;
                break;
            case 2:
                dificultad = Dificultad.MEDIO;
                break;
            case 3:
                dificultad = Dificultad.DIFICIL;
                break;
        }

        nivelRecomendado = pedirIntPositivo("Introduce el nivel recomendado a tener para realizar esta misión");
        recompensaExperiencia = pedirIntPositivo("Introduce la experiencia que te dará como recompensa completar esta misión");

        posicionNuevaMision = gestor.crearMision(id, nombre, dificultad, nivelRecomendado,recompensaExperiencia);
        return posicionNuevaMision;
    }

    public static int pedirId(){
        int id;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Introduce el id de la misión: ");
            id = sc.nextInt();
            if (id < 0 ){
                System.out.println("Id introducido inválido, vuelve a intentarlo");
            }
        } while (id < 0);
        return id;
    }

    public static String pedirNombre(){
        String nombre;
        Scanner sc = new Scanner(System.in);
        //do{
        System.out.println("Introduce el nombre de la misión: ");
        nombre = sc.next();
        //} while ();
        return nombre;
    }

    public static int pedirDificultad(){
        int dificultad;
        Scanner sc = new Scanner(System.in);
        do {
            /* TODO: MOSTRAR MENU DIFICULTADES*/
            dificultad = sc.nextInt();
            if (dificultad < 0 || dificultad > 4){
                System.out.println("Error, vuelve a intentarlo");
            }

        } while (dificultad > 0 && dificultad < 4 );
        return dificultad;
    }

    public static int pedirIntPositivo(String textoPeticion){
        int numeroIntroducido;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(textoPeticion);
            numeroIntroducido = sc.nextInt();
            if (numeroIntroducido < 0 ){
                System.out.println("El número que introduzcas ha de ser positivo");
            }

        }while (numeroIntroducido > 0);
        return  numeroIntroducido;
    }

}
