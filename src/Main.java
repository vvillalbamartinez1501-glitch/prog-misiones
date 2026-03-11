import gestores.GestorMisiones;
import interfaces.InterfazConsola;
import misiones.Dificultad;

public class Main {
    public static void main(String[] args){
        InterfazConsola consola;
        GestorMisiones gestor;

        // creamos el gestor
        gestor = new GestorMisiones();

        // levantamos la interfaz
        consola = new InterfazConsola(gestor);

        // Dificultad dificultad = Dificultad.MEDIO;

        // dificultad.mostrarDificultades();

        int opcionElegida;
        int[] opcionesMenuPrincipal = {0,1,2,3,9};

        generarValoresDefault(gestor);

        // System.out.println(gestor.listarMisiones());

        // opcionElegida = 1;
        // consola.ejecutarAccion(opcionElegida);


        do {
            opcionElegida = consola.pedirOpcionMenu(consola.menuPrincipal,opcionesMenuPrincipal);
            consola.ejecutarAccion(opcionElegida);
            if (opcionElegida!=0){
                consola.pulsarIntro();
            }
        }while (opcionElegida != 0);
    }

    public static void generarValoresDefault(GestorMisiones gestor){
        gestor.crearMision(1,"Inicia sesión", Dificultad.FACIL,1,10);
        gestor.crearMision(2,"Mata a tu primer enemigo", Dificultad.FACIL,5,10);
        gestor.crearMision(3,"Encuentra oro", Dificultad.MEDIO,2,50);
        gestor.crearMision(4,"Encuentra diamante", Dificultad.DIFICIL,10,100);
    }

}


