package Cubo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tabla.Tabla;
import Tabla.Medida;
import Tabla.Proyeccion;

public class Cubo {
    public String nombre;
    public Map<Dimension, Integer> dimensiones_niveles;
    public Map<Hechos, Medida> hechos_medidas;
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    public Cubo(String nombreCubo){
        nombre = nombreCubo;
        dimensiones_niveles = new HashMap<>();
        hechos_medidas = new HashMap<>();
    }

    public void agregarDimension(Dimension dimension, Integer index_nivel){
        dimensiones_niveles.put(dimension, index_nivel);
    }

    public void setHecho(Hechos hechos) {
    }    

    public void setDimensionesProyeccion(List<List<Object>> listaDimensionesProyeccion){
    }
    
    public void setHechosProyeccion(List<List<Object>> listaHechosProyeccion){
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
        Tabla tablaProyeccionCubo = new Tabla();

        // aca implementamos la lógica para que cada vez que se proyecta el cubo, se actualiza la tabla
        // según el estado actual del mismo, en sus niveles y medidas

        Proyeccion p = new Proyeccion(tablaProyeccionCubo);
        p.info();
        p.imprimirPrimerasDiezFilas();
    }
}
