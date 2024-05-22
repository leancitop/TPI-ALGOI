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
    private String clave_foranea;

    public Dimension(String nombre, int col_id, int nivel, Tabla tabla, String clave_foranea){
        this.nombre = nombre;
        this.valores_idH = new HashMap<>();
        this.valores_idD = new HashMap<>();
        this.col_id = col_id;
        this.nivel_index = nivel;
        this.tabla_dimension = tabla;
        this.clave_foranea = clave_foranea;
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

    public String getClaveForanea(){
        return this.clave_foranea;
    }
}
