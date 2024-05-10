import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Clase CSVRow representa una fila del archivo CSV
class CSVRow {
    private List<String> columns;

    public CSVRow(List<String> columns) {
        this.columns = columns;
    }

    public int getColumnCount() {
        return columns.size();
    }

    public List<String> getColumnContent(){
        return columns;
    }
    public String getColumnContent(int index){
        return this.columns.get(index);
    }
}

// Clase CSVTable representa la tabla completa del archivo CSV
class CSVTable {
    private List<CSVRow> rows;

    public static enum funciones{
        IGUAL,
        DISTINTO,
        MENOR,
        MAYOR,
        ENLISTA
    }

    public CSVTable(List<CSVRow> rows) {
        this.rows = rows;
    }

    public CSVRow getRow(int index) {
        return rows.get(index);
    }

    public List<CSVRow> getRows(){
        return rows;
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        // Obtenemos la primera fila de la tabla
        CSVRow firstRow = rows.get(0);
        // Obtenemos el número de columnas de esa fila
        int columnCount = firstRow.getColumnCount();
        return columnCount;
    }
    
    public List<String> getColumns(){
        return rows.get(0).getColumnContent();
        // for (int column = 0; column < rows.get(0).getColumnCount(); column++) {
        //     System.out.println(column);
        //   }
    }

    public <T> CSVTable filtrarTabla(int index, funciones fun, T valor){
        List<CSVRow> rows = new ArrayList<CSVRow>();
        int i = 0;
        for(CSVRow row : this.rows){
            try{
                if(i == 0){ //todo: sacar esto y aplicar headers
                    i++;
                    continue;
                }
                if(fun == funciones.IGUAL){
                    if(row.getColumnContent(index).equals(valor))
                        rows.add(row);
                }
                if(fun == funciones.DISTINTO){
                    if(!row.getColumnContent(index).equals(valor))
                        rows.add(row);
                }
                if(fun == funciones.MAYOR){ //todo: cambiar tipos en getColumn
                    if(row.getColumnContent(index).compareTo((String)valor) > 0)
                        rows.add(row);
                }
                if(fun == funciones.MENOR){
                    if(row.getColumnContent(index).compareTo((String)valor) < 0)
                        rows.add(row);
                }
            }catch(Exception e){
                throw e;
            }
        }
        CSVTable tabla = new CSVTable(rows);
        return tabla;
    }

    public void info() {
        // Número de filas y columnas
        int rowCount = rows.size();
        int columnCount = rows.get(0).getColumnCount();
        System.out.println("Number of rows: " + rowCount);
        System.out.println("Number of columns: " + columnCount);

}
}

// Clase CSVReader se encarga de leer el archivo CSV y convertirlo en una lista de filas
class CSVReader {
    public static CSVTable readCSV(String filePath) throws IOException {
        List<CSVRow> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            
            // Explicación paso a paso de la expresión regular:
            // ",": Coincide con una coma en la cadena.
            // "(?=": Inicio de una lookahead assertion (aserción hacia adelante). Indica que la coma solo coincidirá si está seguida por lo que está dentro del grupo de aserción.
            // "(?: ... )": Grupo no capturador. Agrupa el patrón interno sin capturar su coincidencia.
            // "[^\"]*": Coincide con cualquier cantidad de caracteres que no sean comillas dobles.
            // "\"": Coincide con una comilla doble.
            // "[^\"]*": Coincide con cualquier cantidad de caracteres que no sean comillas dobles.
            // "\"": Coincide con otra comilla doble.
            // "*": Este asterisco se aplica al grupo completo, lo que significa que puede repetirse cualquier número de veces.
            // "[^\"]*$": Coincide con cualquier cantidad de caracteres que no sean comillas dobles al final de la cadena.

            List<String> columnList = new ArrayList<>();
            for (String column : columns) {
                columnList.add(column);
            }
            rows.add(new CSVRow(columnList));
        }
        reader.close();
        return new CSVTable(rows);
    }
}
