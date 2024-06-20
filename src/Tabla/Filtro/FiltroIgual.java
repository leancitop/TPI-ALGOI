package Tabla.Filtro;
import java.util.ArrayList;
import java.util.List;
import Tabla.Columna;

public class FiltroIgual extends Filtro {

    public FiltroIgual(){
    }

    public List<Integer> filtrar(Columna<?> columna, String valor){
        List<Integer> filasOk = new ArrayList<>();
        Integer indices = 0;
        for (Object valorRaw : columna.getDatos()){
            String valCol = String.valueOf(valorRaw);
            if(valCol.equals(valor)){
                filasOk.add(indices);
            }
            indices++;
        }
        return filasOk;
    }
}
    

