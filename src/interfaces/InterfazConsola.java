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
        "5. Editar misión"+
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
        int nivelRecomendado;
        int recompensaExperiencia;
        int idNuevaMision;


        id = pedirId();
        nombre = pedirNombre();

        dificultad = pedirDificultad();

        nivelRecomendado = pedirIntPositivo("Introduce el nivel recomendado a tener para realizar esta misión");
        recompensaExperiencia = pedirIntPositivo("Introduce la experiencia que te dará como recompensa completar esta misión");

        idNuevaMision = gestor.crearMision(id, nombre, dificultad, nivelRecomendado,recompensaExperiencia);
        System.out.println("Misión creada correctamente");
        return idNuevaMision;
    }

    public Dificultad pedirDificultad(){
        int numeroDificultad;
        int[] opcionesDificultad = {1,2,3};
        Dificultad dificultad = null;

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
        return dificultad;
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

        misionAEliminar = pedirIdMision("Introduce el id de la misión que quieres eliminar");

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

        resultado = pedirIdMision("Introduce el id de la misión: \t \t \t 0. Cancelar Operación");

        if (resultado != null){
            System.out.println(resultado.toString());

        }
    }

    /** todo
     * metodo que en busca una mision en base al id que introduce el usuario
     * @param texto el texto que va a imprimirse como peticion al usuario
     * @return la mision con id ocincidente al introducido por el usuario
     */

    public Mision pedirIdMision(String texto){
        int idElegido;
        Mision resultado;
        boolean operacionCancelada = false;
        do{
            System.out.println(texto);
            System.out.println("0. Cancelar");
            idElegido = sc.nextInt();
            resultado = gestor.buscarMisionPorId(idElegido);
            if (resultado == null && idElegido == 0){
                System.out.println("No existe una misión con ese id, vuelve a intentarlo");
            } else if (idElegido == 0){
                operacionCancelada = true;
            }
        } while (resultado == null &&  operacionCancelada);

        return resultado;
    }

    /**
     * metodo que pide un numero entero positivo, le cual será usado como id de la mision creada
     * @return el id de la nueva misión
     */

    public int pedirId(){
        int id;
        boolean repetido;
        do{
            repetido = false;
            System.out.println("Introduce el id de la misión: ");
            id = sc.nextInt();
            if (id < 0 ){
                System.out.println("Id introducido inválido, ha de ser positivo, vuelve a intentarlo");
            } else if (gestor.buscarMisionPorId(id) != null){
                System.out.println("Introduce un id distinto, este está repetido");
                repetido = true;
            }
        } while (id < 0 || repetido );
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

    public boolean pedirBoolean(String texto){
        boolean resultado;
        int opcion;
        int[] opcionesDisponibles = {1,0};

        opcion = pedirOpcionMenu(texto,opcionesDisponibles);

        resultado= (opcion == 1);

        return resultado;

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
            case 5:
                editarMision();
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

    // update mision

    public void editarMision(){
        Mision mision;
        int opcionIntroducida;
        int[] opcionesMenuCambioMision = {1,2,3,4,5,0};

        System.out.println(gestor.listarMisiones());
        // pedir mision
        mision = pedirIdMision("Introduce el id de la misión que quieres editar");

        if (mision != null){
            // decir todos los atributos y pedir opcion
            opcionIntroducida = pedirOpcionMenu(mostrarPosiblesCambios(mision), opcionesMenuCambioMision);

            ejecutarCambio(opcionIntroducida,mision);
            System.out.println("Cmabio realizado correctamente");
        }


    }

    public void ejecutarCambio(int cambioARealizar,Mision mision){
        switch(cambioARealizar){
            case 1:
                mision.setId(pedirId());
                break;
            case 2:
                mision.setNombre(pedirNombre());
                break;
            case 3:
                mision.setDificultad(pedirDificultad());
                break;
            case 4:
                mision.setNivelRecomendado(pedirIntPositivo("Introduce el nuevo nivel recomendado"));
                break;
            case 5:
                mision.setRecompensaExperiencia(pedirIntPositivo("Introduce la nueva recompensa"));
                break;
            case 6:
                mision.setCompletada(pedirBoolean("¿Quieres marcar esta misión como completada? \n\t 1. Si \n\t 0. No"));
                break;
            case 0:
                System.out.println("Cambio cancelado");
                break;
        }
    }
    /*/too
    public String mostrarValoresActuales(Mision mision){

    }
    */


    public String mostrarPosiblesCambios(Mision mision){
        StringBuilder sb = new StringBuilder();
        sb.append("Elige el atributo que quieres cambiar:");
        sb.append("\n");

        sb.append("1. Id: \t");
        sb.append(mision.getId());
        sb.append("\n");

        sb.append("2. Nombre: \t");
        sb.append(mision.getNombre());
        sb.append("\n");

        sb.append("3. Dificultad: \t");
        sb.append(mision.getDificultad().toString());
        sb.append("\n");

        sb.append("4. Nivel recomendado: \t");
        sb.append(mision.getNivelRecomendado());
        sb.append("\n");

        sb.append("5. Recompensa experiencia: \t");
        sb.append(mision.getRecompensaExperiencia());
        sb.append("\n");

        sb.append("6. Marcar como completada: \t");
        sb.append(mision.isCompletada());
        sb.append("\n");

        sb.append("0. Salir");
        sb.append("\n");

        return sb.toString();
    }

    // ejemplo operador terniario
        // [condicion]] ? [valor u accion si true]: [valor u accion si false]
}
