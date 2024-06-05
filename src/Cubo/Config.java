package Cubo;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private String nombre;
    private Map<Dimension, Integer> dimensiones;
    private Hechos hechos;

    private Config(String nombre){
        this.nombre = nombre;
        dimensiones = new HashMap<>();
    }

    public static Config crearConfigCubo(String nombre){
        Config config = new Config(nombre);
        return config;
    }

    public void agregarDimension(String nombre, String path, int clave_foranea){
        Dimension dimension = new Dimension(nombre, path, clave_foranea);
        int indexNiveles = dimension.getTabla().getHeaders().length - 1;
        dimensiones.put(dimension, indexNiveles);
    }

    public void agregarHechos(String path){
        Hechos hechos = new Hechos(path);
        this.hechos = hechos;
    }

    protected String getNombre(){
        return nombre;
    }

    protected Map<Dimension, Integer> getDimensiones() {
        return dimensiones;
    }

    protected Hechos getHechos(){
        return hechos;
    }
}
