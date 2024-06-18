package Tabla;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clase que representa una tabla con múltiples columnas.
 */
public class Tabla {
    private List<Columna<?>> columnas;
    private String[] headers;

    /**
     * Constructor por defecto que inicializa una tabla vacía.
     */
    public Tabla() {
        this.columnas = new ArrayList<>();
        this.headers = new String[0];
    }



    /**
     * Agrega una columna a la tabla.
     * 
     * @param columna La columna que se va a agregar.
     * @throws IllegalArgumentException si la columna es nula o si ya existe una columna con el mismo nombre.
     */
    public void agregarColumna(Columna<?> columna) {
        if (columna == null) {
            throw new IllegalArgumentException("La columna no debe ser nula.");
        }

        for (Columna<?> col : columnas) {
            if (col.getNombre().equals(columna.getNombre())) {
                throw new IllegalArgumentException("La columna ya existe en la tabla: " + columna.getNombre());
            }
        }

        columnas.add(columna);
        cargarHeaders(); // Actualizar los headers después de agregar la columna
    }

    /**
     * Elimina una columna de la tabla por su nombre.
     * 
     * @param nombreColumna El nombre de la columna que se va a eliminar.
     * @throws IllegalArgumentException si el nombre de la columna es nulo o si la columna no existe en la tabla.
     */
    public void eliminarColumna(String nombreColumna) {
        if (nombreColumna == null || nombreColumna.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la columna no debe ser nulo o vacío.");
        }

        boolean columnaEncontrada = false;
        for (Columna<?> col : columnas) {
            if (col.getNombre().equals(nombreColumna)) {
                columnas.remove(col);
                columnaEncontrada = true;
                break;
            }
        }
        if (!columnaEncontrada) {
            throw new IllegalArgumentException("La columna no existe en la tabla: " + nombreColumna);
        } else {
            cargarHeaders(); // Actualizar los headers después de eliminar la columna
        }
    }



    /**
     * Carga los headers (nombres de las columnas) en el array de headers.
     */
    public void cargarHeaders() {
        int numColumnas = columnas.size();
        headers = new String[numColumnas];

        for (int i = 0; i < numColumnas; i++) {
            headers[i] = columnas.get(i).getNombre();
        }
    }

/**
 * Carga los datos de la tabla a partir de una matriz de cadenas.
 * 
 * @param matriz La matriz de datos para cargar en la tabla.
 * @throws IllegalArgumentException si la matriz es nula o está vacía.
 * @throws IllegalStateException si ocurre un error al procesar los datos de la matriz.
 */
public void cargarTabla(String[][] matriz) {
    if (matriz == null || matriz.length == 0) {
        throw new IllegalArgumentException("La matriz no debe ser nula o estar vacía.");
    }

    String[] nombresColumnas = matriz[0];
    this.headers = nombresColumnas;

    for (int i = 0; i < nombresColumnas.length; i++) {
        try {
            Double.parseDouble(matriz[1][i]);
            columnas.add(new ColumnaNumerica(nombresColumnas[i]));
        } catch (NumberFormatException e) {
            columnas.add(new ColumnaString(nombresColumnas[i]));
        }
    }

    try {
        for (int i = 1; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                Columna<?> columna = columnas.get(j);
                if (columna instanceof ColumnaNumerica) {
                    ((ColumnaNumerica) columna).agregarDato(Double.valueOf(matriz[i][j]));
                } else if (columna instanceof ColumnaString) {
                    ((ColumnaString) columna).agregarDato(matriz[i][j]);
                }
            }
        }
    } catch (Exception e) {
        throw new IllegalStateException("Ocurrió un error al procesar los datos de la matriz: " + e.getMessage(), e);
    }
}

    /**
     * Obtiene los headers (nombres de las columnas) de la tabla.
     * 
     * @return Un array de cadenas con los nombres de las columnas.
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * Obtiene las columnas de la tabla.
     * 
     * @return Una lista de las columnas de la tabla.
     */
    public List<Columna<?>> getColumnas() {
        return columnas;
    }

    /**
     * Obtiene el número de filas en la tabla.
     * 
     * @return El número de filas en la tabla.
     */
    public int getNumeroFilas() {
        if (columnas.isEmpty()) {
            return 0;
        }
        return columnas.get(0).getDatos().size();
    }

    /**
     * Obtiene el índice de un header (nombre de columna) en la tabla.
     * 
     * @param nombre El nombre del header.
     * @return El índice del header.
     * @throws NoSuchElementException Si el header no se encuentra en la tabla.
     */
    public Integer getHeaderIndex(String nombre) {
        Integer index = 0;
        for (String headers : this.getHeaders()) {
            if (headers.equals(nombre)) {
                return index;
            }
            index++;
        }
        throw new NoSuchElementException("No se encontró el header: " + nombre);
    }

    /**
     * Obtiene un valor de la tabla por el ID y la columna.
     * 
     * @param id El ID de la fila.
     * @param columna El índice de la columna.
     * @return El valor en la posición especificada.
     */
    public Object getById(double id, int columna) {
        int index = this.columnas.get(0).getDatos().indexOf(id);
        Object valor = this.columnas.get(columna).getDatos().get(index);
        return valor;
    }
}