package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase FiltroEnLista extiende Filtro y proporciona una implementación para
 * filtrar filas de una columna que están en una lista específica de valores.
 */
public class FiltroEnLista extends Filtro {

    /**
     * Constructor por defecto para la clase FiltroEnLista.
     */
    public FiltroEnLista() {}

    /**
     * Este método no está soportado para este filtro y lanza una excepción.
     * 
     * @param columna La columna sobre la cual se intentará realizar el filtrado.
     * @param valor El valor con el cual se intentará filtrar las filas.
     * @throws UnsupportedOperationException Siempre, ya que este método no está soportado.
     */
    @Override
    public List<Integer> filtrar(Columna<?> columna, String valor) {
        throw new UnsupportedOperationException("Este método no está soportado para este filtro.");
    }

    /**
     * Filtra las filas de la columna que están en la lista proporcionada.
     *
     * @param columna La columna a filtrar.
     * @param valores La lista de valores a comparar.
     * @return Una lista de índices de las filas donde el valor está en la lista dada.
     */
    public List<Integer> filtrar(Columna<?> columna, List<String> valores) {
        List<Integer> filasOk = new ArrayList<>();
        int indices = 0;

        for (Object valorRaw : columna.getDatos()) {
            String valCol = valorRaw.toString();
            if (valores.contains(valCol)) {
                filasOk.add(indices);
            }
            indices++;
        }

        return filasOk;
    }
}
