package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columnas.Columna;

/**
 * La clase FiltroMenor extiende Filtro y proporciona una implementación para
 * filtrar filas de una columna que contienen valores numéricos menores que un valor dado.
 */
public class FiltroMenor extends Filtro {
    
    /**
     * Constructor por defecto para la clase FiltroMenor.
     */
    public FiltroMenor(){}

    /**
     * Filtra las filas de una columna que contienen valores numéricos menores que un valor dado.
     * 
     * @param columna La columna sobre la cual se realizará el filtrado.
     * @param valor El valor de comparación como String.
     * @return Una lista de índices de las filas que contienen valores numéricos menores que el valor dado.
     * @throws IllegalArgumentException Si el valor proporcionado no puede ser convertido a Double.
     * @throws IllegalArgumentException Si la columna contiene valores no numéricos.
     */
    public List<Integer> filtrar(Columna<?> columna, String valor){
        List<Integer> filasOk = new ArrayList<>();
        int indices = 0;
        double valorDouble;

        try {
            valorDouble = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor proporcionado debe ser un número");
        }
        
        for (Object valorRaw : columna.getDatos()) {
            String val = valorRaw.toString();
            try {
                Double valDouble = Double.parseDouble(val);
                if (valDouble < valorDouble) {
                    filasOk.add(indices);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La columna debe contener solo valores numéricos");
            }            
            indices++;
        }
        return filasOk;
    }    
}
