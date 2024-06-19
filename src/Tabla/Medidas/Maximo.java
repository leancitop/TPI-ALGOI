package Tabla.Medidas;
import java.util.List;
import Tabla.Columna;
import Tabla.ColumnaNumerica;

public class Maximo extends Medida{
    
    public Maximo() {
    }

    public double operar(Columna<?> columna, List<Integer> indicesFilas){
        if (columna instanceof ColumnaNumerica) {
            double maximo = (double) columna.getContenidoFila(indicesFilas.get(0));
            for (int i : indicesFilas) {
                double valor = (double) columna.getContenidoFila(i);
                if (maximo<valor){
                    maximo=valor;
                }
            }
            return maximo;
        }else {
            throw new IllegalArgumentException("Debe ser tipo de dato numerico.");
        }
    }
}
