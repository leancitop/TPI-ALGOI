package Tabla;

import java.util.List;

public class Contar extends Medida{

    public Contar() {
    }

    public double operar(Columna<?> columna, List<Integer> indicesFilas){
        if (columna instanceof ColumnaNumerica) {
            double contar = (double) indicesFilas.size();
            return contar;
        }else{
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
