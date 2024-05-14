import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dimension {
    private String nombre;
    private Map<String, List<String>> valores;

    public Dimension(String n){
        this.nombre = n;
        this.valores = new HashMap<>();
    }

    public void agregarValor(String valorCSV, String idFila) { // Si aun no existe el valor, arma la lista de IDS. Si ya existe, le suma el id a la lista existente de ese valor
        valores.computeIfAbsent(valorCSV, k -> new ArrayList<>()).add(idFila);
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, List<String>> getValores() {
        return valores;
    }
}
