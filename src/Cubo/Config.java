package Cubo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Tabla.Medida;
import Tabla.Tabla;
import Lectores.LectorCSV;

/**
 * Clase que representa la configuración inicial para un objeto Cubo.
 */
public class Config {
    private String nombre;
    private Map<Dimension, Integer> dimensiones;
    private Tabla hechos;
    private List<Medida> medidas;

    /**
     * Constructor privado de la clase Config.
     * @param nombre Nombre del cubo.
     */
    private Config(String nombre){
        this.nombre = nombre;
        dimensiones = new HashMap<>();
        medidas = new ArrayList<>();
    }

    /**
     * Método estático para crear una instancia de Config con el nombre especificado.
     * @param nombre Nombre del cubo.
     * @return Instancia de Config creada.
     */
    public static Config crearConfigCubo(String nombre){
        Config config = new Config(nombre);
        return config;
    }

    /**
     * Agrega una nueva dimensión al cubo con los parámetros especificados.
     * @param nombre Nombre de la dimensión.
     * @param path Ruta del archivo CSV que contiene los datos de la dimensión.
     * @param claveForanea Índice de la clave foránea en los datos de hechos asociados.
     * @throws IllegalArgumentException Si el nombre o el path son nulos o vacíos,
     *                                  o si ya existe una dimensión con el mismo nombre.
     */
    public void agregarDimension(String nombre, String path, int claveForanea) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la dimensión no puede ser nulo o vacío.");
        }
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("El path no puede ser nulo o vacío.");
        }
        if (dimensiones.containsKey(new Dimension(nombre, path, claveForanea))) {
            throw new IllegalArgumentException("La dimensión con el nombre " + nombre + " ya existe.");
        }

        Dimension dimension = new Dimension(nombre, path, claveForanea);
        int indexNiveles = dimension.getTabla().getHeaders().length - 1;
        dimensiones.put(dimension, indexNiveles);
    }
    
    /**
     * Agrega los datos de hechos al cubo desde el archivo CSV especificado.
     * @param path Ruta del archivo CSV que contiene los datos de hechos.
     * @throws IllegalArgumentException Si el path es nulo o vacío, o si las claves foráneas de las dimensiones
     *                                  no coinciden con los encabezados de los datos de hechos.
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    public void agregarHechos(String path){
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("El path no puede ser nulo o vacío.");
        }
        try{            
            LectorCSV lector = new LectorCSV();
            String[][] csv = lector.leerArchivo(path);
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
                    throw new IllegalArgumentException("Las claves foraneas de las dimensiones no coinciden con los hechos. En la dimension: \" + dimension.getNombre()");
                }
            }
            this.hechos = hechos;
        }catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Agrega medida que se usara en el cubo.
     * @param medida Medida a agregar. Puede agregarse cualquier objeto que extienda a la clase Medida.
     * @throws IllegalArgumentException Si la medida ya esta en la lista de medidas
     */
    public void agregarMedida(Medida medida) {
        if (medidas.contains(medida)) {
            throw new IllegalArgumentException("La medida [" + medida + "] ya esta cargada.");
        }
        medidas.add(medida);
    }

    /**
     * Obtiene el nombre del cubo.
     * @return Nombre del cubo.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene las dimensiones configuradas para el cubo.
     * @return Mapa de dimensiones con sus respectivos niveles.
     */
    public Map<Dimension, Integer> getDimensiones() {
        return dimensiones;
    }

    /**
     * Obtiene los datos de hechos configurados para el cubo.
     * @return Tabla de datos de hechos.
     */
    public Tabla getHechos(){
        return hechos;
    }

        /**
     * Obtiene los datos de hechos configurados para el cubo.
     * @return Lista de medidas.
     */
    public List<Medida> getMedidas() {
        return medidas;
    }
}
