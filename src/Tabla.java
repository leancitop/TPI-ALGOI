import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Tabla filtrarTabla(int index, funciones fun, String valor){
        List<FilaTabla> filas = new ArrayList<FilaTabla>();
        int i = 0;
        for(FilaTabla row : this.filas){
            try{
                if(i == 0){ //todo: sacar esto y aplicar headers
                    i++;
                    continue;
                }
                String[] columnas = row.getContenidoColumna(0).split(";");
                switch (fun) {
                    case MENOR:
                        if(columnas[index].compareTo(valor) < 0){
                            filas.add(row);}
                        break;
                    case MAYOR:
                        if(columnas[index].compareTo(valor) > 0){
                            filas.add(row);}
                        break;
                    case IGUAL:
                        if(columnas[index].equals(valor)){
                            filas.add(row);}
                        break;
                    case DISTINTO:
                        if(!columnas[index].equals(valor)){
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

    public Tabla filtrarTabla(int index, funciones fun, List<String> valor){
        List<FilaTabla> filas = new ArrayList<FilaTabla>();
        int i = 0;
        for(FilaTabla row : this.filas){
            try{
                if(i == 0){ //todo: sacar esto y aplicar headers
                    i++;
                    continue;}
                String[] columnas = row.getContenidoColumna(0).split(";");
                if (valor.contains(columnas[index])){
                    filas.add(row);}
                }
            catch(Exception e){
                throw e;}
            }
        Tabla tabla = new Tabla(filas);
        return tabla;
    }
    
        public Map<String, Integer>  Agrupar(int index){ //(Col, TipoFun)
        //count
        Map<String, Integer> mapa = new HashMap<String, Integer>();
        List<Object> valoresVistos = new ArrayList<>();
        for (FilaTabla fila : this.filas) {
            String val = fila.getContenidoColumna(index);
            if(valoresVistos.contains(val)){
                int valorActual = mapa.get(val);
                valorActual++;
                mapa.replace(val, valorActual);
            }
            else{
                mapa.put(val, 1);     
                valoresVistos.add(val);
            }
        }
        return mapa;
    }


    public void info() {
        // Número de filas y columnas
        int conteoFilas = filas.size();
        int conteoColumnas = filas.get(0).getConteoColumnas();
        System.out.println("Número de filas: " + conteoFilas);
        System.out.println("Número de columnas: " + conteoColumnas);

    }

}


