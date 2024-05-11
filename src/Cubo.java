import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cubo {
    String nombre;
    int cantidadDimensiones;
    Map<String, Dimension> listaDimensiones;
    // Por cada dimensión:
    //  - Niveles jerárquicos
    //  - Miembros (elementos) en formato tabular
    Map<String, Hechos> listaHechos;
    // Medidas representadas en cada celda

    Cubo(String nombreCubo){
        nombre = nombreCubo;
        listaDimensiones = new HashMap<String, Dimension>();
    }

    String getNombre(){
        return nombre;
    }

    Map<String, Dimension> getDimensiones(){
        return listaDimensiones;
    }

    void agregarDimension(Dimension dimension){
        listaDimensiones.put(dimension.getNombre(), dimension);;
    }

}
