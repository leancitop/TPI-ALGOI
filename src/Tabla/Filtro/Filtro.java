package Tabla.Filtro;

import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase abstracta Filtro define una plantilla para las operaciones de filtrado 
 * que se pueden realizar en una columna de datos con un valor específico.
 */
public abstract class Filtro {

    /**
     * Constructor por defecto para la clase Filtro.
     */
    public Filtro() {}

    /**
     * Filtra las filas de una columna dada que coinciden con un valor específico.
     * Este método debe ser implementado por las subclases concretas de Filtro.
     * 
     * @param columna La columna sobre la cual se realizará el filtrado.
     * @param valor El valor con el cual se filtrarán las filas.
     * @return Una lista de índices de las filas que coinciden con el valor especificado.
     */
    public abstract List<Integer> filtrar(Columna<?> columna, String valor);

}
