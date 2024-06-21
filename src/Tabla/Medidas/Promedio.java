package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;
import Tabla.Columnas.ColumnaNumerica;

/**
 * La clase Promedio extiende Medida y proporciona una implementación para calcular
 * el valor promedio en una columna numérica en un conjunto específico de filas.
 */
public class Promedio extends Medida {
    
    /**
     * Constructor por defecto para la clase Promedio.
     */
    public Promedio() {
    }

    /**
     * Realiza una operación para calcular el promedio de los valores en una columna 
     * numérica dada y un conjunto específico de filas.
     * 
     * @param columna La columna sobre la cual se calculará el promedio.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se 
     *                     calculará el promedio.
     * @return El promedio de los valores como un valor double.
     * @throws IllegalArgumentException Si la columna no es una instancia de 
     *                                  ColumnaNumerica.
     */
    @Override
    public double operar(Columna<?> columna, List<Integer> indicesFilas) {
        if (columna instanceof ColumnaNumerica) {
            double suma = 0.0;
            for (int i : indicesFilas) {
                suma += (Double) columna.getContenidoFila(i);
            }
            return suma / indicesFilas.size();
        } else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
