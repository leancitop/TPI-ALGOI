import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tabla.Tabla;

public class Operador {
    static Tabla parsear(
        Map<String, Dimension> mapaDimensiones,
        List<List<Object>> dimensionesProyeccion,
        Hecho hecho,
        List<List<Object>> hechosProyeccion
        )
        {      

            // Armo la lista con los idDimension, para cada dimension y cada nivel

            List<List<Object>> listaIdDimensiones = new ArrayList<>();
            Integer numeroColumnas = 0; 

            for (List<Object> dimension : dimensionesProyeccion) {
                Map<String, Map<String, List<String>>> mapaDimensionIds = new HashMap<>();

                String nombreDimension = (String) dimension.get(0);
                int nivelDimension = (int) dimension.get(1);
                numeroColumnas += nivelDimension;

                for (int i = 1; i < nivelDimension+1; i++) {
                    String nivel = mapaDimensiones.get(nombreDimension).getTabla().getHeaders()[i];
                    Map<String, List<String>> mapaNivelId = mapaDimensiones.get(nombreDimension).getMapaNivelId(i);
                    mapaDimensionIds.put(nivel, mapaNivelId);
                }
                listaIdDimensiones.add(List.of(nombreDimension, mapaDimensionIds));
            }

            // Comienzo a armar la tabla para el return
            
            numeroColumnas += hechosProyeccion.size();
            Tabla tabla = new Tabla(numeroColumnas);
            System.out.println("Columnas: " + numeroColumnas);


            for (List<Object> dimension : listaIdDimensiones){
                System.out.println(dimension.get(0));
                String nombreDimension = (String) dimension.get(0);
                System.out.println(mapaDimensiones.get(nombreDimension).getClaveForanea());
                hecho.getDimensionesMap(mapaDimensiones.get(nombreDimension).getClaveForanea(), "costo");
            }

            return null;
    }

    // static Tabla agrupar(){}

    // static Tabla filtrar(){}

    // static Tabla removerDimension(){}

    // static Tabla sumarizar(){}

    // static void visualizar(Tabla tabla){}
}
