package gestores;

import misiones.Dificultad;
import misiones.Mision;

import java.util.ArrayList;

public class GestorMisiones {
    private ArrayList<Mision> misiones;

    public GestorMisiones() {
        this.misiones = new ArrayList<>();
    }

    // crear misión
    /**
     *
     * @param id id de la mision a crear
     * @param nombre nombre de la mision a crear
     * @param dificultad dificultad de la mision a crear
     * @param nivelRecomendado nivel recomendado de la mision a crear
     * @param recompensaExperiencia experiencia como recompensa obtenida tras realizar la misino
     * @return devuelve el id de la nueva mision creada
     */
    public int crearMision(int id, String nombre, Dificultad dificultad, int nivelRecomendado, int recompensaExperiencia){

        int idMisionCreada;
        Mision mision = new Mision(id, nombre, dificultad, nivelRecomendado, recompensaExperiencia);

        idMisionCreada  = agregarMision(mision);
        return idMisionCreada;
    }

    public int agregarMision(Mision mision){
        this.misiones.add(mision);
        return mision.getId();
    }



    // listar misiones

    /**
     * metodo que lista las misiones del egstor
     * @return informacion de cada mision de su formato toString
     */
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

    /**
     * Recorre el array list en busca de una mision cuyo id coincida con el dado como parámetro
     * @param idMisionBuscada el id de la mision que se buscará
     * @return la misión cuyo id coincida con el dado como parámetro
     */
    public Mision buscarMisionPorId(int idMisionBuscada){
        Mision misionBuscada = null;
        int posicionComprobacion = 0 ;
        while (!misiones.isEmpty() && misionBuscada == null) {
            if (misiones.get(posicionComprobacion) != null && misiones.get(posicionComprobacion).getId() == idMisionBuscada){
                misionBuscada = misiones.get(posicionComprobacion);
            }
            posicionComprobacion++;
        }
        return misionBuscada;
    }

    // eliminar misión por id

    /**
     * quita una mision del arraylist
     * @param idMisionAEliminar el id de la misino que se eliminará
     * @return la misión eliminada
     */
    public Mision eliminarMisionPorId(int idMisionAEliminar){
        Mision misionlEiminada;

        misionlEiminada = misiones.get(idMisionAEliminar);
        if (misionlEiminada != null) {
            misiones.remove(buscarMisionPorId(idMisionAEliminar));
        }

        return  misionlEiminada;
    }

}
