package Tabla;
import java.util.List;

public class Medida {

    public static Double sumar(List<String> valores){
        Double total=0.0; ///discusión sobre headers
        for (String valor: valores){
            total=total+Double.parseDouble(valor);
        }
        return total;
    }
    public static Double max(List<String> valores){
        Double valorMax=Double.parseDouble(valores.get(0));
        for (String valor: valores){
            if (Double.parseDouble(valor)>valorMax){
                valorMax=Double.parseDouble(valor);
            }
        }
        return valorMax;
    }
    public static Double min(List<String> valores){
        Double valorMin=Double.parseDouble(valores.get(0));
        for (String valor: valores){
            if (Double.parseDouble(valor)<valorMin){
                valorMin=Double.parseDouble(valor);
            }
        }
        return valorMin;
    }
    public static int count(List<String> lista){
        int cantidad = lista.size();
        return cantidad;
    }


    
}
