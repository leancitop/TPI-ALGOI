package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;
import Tabla.Columnas.ColumnaNumerica;

/**
 * La clase Suma extiende Medida y proporciona una implementación para calcular
 * la suma de los valores en una columna numérica en un conjunto específico de filas.
 */
public class Suma extends Medida {

    /**
     * Constructor por defecto para la clase Suma.
     */
    public Suma() {
    }

    /**
     * Realiza una operación para calcular la suma de los valores en una columna 
     * numérica dada y un conjunto específico de filas.
     * 
     * @param columna La columna sobre la cual se calculará la suma.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se 
     *                     calculará la suma.
     * @return La suma de los valores como un valor double.
     * @throws IllegalArgumentException Si la columna no es una instancia de 
     *                                  ColumnaNumerica.
     */
    @Override
    public double operar(Columna<?> columna, List<Integer> indicesFilas) {
        if (columna instanceof ColumnaNumerica) {
            double suma = 0.0;
            for (int i : indicesFilas) {
                suma += (double) columna.getContenidoFila(i);
            }
            return suma;
        } else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
