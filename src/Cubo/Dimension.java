package Cubo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tabla.Columna;
import Tabla.Tabla;
import Tabla.ColumnaNumerica;

public class Dimension {
    private String nombre;
    private Map<Double, List<Double>> valores_idD; // el map valores : dim_id
    private Map<Double, Double[]> valores_idH; // map valores : fact_id
    private Tabla tabla;
    private int nivel_index;
    private int clave_foranea;

    public Dimension(String nombre, int nivel, Tabla tabla, int clave_foranea){
        this.nombre = nombre;
        this.valores_idD = new HashMap<>();
        this.valores_idH = new HashMap<>();
        this.nivel_index = nivel;
        this.tabla = tabla;
        this.clave_foranea = clave_foranea;
    }

    public void cargarMapaDimension() {
        Columna<?>[] columnas = tabla.getColumnas();

        try {
            ColumnaNumerica columnaValores = (ColumnaNumerica) columnas[nivel_index];
            ColumnaNumerica columnaIds = (ColumnaNumerica) columnas[0];

            int numFilas = tabla.getNumeroFilas();

            for (int i = 0; i < numFilas; i++) {
                Double valorFila = columnaValores.getContenidoFila(i);
                Double idFila = columnaIds.getContenidoFila(i);

                valores_idD.computeIfAbsent(valorFila, k -> new ArrayList<>()).add(idFila);
            }
        } catch (ClassCastException e) {
            // Manejar la excepción de casting incorrecto
            System.err.println("Error de casting: " + e.getMessage());
        }
    }

    public int getNivelIndex(){
        return this.nivel_index;
    }

    public String getNombre() {
        return nombre;
    }

    public Map<Double, List<Double>> getValoresIdD() {
        return valores_idD;
    }

    public int getClaveForanea(){
        return this.clave_foranea;
    }

    public List<String> getNiveles(){
        List<String> niveles = new ArrayList<>(Arrays.asList(tabla.getHeaders()));
        niveles.remove(0);
        return niveles;
    }
}