package org.example;

import Entidades.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class PronosticosDeportivos {
    public static void main(String[] args) {

        //Crear Listas
        List<Partido> partidos = new ArrayList<>();
        List<Pronostico> pronosticos = new ArrayList<>();
        List<Ronda> rondas = new ArrayList<>();
        List<Participante> participantes = new ArrayList<>();


        //leer archivo resultados.csv y lleno lista de Partidos
        String resultadosArchivo = "C:\\Users\\vaiop\\OneDrive\\Documentos\\Pato\\UTN-Neoris\\ENTREGA FINAL\\Parte 2 - GIT\\TP_Integrador_PronosticosDeportivos\\src\\main\\resources\\resultados.csv";
        leerArchivoResultados(resultadosArchivo, partidos, rondas);
        //leer archivo pronosticos.csv para crear "solo Participantes"
        String pronosticosArchivo2 = "C:\\Users\\vaiop\\OneDrive\\Documentos\\Pato\\UTN-Neoris\\ENTREGA FINAL\\Parte 2 - GIT\\TP_Integrador_PronosticosDeportivos\\src\\main\\resources\\pronosticos.csv";
        leerArchivoPronosticosParticipantes(pronosticosArchivo2,participantes);
        //leer archivo pronosticos.csv y lleno lista de Pronosticos
        String pronosticosArchivo = "C:\\Users\\vaiop\\OneDrive\\Documentos\\Pato\\UTN-Neoris\\ENTREGA FINAL\\Parte 2 - GIT\\TP_Integrador_PronosticosDeportivos\\src\\main\\resources\\pronosticos.csv";
        leerArchivoPronosticos(pronosticosArchivo, pronosticos, partidos);

        //muestra los equipos y resultados de los Partidos
        for (Partido p : partidos) {
            System.out.println("PARTIDO");
            System.out.println("Equipo 1 :" + p.getEquipo1().getNombre());
            System.out.println(p.resultado(p.getEquipo1()));
            System.out.println("Equipo 2 :" + p.getEquipo2().getNombre());
            System.out.println(p.resultado(p.getEquipo2()));
        }

        //Calcula los puntos(supuestamente)
        for (Participante p : participantes) {
            int suma = 0;
            System.out.println(p.getNombre());a
            for (Pronostico pronostico : pronosticos) {
                if (p.getNombre().equals(pronostico.getParticipante())) {
                    //System.out.println(p.getNombre());
                    System.out.println("Equipo elegido :" + pronostico.getEquipo().getNombre());
                    System.out.println("resultado :" + pronostico.getResultado());
                    System.out.println(pronostico.puntos());
                    suma = suma + pronostico.puntos();
                }

            }
            System.out.println("total:" + suma);
        }

    }

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        //METODO QUE LEE ARCHIVO "RESULTADOS" Y CREA LISTA DE PARTIDOS

        private static List<Partido> leerArchivoResultados(String resultadosArchivo, List<Partido> partidos,List<Ronda> rondas){
            //Esta funcion lo que hace es leer el archivo CSV que contiene los resultados de los partidos y
            // va creando objetos de la clase Partido con sus respectivos equipos y goles de cada uno y la ronda a la que corresponde
            // por ultimo retorna una lista de estos partidos.
            BufferedReader lector;
            String linea;

            try {
                lector = new BufferedReader(new FileReader(resultadosArchivo));
                while ((linea = lector.readLine()) != null) {
                    //Se crea un array de string por cada linea
                    String[] lineaArray = linea.split(";");

                    //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                    //String numRonda = lineaArray[0];
                    Equipo equipo1 = new Equipo(lineaArray[1], "equipo1");
                    Equipo equipo2 = new Equipo(lineaArray[4], "equipo2");
                    int golesEquipo1 = Integer.parseInt(lineaArray[2]);
                    int golesEquipo2 = Integer.parseInt(lineaArray[3]);
                    int numRonda = Integer.parseInt(lineaArray[0]);

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



        //METODO QUE LEE ARCHIVO "PRONOSTICOS" Y CREA LISTA DE PRONOSTICOS----------------------------------------------

        private static List<Pronostico> leerArchivoPronosticos (String
        pronosticosArchivo, List < Pronostico > pronosticos, List < Partido > partidos){

            BufferedReader lector;
            String linea;

            try {
                lector = new BufferedReader(new FileReader(pronosticosArchivo));
                while ((linea = lector.readLine()) != null) {
                    //Se crea un array de string por cada linea
                    String[] lineaArray = linea.split(";");

                    //Se crean las variables y se le asignan los datos de cada posicion del array de strings
                    String nombreParticipante = lineaArray[0];
                    Equipo equipo1 = new Equipo(lineaArray[1], "equipo1");
                    String linea2 = lineaArray[2];
                    String linea3 = lineaArray[3];
                    String linea4 = lineaArray[4];
                    Equipo equipo2 = new Equipo(lineaArray[5], "equipo2");

                    //Creo los datos necesarios para el constructor de Pronostico
                    Equipo equipoElegido = new Equipo();
                    Partido partidoElegido = new Partido();
                    ResultadoEnum resultado1 = null;


                    //Controlo donde esta elegida la "x", para seleccionar equipo elegido y resultado (Consigo dato EQUIPO y RESULTADO)
                    if (linea2.equals("x")) {
                        resultado1 = ResultadoEnum.ganador;
                        equipoElegido = equipo1;

                    } else if (linea4.equals("x")) {
                        resultado1 = ResultadoEnum.ganador;
                        equipoElegido = equipo2;
                    } else {
                        resultado1 = ResultadoEnum.empate;

                    }

                    //Recorro la lista de partidos creada antes, para ver de que partido corresponde el pronostico (Consigo dato PARTIDO)
                    for (Partido p : partidos) {
                        if (equipo1.getNombre().equals(p.getEquipo1().getNombre()) && equipo2.getNombre().equals(p.getEquipo2().getNombre())) {
                            partidoElegido = p;
                        }
                    }

                    //Finalmente se instancia un nuevo objeto de la clase pronostico con los parametros requeridos por su constructor


                    Pronostico pronostico = new Pronostico(partidoElegido, equipoElegido, resultado1, nombreParticipante);
                    System.out.println(pronostico.toString());

                    //Se agrega cada partido en el arreglo partidos
                    pronosticos.add(pronostico);
                }

            } catch (Exception e) {
                System.out.println("Error al leer el archivo partidos");
            }
            return pronosticos;
        }



    //METODO QUE LEE ARCHIVO "PRONOSTICOS" Y CREA LISTA DE "SOLO PARTICIPANTES"-----------------------------------------

    private static List<Participante> leerArchivoPronosticosParticipantes (String pronosticosArchivo2, List<Participante> participantes){

        BufferedReader lector;
        String linea;
        ArrayList<String> listaDeNombres = new ArrayList<String>();

        try {
            lector = new BufferedReader(new FileReader(pronosticosArchivo2));
            while ((linea = lector.readLine()) != null) {

                //Se crea un array de string por cada linea
                String[] lineaArray = linea.split(";");

                //Solo busco el String nombre de la posicion 0;
                String nombreParticipante = lineaArray[0];

                //Me fijo que no se repita
                if(listaDeNombres.contains(nombreParticipante)){
                    System.out.println("El nombre ya esta incluido");
                }else{
                    listaDeNombres.add(nombreParticipante);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo partidos");
        }
        //System.out.println("Participantes"+ listaDeNombres);
        for (String nombre: listaDeNombres) {
            Participante participante = new Participante(nombre,0);
            participantes.add(participante);
        }
        return participantes;
    }

}

