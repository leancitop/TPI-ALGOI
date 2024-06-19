package Tabla.Medidas;
import java.util.List;
import Tabla.Columna;
import Tabla.ColumnaNumerica;

public class Minimo extends Medida{
    
    public Minimo() {
    }

    public double operar(Columna<?> columna, List<Integer> indicesFilas){
        if (columna instanceof ColumnaNumerica) {
            double minimo = (double) columna.getContenidoFila(indicesFilas.get(0));
            for (int i : indicesFilas) {
                double valor = (double) columna.getContenidoFila(i);
                if (valor<minimo){
                    minimo=valor;
                }
            }
            return minimo;
        }else{
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
