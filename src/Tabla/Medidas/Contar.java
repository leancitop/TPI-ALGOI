package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;
import Tabla.Columnas.ColumnaNumerica;

/**
 * La clase Contar extiende Medida y proporciona una implementación para contar 
 * el número de filas específicas en una columna numérica.
 */
public class Contar extends Medida {

    /**
     * Constructor por defecto para la clase Contar.
     */
    public Contar() {
    }

    /**
     * Realiza una operación de conteo en una columna numérica dada y un conjunto 
     * específico de filas.
     * 
     * @param columna La columna sobre la cual se realizará el conteo.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se 
     *                     aplicará el conteo.
     * @return El número de filas específicas como un valor double.
     * @throws IllegalArgumentException Si la columna no es una instancia de 
     *                                  ColumnaNumerica.
     */
    @Override
    public double operar(Columna<?> columna, List<Integer> indicesFilas) {
        if (columna instanceof ColumnaNumerica) {
            return (double) indicesFilas.size();
        } else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
