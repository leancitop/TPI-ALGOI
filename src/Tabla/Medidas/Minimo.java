package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;
import Tabla.Columnas.ColumnaNumerica;

/**
 * La clase Minimo extiende Medida y proporciona una implementación para encontrar
 * el valor mínimo en una columna numérica en un conjunto específico de filas.
 */
public class Minimo extends Medida {
    
    /**
     * Constructor por defecto para la clase Minimo.
     */
    public Minimo() {
    }

    /**
     * Realiza una operación para encontrar el valor mínimo en una columna numérica
     * dada y un conjunto específico de filas.
     * 
     * @param columna La columna sobre la cual se buscará el valor mínimo.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se 
     *                     buscará el valor mínimo.
     * @return El valor mínimo encontrado como un valor double.
     * @throws IllegalArgumentException Si la columna no es una instancia de 
     *                                  ColumnaNumerica.
     */
    @Override
    public double operar(Columna<?> columna, List<Integer> indicesFilas) {
        if (columna instanceof ColumnaNumerica) {
            double minimo = (double) columna.getContenidoFila(indicesFilas.get(0));
            for (int i : indicesFilas) {
                double valor = (double) columna.getContenidoFila(i);
                if (valor < minimo) {
                    minimo = valor;
                }
            }
            return minimo;
        } else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
