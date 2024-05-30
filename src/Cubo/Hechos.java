package Cubo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lectores.LectorArchivos;
import Tabla.Columna;
import Tabla.ColumnaNumerica;
import Tabla.Tabla;

public class Hechos {
    private Tabla tabla;
    private Map<String, Integer> indexadoColumnas;

    public Hechos(String path) {
        String[][] csv = LectorArchivos.leerCSV(path);
        Tabla tabla_csv = new Tabla();
        tabla_csv.cargarTabla(csv);

        this.tabla = tabla_csv;
        this.indexadoColumnas = new HashMap<>(); // Inicializar el mapa

        Columna<?>[] columnas = tabla.getColumnas().toArray(new Columna<?>[0]);
        for (int i = 0; i < columnas.length; i++) {
            indexadoColumnas.put(columnas[i].getNombre(), i);
        }
    }

    public Map<String, Integer> getIndexadoColumnas() {
        return indexadoColumnas;
    }

    public Map<Double, Double> getMapaDimensionIdValor(int idDimension, int idMedida) {
        Map<Double, Double> dimensionesMap = new HashMap<>();
        Columna<?>[] columnas = tabla.getColumnas().toArray(new Columna<?>[0]);     
        try {
            ColumnaNumerica columnaValores = (ColumnaNumerica) columnas[idDimension];
            ColumnaNumerica columnaIds = (ColumnaNumerica) columnas[idMedida];
            for (int i = 0; i < columnas[0].getDatos().size(); i++) {
                dimensionesMap.put(
                    columnaValores.getContenidoFila(i),
                    columnaIds.getContenidoFila(i)
                );
            }
            return dimensionesMap;
        } catch (ClassCastException e) {
            System.err.println("Error de casting: " + e.getMessage());
            return null;
        }
    }

    public Tabla getTabla() {
        return tabla;
    }

    public List<String> getValores() {
        if (tabla == null) {
            System.out.println("La tabla es nula en Hechos.getValores()");
        }
        if (tabla.getHeaders() == null) {
            System.out.println("Los encabezados son nulos en Hechos.getValores()");
        }
        return new ArrayList<>(Arrays.asList(tabla.getHeaders()));
    }
}