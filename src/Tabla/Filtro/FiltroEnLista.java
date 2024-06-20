package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columna;

public class FiltroEnLista extends Filtro {

    public FiltroEnLista(){}

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
