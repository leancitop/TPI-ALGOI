import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dimension {
    private String nombre;
    private int col_id;
    private String fk;
    private Map<String, List<String>> valores;    // el map valores : hechos_id
    private Map<String, List<String>> valores_id; // el map valores : dim_id
    private Tabla tabla_dimension;
    private int nivel_index;

    public Dimension(String n, int id, int ni){
        this.nombre = n;
        this.valores = new HashMap<>();
        this.valores_id = new HashMap<>();
        this.col_id = id;
        this.nivel_index = ni;
    }

    public void setTabla(Tabla t){
        this.tabla_dimension = t;
    }

    public void agregarValor(String valorCSV, String idFila) { // Si aun no existe el valor, arma la lista de IDS. Si ya existe, le suma el id a la lista existente de ese valor
        valores.computeIfAbsent(valorCSV, k -> new ArrayList<>()).add(idFila);
    }
    
    public int getValorIndex(){
        return this.nivel_index;
    }

    public void cargarMapaDimension(){
        Tabla tabla = this.getTabla();
        List<FilaTabla> filas = tabla.getFilas();
        int index = this.getValorIndex();
        int id = this.getId();

        for (FilaTabla fila : filas){
            String valorFila = fila.getContenidoColumna(index);
            String idFila = fila.getContenidoColumna(id);

            valores_id.computeIfAbsent(valorFila, k -> new ArrayList<>()).add(idFila);
        }
    }

    public Tabla getTabla() {
        return tabla_dimension;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return col_id;
    }


    public Map<String, List<String>> getValoresId() {
        return valores_id;
    }
}
