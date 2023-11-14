package Entidades;


import java.util.List;

public class Ronda {
    private int numRonda;
    private List<Partido> partidos;

    //Constructor vacio y Constructor completo


    public Ronda(int numRonda) {
        this.numRonda = numRonda;
    }

    public Ronda(int numRonda, List<Partido> partidos) {
        this.numRonda = numRonda;
        this.partidos = partidos;
    }

    //Getters y Setters


    public int getNumRonda() {
        return numRonda;
    }

    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    //Metodo Puntos-Devuelve los puntos ganados en la ronda
    public int Puntos(List<Pronostico> pronosticos){
        int suma = 0;

        for (Pronostico pro : pronosticos){
            suma = suma + pro.puntos();
        }
        return suma;
    }


}
