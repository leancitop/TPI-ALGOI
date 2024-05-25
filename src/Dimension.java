import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tabla.Columna;
import Tabla.Tabla;

public class Dimension {
    private String nombre;
    private int col_id;
    private Map<String, List<String>> valores_idD; // el map valores : dim_id
    private Tabla tabla_dimension;
    private int nivel_index;
    private String clave_foranea;

    public Dimension(String nombre, int col_id, int nivel, Tabla tabla, String clave_foranea){
        this.nombre = nombre;
        this.valores_idD = new HashMap<>();
        this.col_id = col_id;
        this.nivel_index = nivel;
        this.tabla_dimension = tabla;
        this.clave_foranea = clave_foranea;
    }

    public void cargarMapaDimension() {
        Tabla tabla = this.getTabla();
        int valorIndex = this.getValorIndex();
        int id = this.getId();

        Columna<?>[] columnas = tabla.getColumnas();
        Columna<?> columnaValores = columnas[valorIndex];
        Columna<?> columnaIds = columnas[id];

        int numFilas = tabla.getNumeroFilas();

        for (int i = 0; i < numFilas; i++) {
            String valorFila = columnaValores.getContenidoColumna(i);
            String idFila = columnaIds.getContenidoColumna(i);

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

    public List<String> getNiveles(){
        List<String> niveles = new ArrayList<>(Arrays.asList(tabla_dimension.getHeaders()));
        niveles.remove(this.getClaveForanea());
        return niveles;
    }
}