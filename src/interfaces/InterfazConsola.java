package interfaces;

import gestores.GestorMisiones;
import misiones.Dificultad;
import misiones.Mision;

import java.util.Scanner;

public class InterfazConsola {
    private final GestorMisiones gestor;
    private Scanner sc;

    /**
     * metodo constructor de la clase InterfazCOnsola
     * @param gestor el gestor que va a usar la interfaz
     */
    public InterfazConsola(GestorMisiones gestor) {
        this.gestor = gestor;
        this.sc = new Scanner(System.in);

        // this.iniciar();
    }

    /*
    public void iniciar(){
        int opcion;

        System.out.println("Iniciando consola...");

        mostrarMenu();

        opcion = leerOpcion();

        lanzarAccion(opcion);
    }
     */

    public String menuPrincipal =
        "1. Listar misiones" +
        "\n"+
        "2. Crear nueva misión"+
        "\n"+
        "3. Buscar misión"+
        "\n"+
        "4. Contar misiones"+
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

    /**
     * metodo que pide una opcion de menu
     * @param menu el menu que imprime
     * @param opcionesDisponibles las opciones disponibles, si la opcion elegida no se encuentra entre estas, repite el proceso hasta que lo hace
     * @return la opcion elegida
     */
    public int pedirOpcionMenu(String menu, int[] opcionesDisponibles){
        int opcionElegida;
        System.out.println("Ingresa una opción (elección mediante número): ");
        do{
            System.out.println(menu);
            opcionElegida = sc.nextInt();
        } while (!validarOpcionMenu(opcionElegida, opcionesDisponibles));
        return  opcionElegida;
    }


    /**
     * metodo que busca en las opciones disponibles la opcion elegida hasta que lo encuentra o hasta que termina el array
     * @param opcionElegida opcion introducida por el usuario
     * @param opcionesDisponibles opciones que tiene el usuario
     * @return true si la opcion elegida es valida, false si no
     */

    public boolean validarOpcionMenu(int opcionElegida, int[] opcionesDisponibles){
        boolean opcionValida = false;
        for (int i = 0 ; !opcionValida && i  < opcionesDisponibles.length ; i++){
            if (opcionesDisponibles[i] == opcionElegida){
                opcionValida = true;
            }
        }
        return opcionValida;
    }

    /**
     * metodo que crea paso a paso y agrega una mision al gestor
     * @return el id de la nueva mision en el gestor de misiones
     */

    public int agregarMision (){
        int id;
        String nombre;
        Dificultad dificultad = null;
        int numeroDificultad;
        int nivelRecomendado;
        int recompensaExperiencia;
        int idNuevaMision;
        int[] opcionesDificultad = {1,2,3,4};

        id = pedirId();
        nombre = pedirNombre();
        numeroDificultad = pedirOpcionMenu(menuDificultades,opcionesDificultad);
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

        idNuevaMision = gestor.crearMision(id, nombre, dificultad, nivelRecomendado,recompensaExperiencia);
        System.out.println("Misión creada correctamente");
        return idNuevaMision;
    }

    /**
     * metodo que borra un mision del gestor, pidiendo una confirmaicon previamente
     */

    public void borrarMision(){
        String confirmacionEliminacion ;
        int[] opcionesDisponibles = {1,9};

        int opcionElegida;
        Mision misionAEliminar;

        System.out.println(gestor.listarMisiones());

        // pedir id hasta que haya mision

        misionAEliminar = pedirIdMision("Introduce el id de la misión que quieres eliminar",gestor);

        confirmacionEliminacion = ("Estás seguro que quieres eliminar la misión?\n" +
                "1. Confirmar \t \t" +
                "9. Cancelar");

        opcionElegida = pedirOpcionMenu(confirmacionEliminacion,opcionesDisponibles);

        switch(opcionElegida){
            case 1:
                gestor.eliminarMisionPorId(misionAEliminar.getId());
                System.out.println("Misión eliminada correctamente");
                break;
            case 9:
                System.out.println("Operación cancelada");
                break;
        }
    }

    /**
     * metodo que imprime el listado d emisiones, pide el id de una de las misiones  eimprime la informacion de la misma
     */

    public void buscarMisionPorId(){
        Mision resultado;

        System.out.println(gestor.listarMisiones());

        resultado = pedirIdMision("Introduce el id de la misión: \t \t \t 0. Cancelar Operación",gestor);

        if (resultado != null){
            System.out.println(resultado.toString());

        }
    }

    /** todo
     * metodo que en busca una mision en base al id que introduce el usuario
     * @param texto el texto que va a imprimirse como peticion al usuario
     * @param gestor el gestor donde van a buscarse las misiones
     * @return la mision con id ocincidente al introducido por el usuario
     */

    public Mision pedirIdMision(String texto, GestorMisiones gestor){
        int idElegido;
        Mision resultado;
        boolean operacionCancelada = false;
        do{
            System.out.println(texto);
            idElegido = sc.nextInt();
            resultado = gestor.buscarMisionPorId(idElegido);
            if (resultado == null){
                System.out.println("No existe una misión con ese id, vuelve a intentarlo");
            } else if (idElegido == 0){
                operacionCancelada = true;
            }
        } while (resultado == null || operacionCancelada);

        return resultado;
    }

    /**
     * metodo que pide un numero entero positivo, le cual será usado como id de la mision creada
     * @return el id de la nueva misión
     */

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

    /**
     * metodo que pide un string, este será usado como el nombre de la misión creada
     * @return el nombre de la nueva misión
     */

    public static String pedirNombre(){
        String nombre;
        Scanner sc = new Scanner(System.in);
        //do{
        System.out.println("Introduce el nombre de la misión: ");
        nombre = sc.next();
        //} while ();
        return nombre;
    }

    /**
     * metodo que pide un numero entero positivo
     * @param textoPeticion la peticion a imprimr
     * @return el numero entero introducido por el usuario
     */

    public static int pedirIntPositivo(String textoPeticion){
        int numeroIntroducido;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(textoPeticion);
            numeroIntroducido = sc.nextInt();
            if (numeroIntroducido < 0 ){
                System.out.println("El número que introduzcas ha de ser positivo");
            }

        }while (!(numeroIntroducido > 0));
        return  numeroIntroducido;
    }

    /**
     * metodo que imprime una despedida, usado en caso de finalizacion del programa
     */

    public void hacerDespedida(){
        System.out.println("Cerrando el programa...");
    }

    /**
     * metodo que llama a diferentes metodos en base a la eleccion del usuario, siendo esta en forma de numero entero
     * @param opcionElegida la opcion elegida por el usuario
     */

    public void ejecutarAccion(int opcionElegida){
        switch (opcionElegida) {
            case 0:
                hacerDespedida();
                break;
            case 1:
                System.out.println(gestor.listarMisiones());
                break;
            case 2:
                agregarMision();
                break;
            case 3:
                buscarMisionPorId();
                break;
            case 4:
                decirNumeroMisiones();
                break;
            case 9:
                borrarMision();
                break;
        }
    }

    /**
     * metodo que imprime el numero actual de misiones registradas en el gestor
     *
     */

    private void decirNumeroMisiones(){
        System.out.println(String.format("Número de misiones actualmente registradas en el gestor: %d", this.gestor.contarMisiones()));
    }

    /*
    public void pulsarIntro(){
        String intro;

        System.out.println("----------- Pulsa Intro para continuar -----------");

        intro = sc.nextLine();
    }
     */
}
