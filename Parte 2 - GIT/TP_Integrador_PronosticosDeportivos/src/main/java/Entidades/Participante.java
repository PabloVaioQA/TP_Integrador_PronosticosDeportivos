package Entidades;


import java.util.Objects;

public class Participante {
    private String nombre;
    private int totalPuntos;

    public Participante() {
    }

    public Participante(String nombre, int totalPuntos) {
        this.nombre = nombre;
        this.totalPuntos = totalPuntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                "nombre='" + nombre + '\'' +
                ", totalPuntos=" + totalPuntos +
                '}';
    }
}
