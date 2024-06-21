package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;
import Tabla.Columnas.ColumnaNumerica;

/**
 * La clase Maximo extiende Medida y proporciona una implementación para encontrar
 * el valor máximo en una columna numérica en un conjunto específico de filas.
 */
public class Maximo extends Medida {

    /**
     * Constructor por defecto para la clase Maximo.
     */
    public Maximo() {
    }

    /**
     * Realiza una operación para encontrar el valor máximo en una columna numérica
     * dada y un conjunto específico de filas.
     * 
     * @param columna La columna sobre la cual se buscará el valor máximo.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se 
     *                     buscará el valor máximo.
     * @return El valor máximo encontrado como un valor double.
     * @throws IllegalArgumentException Si la columna no es una instancia de 
     *                                  ColumnaNumerica.
     */
    @Override
    public double operar(Columna<?> columna, List<Integer> indicesFilas) {
        if (columna instanceof ColumnaNumerica) {
            double maximo = (double) columna.getContenidoFila(indicesFilas.get(0));
            for (int i : indicesFilas) {
                double valor = (double) columna.getContenidoFila(i);
                if (maximo < valor) {
                    maximo = valor;
                }
            }
            return maximo;
        } else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
