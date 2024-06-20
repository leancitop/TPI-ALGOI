package Tabla.Filtro;

import java.util.ArrayList;
import java.util.List;

import Tabla.Columna;

public class FiltroMenor extends Filtro {
    
    public FiltroMenor(){}

    public List<Integer> filtrar(Columna<?> columna, String valor){
        List<Integer> filasOk = new ArrayList<>();
        Integer indices = 0;
        double valorDouble;

        try {
            valorDouble = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor proporcionado debe ser un número");
        }
        for (Object valorRaw : columna.getDatos()){
            String val = valorRaw.toString();
            try {
                Double valCol = Double.parseDouble(val);
                if (valCol < valorDouble) {
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
