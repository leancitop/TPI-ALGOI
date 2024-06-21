package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase FiltroIgual extiende Filtro y proporciona una implementación para
 * filtrar filas de una columna que coinciden exactamente con un valor específico.
 */
public class FiltroIgual extends Filtro {

    /**
     * Constructor por defecto para la clase FiltroIgual.
     */
    public FiltroIgual() {
    }

    /**
     * Filtra las filas de una columna dada que coinciden exactamente con un valor específico.
     * 
     * @param columna La columna sobre la cual se realizará el filtrado.
     * @param valor El valor con el cual se compararán las filas.
     * @return Una lista de índices de las filas que coinciden exactamente con el valor especificado.
     */
    @Override
    public List<Integer> filtrar(Columna<?> columna, String valor) {
        List<Integer> filasOk = new ArrayList<>();
        int indices = 0;
        for (Object valorRaw : columna.getDatos()) {
            String valCol = String.valueOf(valorRaw);
            if (valCol.equals(valor)) {
                filasOk.add(indices);
            }
            indices++;
        }
        return filasOk;
    }
}
