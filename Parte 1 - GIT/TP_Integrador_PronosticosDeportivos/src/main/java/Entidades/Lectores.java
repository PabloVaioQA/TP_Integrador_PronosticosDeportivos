package Entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Lectores {

    public Lectores() {
    }

    //METODO QUE LEE ARCHIVO "RESULTADOS" Y CREA LISTA DE PARTIDOS
    public static List<Partido> leerArchivoResultados(String resultados, List<Partido> partidos) {
        //Esta funcion lo que hace es leer el archivo CSV que contiene los resultados de los partidos y
        // va creando objetos de la clase Partido con sus respectivos equipos y goles de cada uno y la ronda a la que corresponde
        // por ultimo retorna una lista de estos partidos.
        BufferedReader lector;
        String linea;
        try {
            lector = new BufferedReader(new FileReader(resultados));
            while ((linea = lector.readLine()) != null) {
                //Se crea un array de string por cada linea
                String[] lineaArray = linea.split(";");

                //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                Equipo equipo1 = new Equipo(lineaArray[0], "equipo1");
                Equipo equipo2 = new Equipo(lineaArray[3], "equipo2");
                int golesEquipo1 = Integer.parseInt(lineaArray[1]);
                int golesEquipo2 = Integer.parseInt(lineaArray[2]);

                //Se instancia un nuevo objeto de la clase partido con los parametros requeridos por su constructor
                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
                System.out.println(partido.toString());

                //Se agrega cada partido en el arreglo partidos
                partidos.add(partido);
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }
        return partidos;
    }



    //METODO QUE LEE ARCHIVO "PRONOSTICOS" Y CREA LISTA DE PRONOSTICOS
    public static List<Pronostico> leerArchivoPronosticos(String pronosticosArchivo, List<Pronostico> pronosticos, List<Partido> partidos) {

        BufferedReader lector;
        String linea;
        try {
            lector = new BufferedReader(new FileReader(pronosticosArchivo));
            while ((linea = lector.readLine()) != null) {
                //Se crea un array de string por cada linea
                String[] lineaArray = linea.split(";");

                //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                Equipo equipo1 = new Equipo(lineaArray[0], "equipo1");
                String linea1 = lineaArray[1];
                String linea2 = lineaArray[2];
                String linea3 = lineaArray[3];
                Equipo equipo2 = new Equipo(lineaArray[4], "equipo2");

                //Creo los datos necesarios para el constructor de Pronostico
                Equipo equipoElegido = new Equipo();
                Partido partidoElegido = new Partido();
                ResultadoEnum resultado1 = null;

                //Controlo donde esta elegida la "x", para seleccionar equipo elegido y resultado (Consigo dato EQUIPO y RESULTADO)
                if(linea1.equals("x")){
                    resultado1 = ResultadoEnum.ganador;
                    equipoElegido  = equipo1;
                }else if(linea3.equals("x")){
                    resultado1 = ResultadoEnum.ganador;
                    equipoElegido = equipo2;
                }else{
                    resultado1 = ResultadoEnum.empate;
                }

                //Recorro la lista de partidos creada antes, para ver de que partido corresponde el pronostico (Consigo dato PARTIDO)
                for (Partido p:partidos) {
                    if (equipo1.getNombre().equals(p.getEquipo1().getNombre()) && equipo2.getNombre().equals(p.getEquipo2().getNombre())){
                        partidoElegido = p;
                    }
                }

                //Finalmente se instancia un nuevo objeto de la clase pronostico con los parametros requeridos por su constructor

                Pronostico pronostico = new Pronostico(partidoElegido,equipoElegido,resultado1);
                System.out.println(pronostico.toString());
                //Se agrega cada partido en el arreglo partidos
                pronosticos.add(pronostico);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }
        return pronosticos;
    }

}
