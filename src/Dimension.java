import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dimension {
    private String nombre;
    private String col_id;
    private Map<String, List<String>> valores;    // el map valores : hechos_id
    private Map<String, List<String>> valores_id; // el map valores : dim_id
    private Tabla tabla_dimension;

    public Dimension(String n, String i){
        this.nombre = n;
        this.valores = new HashMap<>();
        this.col_id = i;
    }

    public void setTabla(Tabla t){
        this.tabla_dimension = t;
    }

    public void agregarValor(String valorCSV, String idFila) { // Si aun no existe el valor, arma la lista de IDS. Si ya existe, le suma el id a la lista existente de ese valor
        valores.computeIfAbsent(valorCSV, k -> new ArrayList<>()).add(idFila);
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return col_id;
    }


    public Map<String, List<String>> getValores() {
        return valores;
    }
}
