package Tabla;

/**
 * Representa una columna de datos numéricos (tipo Double) en una tabla.
 * 
 * Extiende la clase `Columna` para proporcionar funcionalidad específica para 
 * trabajar con valores numéricos.
 */
public class ColumnaNumerica extends Columna<Double> {

    /**
     * Crea una nueva instancia de ColumnaNumerica con el nombre especificado.
     *
     * @param nombre El nombre de la columna.
     */
    public ColumnaNumerica(String nombre) {
        super(nombre);
    }
}
