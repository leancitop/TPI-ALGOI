package Tabla;
import java.util.List;


public abstract class Columna<T> {
    private String nombre;

    public Columna(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void agregarDato(String dato);
    
    public abstract String getContenidoColumna(int index);
    
    public abstract List<T> getDatos();
}