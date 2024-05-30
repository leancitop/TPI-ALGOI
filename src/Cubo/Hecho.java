package Cubo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lectores.LectorArchivos;
import Tabla.Columna;
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
        System.out.println("Filas: "+tabla_csv.getNumeroFilas());
        this.indexadoColumnas = new HashMap<>();

        Columna<?>[] columnas = tabla.getColumnas();
        for (int i = 0; i < columnas.length; i++) {
            indexadoColumnas.put(columnas[i].getNombre(), i);
        }
    }

    public Map<String, Integer> getIndexadoColumnas() {
        return indexadoColumnas;
    }

    public Tabla getTablaDimensionIdValor(String idDimension, String valor) {
        Tabla tablaRetorno = new Tabla(2);
        tablaRetorno.setColumna(0, tabla.getColumna(idDimension));
        tablaRetorno.setColumna(1, tabla.getColumna(valor));
        System.out.println(tablaRetorno.getNumeroFilas());
        return tablaRetorno;
    }

    public List<String> getValores(){
        List<String> valores = new ArrayList<>(Arrays.asList(tabla.getHeaders()));
        return valores;
    }
}
