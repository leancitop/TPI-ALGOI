import java.util.ArrayList;
import java.util.List;

public class Cubo {
    String nombre;
    int cantidadDimensiones;
    List<Dimension> listaDimensiones;
    // Por cada dimensión:
    //  - Niveles jerárquicos
    //  - Miembros (elementos) en formato tabular
    String[] listaHechos;
    // Medidas representadas en cada celda

    Cubo(String nombreCubo){
        nombre = nombreCubo;
        listaDimensiones = new ArrayList<>();
    }

    String getNombre(){
        return nombre;
    }

    List<Dimension> getDimensiones(){
        return listaDimensiones;
    }

    void agregarDimension(Dimension dimension){
        listaDimensiones.add(dimension);
    }

}
