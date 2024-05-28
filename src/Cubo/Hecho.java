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

public class Hecho {
    private Tabla tabla;
    private Map<String, Integer> indexadoColumnas;

    public Hecho(String path, int numeroColumnas) {
        // TODO: Comprobar que existan las 3 dimensiones, o devolver una excepción

        String[][] csv = LectorArchivos.leerCSV(path);
        Tabla tabla_csv = new Tabla(numeroColumnas);
        tabla_csv.cargarTabla(csv);

        this.tabla = tabla_csv;
        this.indexadoColumnas = new HashMap<>(); // Inicializar el mapa

        Columna<?>[] columnas = tabla.getColumnas();
        for (int i = 0; i < columnas.length; i++) {
            indexadoColumnas.put(columnas[i].getNombre(), i);
        }
    }

    public Map<String, Integer> getIndexadoColumnas() {
        return indexadoColumnas;
    }

    public Map<Double, Double> getMapaDimensionIdValor(int idDimension, int idMedida) {
        Map<Double, Double> dimensionesMap = new HashMap<>();
        Columna<?>[] columnas = tabla.getColumnas();        
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
            // Manejar la excepción de casting incorrecto
            System.err.println("Error de casting: " + e.getMessage());
            return null;
        }
    }

    public List<String> getValores(){
        List<String> valores = new ArrayList<>(Arrays.asList(tabla.getHeaders()));
        return valores;
    }
}
