package Tabla.Medidas;

import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase abstracta Medida define una plantilla para las operaciones que se 
 * pueden realizar en una columna de datos y un conjunto de filas específicas. Se usa para operar 
 * sobre una Tabla.
 */
public abstract class Medida {
    
    /**
     * Constructor por defecto para la clase Medida.
     */
    public Medida() {
    }

    /**
     * Realiza una operación en una columna dada y en un conjunto específico de filas.
     * Este método debe ser implementado por las subclases concretas de Medida.
     * 
     * @param columna La columna sobre la cual se realizará la operación.
     * @param indicesFilas Una lista de índices de las filas sobre las cuales se aplicará la operación.
     * @return El resultado de la operación como un valor double.
     */
    public abstract double operar(Columna<?> columna, List<Integer> indicesFilas);

    /**
     * Devuelve el nombre de la clase concreta de Medida.
     * 
     * @return El nombre de la clase sin el paquete como un String.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * Compara este objeto Medida con otro objeto para determinar si son iguales.
     * 
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Medida otraMedida = (Medida) obj;
        return getClass().equals(otraMedida.getClass());
    }
}