package Cubo;
import java.util.HashMap;
import java.util.Map;
import Lectores.LectorArchivos;
import Tabla.Tabla;

public class Config {
    private String nombre;
    private Map<Dimension, Integer> dimensiones;
    private Tabla hechos;

    private Config(String nombre){
        this.nombre = nombre;
        dimensiones = new HashMap<>();
    }

    public static Config crearConfigCubo(String nombre){
        Config config = new Config(nombre);
        return config;
    }

    public void agregarDimension(String nombre, String path, int clave_foranea) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la dimensión no puede ser nulo o vacío.");
        }
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("El path no puede ser nulo o vacío.");
        }
        if (dimensiones.containsKey(new Dimension(nombre, path, clave_foranea))) {
            throw new IllegalArgumentException("La dimensión con el nombre " + nombre + " ya existe.");
        }

        Dimension dimension = new Dimension(nombre, path, clave_foranea);
        if (hechos != null) {
            String[] headers = hechos.getHeaders();

            if (clave_foranea < 0 || clave_foranea >= headers.length) {
                throw new IllegalArgumentException("La clave foranea está fuera del rango de los headers de los hechos.");
            }
    
            if (!headers[clave_foranea].equals(dimension.getTabla().getHeaders()[0])) {
                throw new IllegalArgumentException("La clave foranea de la dimension no coincide con los hechos.");
            }
        }
        int indexNiveles = dimension.getTabla().getHeaders().length - 1;
        dimensiones.put(dimension, indexNiveles);
    }
    

    public void agregarHechos(String path){
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("El path no puede ser nulo o vacío.");
        }
        String[][] csv = LectorArchivos.leerCSV(path);
        Tabla hechos = new Tabla();
        hechos.cargarTabla(csv);
        String[] headers = hechos.getHeaders();
        for (Map.Entry<Dimension, Integer> entry : dimensiones.entrySet()) {
            Dimension dimension = entry.getKey();
            String idDimension = dimension.getTabla().getHeaders()[0];
            if (dimension.getClaveForanea() < 0 || dimension.getClaveForanea() >= headers.length) {
                throw new IllegalArgumentException("La clave foranea está fuera del rango de los headers de los hechos. En la dimension: " + dimension.getNombre());
            }
            if (!headers[dimension.getClaveForanea()].equals(idDimension)){
                throw new IllegalArgumentException("Las claves foraneas de las dimensiones no coinciden con los hechos.");
            }
        }
        this.hechos = hechos;
    }

    public String getNombre(){
        return nombre;
    }

    public Map<Dimension, Integer> getDimensiones() {
        return dimensiones;
    }

    public Tabla getHechos(){
        return hechos;
    }
}
