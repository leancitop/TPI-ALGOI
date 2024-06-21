package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase FiltroDiferente extiende Filtro y proporciona una implementación para
 * filtrar filas de una columna que no coinciden con un valor específico.
 */
public class FiltroDiferente extends Filtro {

    /**
     * Constructor por defecto para la clase FiltroDiferente.
     */
    public FiltroDiferente() {
    }

    /**
     * Filtra las filas de una columna dada que no coinciden con un valor específico.
     * 
     * @param columna La columna sobre la cual se realizará el filtrado.
     * @param valor El valor con el cual se compararán las filas.
     * @return Una lista de índices de las filas que no coinciden con el valor especificado.
     */
    @Override
    public List<Integer> filtrar(Columna<?> columna, String valor) {
        List<Integer> filasOk = new ArrayList<>();
        int indices = 0;
        for (Object valorRaw : columna.getDatos()) {
            String valCol = valorRaw.toString();
            if (!valCol.equals(valor)) {
                filasOk.add(indices);
            }
            indices++;
        }
        return filasOk;
    }
}
