package misiones;

public enum Dificultad {
    FACIL (1,1),
    MEDIO (5,5),
    DIFICIL(10,10);


    private int nivel;
    private int multiplicadorExperiencia;

    private Dificultad(int nivel, int multiplicadorExperiencia) {
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
        return "Dificultad{" +
                "nivel=" + nivel +
                ", multiplicadorExperiencia=" + multiplicadorExperiencia +
                '}';
    }

    public void mostrarDificultades(){
        for(Dificultad d : Dificultad.values()){
            System.out.println(d);
        }
    }
}
