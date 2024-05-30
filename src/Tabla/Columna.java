package Tabla;
import java.util.ArrayList;
import java.util.List;

public abstract class Columna<T> {
    private String nombre;
    private List<T> datos;

    public Columna(String nombre) {
        this.nombre = nombre;
        this.datos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarDato(T dato){
        datos.add(dato);
    }

    public T getContenidoFila(int nfila) {
        if (nfila < 0 || nfila >= datos.size()) {
            return null;
        }
        return datos.get(nfila);
    }

    public T getContenidoFila(Double numeroDouble) {
        int nfila = numeroDouble.intValue();
        if (nfila < 0 || nfila >= datos.size()) {
            return null;
        }
        return datos.get(nfila);
    }

    public List<T> getDatos() {
        return datos;
    }
    public void setDatos(List<T> datos) {
        // Se verifica si los datos son del mismo tipo que la columna antes de asignarlos
        if (!datos.isEmpty() && datos.get(0).getClass().equals(this.datos.get(0).getClass())) {
            this.datos = (List<T>) datos;
        } else {
            System.err.println("Los datos proporcionados no son del mismo tipo que los datos de la columna.");
        }
    }
}
