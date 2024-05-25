package Tabla;
import java.util.List;
import java.util.ArrayList;

class ColumnaNumerica extends Columna {
    private List<Double> datos;

    public ColumnaNumerica(String nombre) {
        super(nombre);
        this.datos = new ArrayList<>();
    }

    @Override
    public void agregarDato(String dato) {
        try {
            datos.add(Double.parseDouble(dato));
        } catch (NumberFormatException e) {
            datos.add(Double.NaN); // Puede ser mejor manejar esto de otra manera
        }
    }

    public List<Double> getDatos() {
        return datos;
    }

    @Override
    public String getContenidoColumna(int index) {
        if (index < 0 || index >= datos.size()) {
            return null; // o lanzar una excepción si lo prefieres
        }
        return datos.get(index).toString();
    }
}
