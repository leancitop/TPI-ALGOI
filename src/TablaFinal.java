import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaFinal {
    
// tiene que guardar en una tabla el resultado del cruce entre el dict 
// que genera la dimension y el valor y la medida que se eligio


// TODO: Ver como pasarle el tipo de medida que queramos
// Ver como generar una tabla primero y luego imprimirla. Esto solo imprime las operaciones.

public static void imprimirTablaFinal(Dimension dimension, Hecho hechos, String valor){
    dimension.cargarMapaDimension();
    Map<String, List<String>> valoresId = dimension.getValoresIdD();
    Map<String, String> dimensionesMap = hechos.getDimensionesMap(dimension.getClaveForanea(), valor);
    Map<String, Double> tablaFinal = new HashMap<>();

    for (Map.Entry<String, List<String>> entrada : valoresId.entrySet()) {
        String clave = entrada.getKey();
        List<String> valore = entrada.getValue();
        if (valore.contains(dimension.getClaveForanea())){
            continue;
        }
        List<List<String>> listaClave = new ArrayList<>();
        Double suma = Medida.sumar(valore); // ACA CAMBIAR MEDIDA
        tablaFinal.put(clave, suma);
// metodo que toma los maps e imprime en formato tabular la tabla final
    }
    for (Map.Entry<String, Double> entry : tablaFinal.entrySet()) {
        System.out.println(entry.getKey() + " : " + entry.getValue());
    }
}
 



    // IGNORAR
    // public static Map<String, List<List<String>>> crearTablaFinal(Dimension dimension, Hecho hechos, String valor){
    //     dimension.cargarMapaDimension();
    //     Map<String, List<String>> valoresId = dimension.getValoresId();
    //     Map<String, String> dimensionesMap = hechos.getDimensionesMap(dimension.getFK(), valor);
    //     Map<String, List<List<String>>> tablaFinal = new HashMap<>();

    //     for (Map.Entry<String, List<String>> entrada : valoresId.entrySet()) {
    //         String clave = entrada.getKey();
    //         List<String> valore = entrada.getValue();
    //         List<List<String>> listaClave = new ArrayList<>();
    //         for (String id : valore){
    //             dimensionesMap.get(id);
    //             List<String> lista = Arrays.asList(id, dimensionesMap.get(id));
    //             listaClave.add(lista);
    //         }
    //         tablaFinal.put(clave, listaClave);
    //     }
    //     return tablaFinal;
    //     }
}

