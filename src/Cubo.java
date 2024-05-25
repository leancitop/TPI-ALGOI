import java.util.HashMap;
import java.util.Map;


public class Cubo {
    String nombre;
    Map<String, Dimension> listaDimensiones;
    Hecho tablaHecho;
    // dimensionesProyeccion - se setea en setDimensionesProyeccion()
    // hechosProyeccion - objeto que se setea setDimensionesProyeccion()
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    Cubo(String nombreCubo){
        nombre = nombreCubo;
        listaDimensiones = new HashMap<String, Dimension>();
    }

    void agregarDimension(Dimension dimension){
        listaDimensiones.put(dimension.getNombre(), dimension);
    }

    void setHecho(Hecho hecho){
        tablaHecho = hecho;
    }

    void setDimensionesProyeccion(){
        // seteamos las dimensiones y los niveles en las que queramos visualizar
    }
    
    void setHechosProyeccion(){
        // elegimos los valores y las medidas con las que los queramos visualizar
    }

    void slice(){
        // seteamos un objeto slice que se va a usar en el metodo proyectar
    }

    void dice(){
        // seteamos un objeto slice que se va a usar en el metodo proyectar
    }

    void rollUp(){
        // agarramos la dimension elegida y modificamos su nivel en dimensionesProyeccion
    }

    void drillDown(){
        // agarramos la dimension elegida y modificamos su nivel en dimensionesProyeccion
    }

    void proyectar(){
        // Tabla tabla = new(Tabla)
        // tabla = tabla.parsear(listaDimensiones, tablaHecho, dimensionesProyeccion, hechosProyeccion)
        // tabla = tabla.agrupar()
        // si diceProyeccion o sliceProyeccion:
        //      por cada filtro:
        //          tabla = tabla.filtrar(filtro)
        // si sliceProyeccion:
        //      tabla = tabla.removerDimension(sliceProyeccion)
        // tabla = tabla.sumarizar()
        // tabla.visualizar()
    }
}
