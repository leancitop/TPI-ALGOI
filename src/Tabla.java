import java.util.ArrayList;
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

// Clase Tabla representa la tabla completa del archivo CSV
class Tabla {
    private List<FilaTabla> filas;

    public static enum funciones{
        IGUAL,
        DISTINTO,
        MENOR,
        MAYOR,
        ENLISTA
    }

    public Tabla(List<FilaTabla> filas) {
        this.filas = filas;
    }

    public FilaTabla getFila(int index) {
        return filas.get(index);
    }

    public List<FilaTabla> getFilas(){
        return filas;
    }

    public int getConteoFilas() {
        return filas.size();
    }

    public int getConteoColumnas() {
        // Obtenemos la primera fila de la tabla
        FilaTabla primeraFila = filas.get(0);
        // Obtenemos el número de columnas de esa fila
        int conteoColumnas = primeraFila.getConteoColumnas();
        return conteoColumnas;
    }
    
    public List<String> getColumnas(){
        return filas.get(0).getContenidoColumna();
        // for (int column = 0; column < filas.get(0).getConteoColumnas(); column++) {
        //     System.out.println(column);
        //   }
    }

    public <T> Tabla filtrarTabla(int index, funciones fun, T valor){
        List<FilaTabla> filas = new ArrayList<FilaTabla>();
        int i = 0;
        for(FilaTabla row : this.filas){
            try{
                if(i == 0){ //todo: sacar esto y aplicar headers
                    i++;
                    continue;
                }
                switch (fun) {
                    case MENOR:
                        if(row.getContenidoColumna(index).compareTo((String)valor) < 0){
                            filas.add(row);}
                        break;
                    case MAYOR:
                        if(row.getContenidoColumna(index).compareTo((String)valor) > 0){
                            filas.add(row);}
                        break;
                    case IGUAL:
                        if(row.getContenidoColumna(index).equals(valor)){
                            filas.add(row);}
                        break;
                    case DISTINTO:
                        if(!row.getContenidoColumna(index).equals(valor)){
                            filas.add(row);}
                            break;
                    case ENLISTA:
                        break;
                }
            }catch(Exception e){
                throw e;
            }
        }
        Tabla tabla = new Tabla(filas);
        return tabla;
    }

    public void info() {
        // Número de filas y columnas
        int conteoFilas = filas.size();
        int conteoColumnas = filas.get(0).getConteoColumnas();
        System.out.println("Número de filas: " + conteoFilas);
        System.out.println("Número de columnas: " + conteoColumnas);

}
}


