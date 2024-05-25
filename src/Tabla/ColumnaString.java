package Tabla;
import java.util.List;
import java.util.ArrayList;

class ColumnaString extends Columna {
    private List<String> datos;

    public ColumnaString(String nombre) {
        super(nombre);
        this.datos = new ArrayList<>();
    }

    @Override
    public void agregarDato(String dato) {
        datos.add(dato);
    }

    public List<String> getDatos() {
        return datos;
    }

    @Override
    public String getContenidoColumna(int index) {
        if (index < 0 || index >= datos.size()) {
            return null; // o lanzar una excepción si lo prefieres
        }
        return datos.get(index);
    }
}
