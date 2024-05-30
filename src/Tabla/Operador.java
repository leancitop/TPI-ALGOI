package Tabla;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Cubo.Dimension;
import Cubo.Hechos;

public class Operador {
    public static Tabla parsear(Map<Dimension, Integer> niveles_cubo, Hechos hechos) {
        // Crear la tabla final
        Tabla tablaResultado = new Tabla();
    
        // Añadir columnas a la tabla resultado basadas en los niveles de dimensión
        for (Map.Entry<Dimension, Integer> entry : niveles_cubo.entrySet()) {
            Dimension dimension = entry.getKey();
            int nivel = entry.getValue();
            String nombreColumna = dimension.getTabla().getHeaders()[nivel];
            System.out.println("Nombre de columna para la dimensión " + dimension.getNombre() + ": " + nombreColumna);
    
            // Determinar tipo de columna (String o Numérica)
            Columna<?> columnaOriginal = dimension.getTabla().getColumnas().get(nivel);
            if (columnaOriginal instanceof ColumnaNumerica) {
                System.out.println("Agregando columna numérica para la dimensión " + dimension.getNombre());
                tablaResultado.setColumna(new ColumnaNumerica(nombreColumna));
            } else if (columnaOriginal instanceof ColumnaString) {
                System.out.println("Agregando columna de cadena para la dimensión " + dimension.getNombre());
                tablaResultado.setColumna(new ColumnaString(nombreColumna));
            }
        }
    
        // Añadir columnas de hechos a la tabla resultado
        for (String header : hechos.getValores()) {
            System.out.println("Agregando columna numérica para el hecho: " + header);
            // Determinar tipo de columna (String o Numérica)
            Columna<?> columnaOriginal = hechos.getTabla().getColumnas().stream()
                .filter(columna -> columna.getNombre().equals(header))
                .findFirst()
                .orElse(null);
    
            if (columnaOriginal instanceof ColumnaNumerica) {
                tablaResultado.setColumna(new ColumnaNumerica(header));
            } else if (columnaOriginal instanceof ColumnaString) {
                tablaResultado.setColumna(new ColumnaString(header));
            }
        }
        tablaResultado.cargarHeaders();
        return tablaResultado;
    }
    
    

    public static Tabla agrupar(Tabla tabla_operacion, List<String> columnas_agrupacion, List<String> columnas_a_agrupar) {
        // Crear la tabla resultado
        Tabla tablaResultado = new Tabla();
    
        // Agregar columnas de agrupación a la tabla resultado
        for (String columna : columnas_agrupacion) {
            // Determinar tipo de columna (String o Numérica)
            Columna<?> columnaOriginal = tabla_operacion.getColumnas().stream()
                    .filter(col -> col.getNombre().equals(columna))
                    .findFirst()
                    .orElse(null);
    
            if (columnaOriginal instanceof ColumnaNumerica) {
                tablaResultado.setColumna(new ColumnaNumerica(columna));
            } else if (columnaOriginal instanceof ColumnaString) {
                tablaResultado.setColumna(new ColumnaString(columna));
            }
        }
    
        // Crear un mapa para almacenar los valores agrupados
        Map<List<Object>, List<List<Object>>> grupos = new HashMap<>();
    
        // Recorrer las filas de la tabla original y agrupar según las columnas de agrupación
        for (int i = 0; i < tabla_operacion.getNumeroFilas(); i++) {
            // Crear una lista para almacenar la clave de agrupación
            List<Object> claveAgrupacion = new ArrayList<>();
            for (String columna : columnas_agrupacion) {
                Columna<?> columnaActual = tabla_operacion.getColumnas().stream()
                        .filter(col -> col.getNombre().equals(columna))
                        .findFirst()
                        .orElse(null);
                claveAgrupacion.add(columnaActual.getContenidoFila(i));
            }
    
            // Si el grupo no existe, se crea uno nuevo
            grupos.putIfAbsent(claveAgrupacion, new ArrayList<>());
    
            // Obtener la lista de valores para las columnas a agrupar
            List<Object> valoresAgrupar = new ArrayList<>();
            for (String columna : columnas_a_agrupar) {
                Columna<?> columnaActual = tabla_operacion.getColumnas().stream()
                        .filter(col -> col.getNombre().equals(columna))
                        .findFirst()
                        .orElse(null);
                valoresAgrupar.add(columnaActual.getContenidoFila(i));
            }
    
            // Agregar los valores al grupo correspondiente
            grupos.get(claveAgrupacion).add(valoresAgrupar);
        }
    
        // Agregar las columnas a agrupar a la tabla resultado
        for (String columna : columnas_a_agrupar) {
            // Determinar tipo de columna (String o Numérica)
            Columna<?> columnaOriginal = tabla_operacion.getColumnas().stream()
                    .filter(col -> col.getNombre().equals(columna))
                    .findFirst()
                    .orElse(null);
    
            if (columnaOriginal instanceof ColumnaNumerica) {
                tablaResultado.setColumna(new ColumnaNumerica(columna));
            } else if (columnaOriginal instanceof ColumnaString) {
                tablaResultado.setColumna(new ColumnaString(columna));
            }
        }
    
        // Llenar la tabla resultado con los grupos y valores agrupados
        for (Map.Entry<List<Object>, List<List<Object>>> entry : grupos.entrySet()) {
            List<Object> claveAgrupacion = entry.getKey();
            List<List<Object>> valoresGrupo = entry.getValue();
    
            // Agregar la clave de agrupación a la fila
            for (Object valor : claveAgrupacion) {
                if (valor instanceof Double) {
                    tablaResultado.getColumnas().stream()
                            .filter(col -> col instanceof ColumnaNumerica)
                            .forEach(col -> ((ColumnaNumerica) col).agregarDato((Double) valor));
                } else if (valor instanceof String) {
                    tablaResultado.getColumnas().stream()
                            .filter(col -> col instanceof ColumnaString)
                            .forEach(col -> ((ColumnaString) col).agregarDato((String) valor));
                }
            }
    
            // Agregar los valores agrupados a la fila
            for (List<Object> fila : valoresGrupo) {
                for (Object valor : fila) {
                    if (valor instanceof Double) {
                        tablaResultado.getColumnas().stream()
                                .filter(col -> col instanceof ColumnaNumerica)
                                .forEach(col -> ((ColumnaNumerica) col).agregarDato((Double) valor));
                    } else if (valor instanceof String) {
                        tablaResultado.getColumnas().stream()
                                .filter(col -> col instanceof ColumnaString)
                                .forEach(col -> ((ColumnaString) col).agregarDato((String) valor));
                    }
                }
            }
        }
        tablaResultado.cargarHeaders();
        return tablaResultado;
    }
    // static Tabla filtrar(){}

    // static Tabla removerDimension(){}

    // static Tabla sumarizar(){}

    // static void visualizar(Tabla tabla){}
}
