package interfaces;

import gestores.GestorMisiones;
import misiones.Dificultad;
import misiones.Mision;

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

    public void borrarMision(GestorMisiones gestor){
        String confirmacionEliminacion ;
        int[] opcionesDisponibles = {1,9};

        int opcionElegida;
        Mision misionAEliminar;

        System.out.println(gestor.listarMisiones());

        // pedir id hasta que haya mision

        misionAEliminar = pedirIdMision("Introduce el id de la misión que quieres eliminar",gestor);

        confirmacionEliminacion = ("Estás seguro que quieres eliminar la misión?" +
                "1. Cancelar \t \t" +
                "9. Confirmar");

        opcionElegida = pedirOpcionMenu(confirmacionEliminacion,opcionesDisponibles);

        switch(opcionElegida){
            case 1:
                gestor.eliminarMisionPorId(misionAEliminar.getId());
                break;
            case 9:
                System.out.println("Operación cancelada");
                break;
        }
    }

    public void buscarMisionPorId(GestorMisiones gestor){
        Mision resultado;

        gestor.listarMisiones();

        resultado = pedirIdMision("Introduce el id de la misión",gestor);

        System.out.println(resultado.toString());
    }

    public Mision pedirIdMision(String texto, GestorMisiones gestor){
        int idElegido;
        Scanner sc = new Scanner(System.in);
        Mision resultado;
        do{
            System.out.println(texto);
            idElegido = sc.nextInt();
            resultado = gestor.buscarMisionPorId(idElegido);
            if (resultado == null){
                System.out.println("No existe una misión ocn ese id, vuelve a intentarlo");
            }
        } while (resultado != null);

        return resultado;
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

    public static void hacerDespedida(){
        System.out.println("Cerrando el programa...");
    }

    public static void imprimir(String impresion){
        System.out.println(impresion);
    }

    public static void menuBuscarMision(){

    }

}
