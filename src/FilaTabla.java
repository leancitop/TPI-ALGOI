import java.util.List;

// Clase FilaTabla representa una fila del archivo CSV
class FilaTabla {
    private List<String> columns;

    public FilaTabla(List<String> columns) {
        this.columns = columns;
    }

    public int getConteoColumnas() {
        return columns.size();
    }

    public List<String> getContenidoColumna(){
        return columns;
    }
    public String getContenidoColumna(int index){
        return this.columns.get(index);
    }
}
