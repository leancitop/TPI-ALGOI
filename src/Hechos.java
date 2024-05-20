import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hechos {
    private Tabla tabla;
    private Map<String, Integer> indexadoColumnas; // Asegúrate de que esta línea esté presente

    // Constructor
    public Hechos(String path){
        this.tabla = LectorArchivos.leerCSV(path);
        this.indexadoColumnas = new HashMap<>(); // Inicializar el mapa
        
        List<String> columnas = tabla.getColumnas();
        for (int i = 0; i < columnas.size(); i++) {
            indexadoColumnas.put(columnas.get(i), i);
        }

        System.out.println("Se cargaron los hechos");
    }


    // Getter para indexadoColumnas
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
                filas.get(i).getContenido(indexadoColumnas.get(idDimension)), 
                filas.get(i).getContenido(indexadoColumnas.get(valor)));
        }
        return dimensionesMap;
    }

    // Puedes agregar otros métodos aquí según sea necesario
}



// id_producto;id_punto_venta;id_fecha;cantidad;valor_unitario;valor_total;costo
// 235;312;55;2;28.84;57.68;63.45
// 351;312;55;2;2024.99;4049.98;3796.19
// 348;312;55;2;2024.99;4049.98;3796.19

// - tiene que comprobar que existan las 3 dimensiones, o devolver una excepción

// - tiene que tener un GET que devuelva a partir de la tabla hechos armar un mapa con id_dimension : id_valor