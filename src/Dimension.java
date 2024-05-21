import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dimension {
    private String nombre;
    private int col_id;
    private Map<String, List<String>> valores_idH;    // el map valores : hechos_id
    private Map<String, List<String>> valores_idD; // el map valores : dim_id
    private Tabla tabla_dimension;
    private int nivel_index;

    public Dimension(String n, int id, int ni, Tabla t){
        this.nombre = n;
        this.valores_idH = new HashMap<>();
        this.valores_idD = new HashMap<>();
        this.col_id = id;
        this.nivel_index = ni;
        this.tabla_dimension = t;
    }

    public void cargarMapaDimension(){
        Tabla tabla = this.getTabla();
        List<FilaTabla> filas = tabla.getFilas();
        int index = this.getValorIndex();
        int id = this.getId();

        for (FilaTabla fila : filas){
            String valorFila = fila.getContenidoColumna(index);
            String idFila = fila.getContenidoColumna(id);

            valores_idD.computeIfAbsent(valorFila, k -> new ArrayList<>()).add(idFila);
        }
    }

    public int getValorIndex(){
        return this.nivel_index;
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

    public Map<String, List<String>> getValoresIdD() {
        return valores_idD;
    }
}
