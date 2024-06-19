package Tabla.Medidas;
import java.util.List;
import Tabla.Columna;
import Tabla.ColumnaNumerica;

public class Promedio extends Medida{
    
    public Promedio() {
    }

    public double operar(Columna<?> columna, List<Integer> indicesFilas){
        if (columna instanceof ColumnaNumerica) {
            double suma = 0.0;
            for (int i : indicesFilas) {
                suma += (Double) columna.getContenidoFila(i);
            }
            return suma / indicesFilas.size();
        }else{
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
