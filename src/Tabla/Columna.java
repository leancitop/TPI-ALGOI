package Tabla;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase abstracta Columna representa una columna de una tabla.
 * 
 * @param <T> el tipo de datos que almacenará la columna.
 */
public abstract class Columna<T> {
    private String nombre;
    private List<T> datos;

    /**
     * Constructor de la clase Columna.
     * 
     * @param nombre el nombre de la columna.
     */
    public Columna(String nombre) {
        this.nombre = nombre;
        this.datos = new ArrayList<>();
    }

    /**
     * Obtiene el nombre de la columna.
     * 
     * @return el nombre de la columna.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega un dato a la columna.
     * 
     * @param dato el dato a agregar.
     */
    public void agregarDato(T dato){
        datos.add(dato);
    }

    /**
     * Obtiene el contenido de una fila específica.
     *
     * @param nfila el número de la fila.
     * @return el contenido de la fila.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    public T getContenidoFila(int nfila) {
        if (nfila < 0 || nfila >= datos.size()) {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango: " + nfila);
        }
        return datos.get(nfila);
    }



    /**
     * Obtiene los datos de la columna.
     * 
     * @return una lista con los datos de la columna.
     */
    public List<T> getDatos() {
        return datos;
    }

    /**
     * Establece los datos de la columna, asegurándose de que sean del mismo tipo que los datos actuales.
     * 
     * @param datos una lista de datos para establecer en la columna.
     * @throws IllegalArgumentException si los datos proporcionados no son del mismo tipo que los datos de la columna.
     */
    public void setDatos(List<T> datos) {
        if (!datos.isEmpty() && datos.get(0).getClass().equals(this.datos.get(0).getClass())) {
            this.datos = datos;
        } else {
            throw new IllegalArgumentException("Los datos proporcionados no son del mismo tipo que los datos de la columna.");
        }
    }
}
