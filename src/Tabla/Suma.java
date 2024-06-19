package Tabla;

import java.util.List;

public class Suma extends Medida{

    public Suma() {
    }

    public double operar(Columna<?> columna, List<Integer> indicesFilas){
        if (columna instanceof ColumnaNumerica) {
            double suma = 0.0;
            for (int i : indicesFilas) {
                suma += (double) columna.getContenidoFila(i);
            }
            return suma;
        }else{
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
