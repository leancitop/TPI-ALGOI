package Cubo;

import java.io.IOException;

import Lectores.LectorArchivo;
import Tabla.Tabla;

/**
 * Clase que representa una dimensión usada en un objeto Cubo.
 */
public class Dimension {
    private String nombre;
    private Tabla tabla;
    private int claveForanea;
    private int cantidadNiveles;

    /**
     * Constructor de la clase Dimension.
     * @param nombre Nombre de la dimensión.
     * @param path Ruta del archivo CSV para cargar la tabla asociada a la dimensión.
     * @param claveForanea Índice de la clave foránea en la tabla.
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    public Dimension(String nombre, String path, int claveForanea){
        this.nombre = nombre;
        try{
            String[][] csv = LectorArchivo.leerCSV(path);
            Tabla t = new Tabla();
            t.cargarTabla(csv);
            this.tabla = t;
            this.claveForanea = claveForanea;
            this.cantidadNiveles = tabla.getHeaders().length-1;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Obtiene el nombre de la dimensión.
     * @return Nombre de la dimensión.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la clave foránea de la dimensión.
     * @return Clave foránea de la dimensión.
     */
    public int getClaveForanea(){
        return claveForanea;
    }

    /**
     * Obtiene la tabla asociada a la dimensión.
     * @return Tabla asociada a la dimensión.
     */
    public Tabla getTabla(){
        return tabla;
    }

    /**
     * Obtiene el número de niveles de la dimensión.
     * @return Número de niveles de la dimensión.
     */
    public int getCantidadNiveles(){
        return cantidadNiveles;
    }

    /**
     * Devuelve una representación en cadena del objeto Dimension.
     * 
     * @return Una cadena que representa el objeto Dimension, incluyendo su nombre y niveles.
     */
    @Override
    public String toString() {
        String salida = "- Dimension: " + nombre + "\n" + "   - Niveles: [";
        for (int i = 1; i <= cantidadNiveles; i++) {
            if (i == cantidadNiveles){
                salida += tabla.getHeaders()[i] + "]";
            }else{
                salida += tabla.getHeaders()[i] + ", ";
            }
        }
        return salida;
    }
}