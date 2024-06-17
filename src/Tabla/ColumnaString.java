package Tabla;

/**
 * Representa una columna de datos de texto (tipo String) en una tabla.
 * 
 * Extiende la clase `Columna` para proporcionar funcionalidad específica para 
 * trabajar con valores de texto.
 */
public class ColumnaString extends Columna<String> {

    /**
     * Crea una nueva instancia de ColumnaString con el nombre especificado.
     *
     * @param nombre El nombre de la columna.
     */
    public ColumnaString(String nombre) {
        super(nombre);
    }
}
