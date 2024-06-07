package Tabla;
import java.util.ArrayList;
import java.util.List;

public class Tabla {
    private List<Columna<?>> columnas;
    private String[] headers;

    public Tabla() {
        this.columnas = new ArrayList<>();
        this.headers = new String[0];
    }
    public void agregarColumna(Columna<?> columna) {
        for (Columna<?> col : columnas) {
            if (col.getNombre().equals(columna.getNombre())) {
                System.out.println("La columna ya existe en la tabla.");
            }
        }
        columnas.add(columna);
        cargarHeaders(); // Actualizar los headers después de agregar la columna
    }

    // Método para eliminar una columna por su nombre
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

    public void cargarHeaders() {
        int numColumnas = columnas.size();
        headers = new String[numColumnas];

        for (int i = 0; i < numColumnas; i++) {
            headers[i] = columnas.get(i).getNombre();
        }
    }

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

    public String[] getHeaders() {
        return headers;
    }

    public List<Columna<?>> getColumnas() {
        return columnas;
    }

    public int getNumeroFilas() {
        if (columnas.isEmpty()) {
            return 0;
        }
        return columnas.get(0).getDatos().size();
    }

    public Integer getHeaderIndex(String nombre){
        Integer index=0;
        for (String headers : this.getHeaders()){
            if (headers.equals(nombre)){
                return index;
            }
            index++;
        }
        System.out.println("No se encontro el header");
        return null;
    }
}
