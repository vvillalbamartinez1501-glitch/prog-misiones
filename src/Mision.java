public class Mision {
    private int id;
    private String nombre;
    private String dificultad;
    private int nivelRecomendado;
    private int recompensaExperiencia;
    private boolean completada;


    public Mision(int id, String nombre, String dificultad, int nivelRecomendado, int recompensaExperiencia) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.nivelRecomendado = nivelRecomendado;
        this.recompensaExperiencia = recompensaExperiencia;
        this.completada = false;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public int getNivelRecomendado() {
        return nivelRecomendado;
    }

    public int getRecompensaExperiencia() {
        return recompensaExperiencia;
    }

    public boolean isCompletada() {
        return completada;
    }

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

        sb.append(" - completada: ");
        if (completada){
            sb.append("Completada");
        } else {
            sb.append("Sin completar");

        }
        return sb.toString();
    }
}
