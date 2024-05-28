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
    
    public T getContenidoFila(int index) {
        if (index < 0 || index >= datos.size()) {
            return null; // o lanzar una excepción
        }
        T dato = datos.get(index);
        return dato;
    }
    public List<T> getDatos() {
        return datos;
    }
}