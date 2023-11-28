package org.example;

import Entidades.*;
import Servicios.LectorCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
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


        LectorCsv lc = new LectorCsv();

        //leer archivo resultados.csv y lleno lista de Partidos
        //Path rutaRelativa = Paths.get("src", "main", "resources", "resultados.csv" );
        String resultadosArchivo = "src\\main\\resources\\resultados.csv";
        lc.leerArchivoResultados(resultadosArchivo, partidos, rondas);

        //leer archivo pronosticos.csv para crear "solo Participantes"
        String pronosticosArchivo2 = "src\\main\\resources\\pronosticos.csv";
        lc.leerArchivoPronosticosParticipantes(pronosticosArchivo2,participantes);

        //leer archivo pronosticos.csv y lleno lista de Pronosticos
        String pronosticosArchivo = "src\\main\\resources\\pronosticos.csv";
        lc.leerArchivoPronosticos(pronosticosArchivo, pronosticos, partidos);


        //muestra los equipos y resultados de los Partidos
        for (Partido p : partidos) {

            System.out.println("PARTIDO");
            System.out.println("Equipo 1 :" + p.getEquipo1().getNombre());
            System.out.println(p.resultado(p.getEquipo1()));
            System.out.println("Equipo 2 :" + p.getEquipo2().getNombre());
            System.out.println(p.resultado(p.getEquipo2()));
            System.out.println(" ");
        }

        //Calcula los puntos(supuestamente)
        for (Participante p : participantes) {
            int suma = 0;

            System.out.println("PARTICIPANTE: " + p.getNombre());
            System.out.println(" ");

            for (Pronostico pronostico : pronosticos) {
                if (p.getNombre().equals(pronostico.getParticipante())) {
                    //System.out.println(p.getNombre());
                    System.out.println("Equipo elegido :" + pronostico.getEquipo().getNombre());
                    System.out.println("Resultado :" + pronostico.getResultado());
                    System.out.println("Puntos: " + pronostico.puntos());
                    suma = suma + pronostico.puntos();
                }

            }
            System.out.println("TOTAL:" + suma);
            System.out.println(" ");
        }

    }
}

