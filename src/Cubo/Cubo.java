package Cubo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tabla.Tabla;
import Tabla.Proyeccion;

public class Cubo {
    public String nombre;
    public Map<String, Integer[]> niveles_elegidos; // nombre_dimension : niveles_usados
    public Map<Dimension, Integer> niveles; 
    public Hechos hechos;
    public int idMedida;
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    public Cubo(String nombreCubo){
        nombre = nombreCubo;
        niveles = new HashMap<>();
    }

    public void agregarDimension(Dimension dimension, Integer index_nivel){
        niveles.put(dimension, index_nivel);
    }

    public void setHecho (Hechos hechos, int id) {
    }    

    public void setDimensionesProyeccion(List<List<Object>> listaDimensionesProyeccion){
    }
    
    public void setHechosProyeccion(List<List<Object>> listaHechosProyeccion){
    }

    public Cubo slice(){
        // filtra el cubo en una dimensión, con SOLO UN VALOR del nivel seleccionado. Debería devolver otro cubo
        return null;
    }

    public Cubo dice(){
        // es un slice pero con más de un valor. También debería devolver otro cubo
        return null;
    }

    void rollUp(Dimension dimension){
        // agarramos la dimension elegida y SUBIMOS su nivel de abstraccion en niveles
        // debería editar las dimensiones del cubo actual
    }

    void drillDown(){
        // agarramos la dimension elegida y BAJAMOS su nivel de abstraccion en niveles
        // debería editar las dimensiones del cubo actual
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
