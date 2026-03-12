package misiones;

public enum Dificultad {
    FACIL ("Fácil",1,1),
    MEDIO ("Medio",5,5),
    DIFICIL("Difícil",10,10);


    private final String nombre;
    private int nivel;
    private int multiplicadorExperiencia;

    private Dificultad(String nombre,int nivel, int multiplicadorExperiencia) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.multiplicadorExperiencia = multiplicadorExperiencia;
    }

    public int getNivel() {
        return nivel;
    }

    public int getMultiplicadorExperiencia() {
        return multiplicadorExperiencia;
    }
    @Override
    public String toString() {
        return nombre;
    }

    /*
    @Override
    public String toString() {
        return "Dificultad{" +
                "nivel=" + nivel +
                ", multiplicadorExperiencia=" + multiplicadorExperiencia +
                '}';
    }
     */

    public void mostrarDificultades(){
        for(Dificultad d : Dificultad.values()){
            System.out.println(d);
        }
    }
}
