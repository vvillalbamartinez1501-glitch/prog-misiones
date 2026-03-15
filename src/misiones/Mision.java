package misiones;

public class Mision {
    private int id;
    private String nombre;
    private Dificultad dificultad;
    private int nivelRecomendado;
    private int recompensaExperiencia;
    private boolean completada;

    /**
     * metodo constructor de la clase misiones.Mision
     * @param id
     * @param nombre
     * @param dificultad
     * @param nivelRecomendado
     * @param recompensaExperiencia
     */
    public Mision(int id, String nombre, Dificultad dificultad, int nivelRecomendado, int recompensaExperiencia) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.nivelRecomendado = nivelRecomendado;
        this.recompensaExperiencia = recompensaExperiencia;
        this.completada = false;
    }

    /**
     * getter del id
     * @return el id de la mision
     */

    public int getId() {
        return id;
    }

    /**
     * getter del nombre
     * @return nombre de la mision
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * getter de la dificultad de la mision
     * @return nombre de la dificultad de la mision
     */
    public Dificultad getDificultad() {
        return dificultad;
    }

    /**
     * getter del nivel recomendado para realizar la mision
     * @return el nivel recomendado para realizar la mision
     */

    public int getNivelRecomendado() {
        return nivelRecomendado;
    }

    /**
     * getter de la recompensa de completar la mision
     * @return la recompensa por completar la mision
     */

    public int getRecompensaExperiencia() {
        return recompensaExperiencia;
    }

    /**
     * getter del estado de haber completado la mision
     * @return true si se completó la mision, false si no
     */

    public boolean isCompletada() {
        return completada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public void setNivelRecomendado(int nivelRecomendado) {
        this.nivelRecomendado = nivelRecomendado;
    }

    public void setRecompensaExperiencia(int recompensaExperiencia) {
        this.recompensaExperiencia = recompensaExperiencia;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    /**
     * toString de la clase misiones.Mision
     * @return la informacion de la mision en formato toString
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(id);
        sb.append("]");
        sb.append(" - ");
        sb.append(nombre);
        sb.append(" - dificultad: ");
        sb.append(dificultad);

        sb.append(" - nivel recomendado: ");
        sb.append(nivelRecomendado);

        sb.append(" - experiencia: ");
        sb.append(recompensaExperiencia);

        sb.append(" - ");
        if (completada){
            sb.append("Completada");
        } else {
            sb.append("Sin completar");

        }
        return sb.toString();
    }

    /**
     * metodo que devuelve el multiplicador de experiencia de la recompensa de la mision
     * @return el multiplicador de experiencia correspondiente a la dificultad
     */

    public int consultarMultiplicadorExperiencia(){
        return dificultad.getMultiplicadorExperiencia();
    }


}
