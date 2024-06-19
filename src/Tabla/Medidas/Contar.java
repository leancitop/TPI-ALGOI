package Tabla.Medidas;
import java.util.List;
import Tabla.Columna;
import Tabla.ColumnaNumerica;

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
