package Entidades;


import java.util.List;

public class Ronda {
    private String numero;
    private List<Partido> partidos;

    //Constructor vacio y Constructor completo


    public Ronda(String numero) {
        this.numero = numero;
    }

    public Ronda(String numero, List<Partido> partidos) {
        this.numero = numero;
        this.partidos = partidos;
    }

    //Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
