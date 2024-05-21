import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hechos {
    private Tabla tabla;
    private Map<String, Integer> indexadoColumnas;


    public Hechos(String path){

        // TODO tiene que comprobar que existan las 3 dimensiones, o devolver una excepción

        this.tabla = LectorArchivos.leerCSV(path);
        this.indexadoColumnas = new HashMap<>(); // Inicializar el mapa
        
        List<String> columnas = tabla.getColumnas();
        for (int i = 0; i < columnas.size(); i++) {
            indexadoColumnas.put(columnas.get(i), i);
        }

        System.out.println("Se cargaron los hechos");
    }

    public Map<String, Integer> getIndexadoColumnas() {
        return indexadoColumnas;
    }

    public Map<String, String> getDimensionesMap(String idDimension, String valor) {
        Map<String, String> dimensionesMap = new HashMap<>();
        System.out.println(indexadoColumnas.get(idDimension));
        System.out.println(indexadoColumnas.get(valor));
        List<FilaTabla> filas = tabla.getFilas();
        for (int i = 0; i < filas.size(); i++) {
            dimensionesMap.put(
                filas.get(i).getContenidoColumna(indexadoColumnas.get(idDimension)), 
                filas.get(i).getContenidoColumna(indexadoColumnas.get(valor)));
        }
        return dimensionesMap;
    }

}