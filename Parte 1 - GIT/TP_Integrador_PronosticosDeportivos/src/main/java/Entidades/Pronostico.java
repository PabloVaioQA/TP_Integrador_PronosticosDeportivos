package Entidades;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultado;

    //Constructor vacio y Constructor completo
    public Pronostico() {
    }

    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    //Getters y Setters
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }


    //Metodo Puntos
    public int puntos() {

        if (partido.resultado(equipo) == resultado) {
            return 1;
        } else if ((resultado == ResultadoEnum.empate) && (partido.resultado(equipo) == ResultadoEnum.empate)) {
            return 1;
        } else {
            return 0;
        }
    }

    //Metodo ToString
    @Override
    public String toString() {
        return "Pronostico{" +
                "partido=" + partido.toString() +
                ", equipo=" + equipo.getNombre() +
                ", resultado=" + resultado.toString() +
                '}';
    }
}
