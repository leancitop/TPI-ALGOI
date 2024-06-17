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
     */
    public void agregarColumna(Columna<?> columna) {
        for (Columna<?> col : columnas) {
            if (col.getNombre().equals(columna.getNombre())) {
                System.out.println("La columna ya existe en la tabla.");
            }
        }
        columnas.add(columna);
        cargarHeaders(); // Actualizar los headers después de agregar la columna
    }

    /**
     * Elimina una columna de la tabla por su nombre.
     * 
     * @param nombreColumna El nombre de la columna que se va a eliminar.
     */
    public void eliminarColumna(String nombreColumna) {
        boolean columnaEncontrada = false;
        for (Columna<?> col : columnas) {
            if (col.getNombre().equals(nombreColumna)) {
                columnas.remove(col);
                columnaEncontrada = true;
                break;
            }
        }
        if (!columnaEncontrada) {
            System.out.println("La columna no existe en la tabla.");
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
     */
    public void cargarTabla(String[][] matriz) {
        if (matriz.length == 0) {
            return;
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