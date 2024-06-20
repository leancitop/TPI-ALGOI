package Tabla.Filtro;
import java.util.ArrayList;
import java.util.List;
import Tabla.Columna;

public class FiltroDiferente extends Filtro {

    public FiltroDiferente(){
    }

    public List<Integer> filtrar(Columna<?> columna, String valor){
        List<Integer> filasOk = new ArrayList<>();
        Integer indices = 0;
        for (Object valorRaw : columna.getDatos()){
            String valCol = valorRaw.toString();
            if(!valCol.equals(valor)){
                filasOk.add(indices);
            }
            indices++;
        }
        return filasOk;
    }
}
