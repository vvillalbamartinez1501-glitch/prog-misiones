import java.util.ArrayList;
import java.util.Scanner;

public class GestorMisiones {
    private ArrayList<Mision> misiones;

    public GestorMisiones() {
        this.misiones = new ArrayList<>();
    }

    // crear misión
    public Mision crearMision(int id, String nombre, String dificultad, int nivelRecomendado, int recompensaExperiencia){

    }





    // listar misiones
    public String listarMisiones() {
        StringBuilder sb = new StringBuilder();

        if (!misiones.isEmpty()) {
            for (Mision mision : misiones) {
                sb.append(mision.toString());
                sb.append("\n");
            }
        } else {
            sb.append("Todavía no ha sido registrada ninguna misión");
            sb.append("\n");
        }
        return sb.toString();
    }

    // buscar misión por id
    public Mision buscarMisionPorId(int idMisionBuscada){
        Mision misionBuscada = null;
        int posicionComprobacion = 0 ;
        while (!misiones.isEmpty() && misionBuscada == null) {
            if (misiones.get(posicionComprobacion).getId() == idMisionBuscada){
                misionBuscada = misiones.get(posicionComprobacion);
            }
            posicionComprobacion++;
        }
        return misionBuscada;
    }

    // eliminar misión por id
    public Mision eliminarMisionPorId(int idMisionAEliminar){
        Mision misionELiminada;

        misionELiminada = misiones.get(idMisionAEliminar);
        if (misionELiminada != null) {
            misiones.remove(buscarMisionPorId(idMisionAEliminar));
        }

        return  misionELiminada;
    }

}
