package Entidades;

public class Participante {
    private String Nombre;
    private int totalPuntos;


    public Participante(String nombre) {
        Nombre = nombre;
    }

    public Participante(String nombre, int totalPuntos) {
        Nombre = nombre;
        this.totalPuntos = totalPuntos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "Nombre='" + Nombre + '\'' +
                '}';
    }
}
