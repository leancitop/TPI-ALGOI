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
        this.indexadoColumnas = new HashMap<>(); // Inicializar el mapa

        Columna<?>[] columnas = tabla.getColumnas();
        for (int i = 0; i < columnas.length; i++) {
            indexadoColumnas.put(columnas[i].getNombre(), i);
        }
    }

    public Map<String, Integer> getIndexadoColumnas() {
        return indexadoColumnas;
    }

    public Map<String, String> getDimensionesMap(String idDimension, String valor) {
        Map<String, String> dimensionesMap = new HashMap<>();
        Columna<?>[] columnas = tabla.getColumnas();
        int indexIdDimension = indexadoColumnas.get(idDimension);
        int indexValor = indexadoColumnas.get(valor);
        
        for (int i = 0; i < columnas[0].getDatos().size(); i++) {
            dimensionesMap.put(
                columnas[indexIdDimension].getContenidoColumna(i),
                columnas[indexValor].getContenidoColumna(i)
            );
        }
        return dimensionesMap;
    }

    public List<String> getValores(){
        List<String> valores = new ArrayList<>(Arrays.asList(tabla.getHeaders()));
        return valores;
    }
}
