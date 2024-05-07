import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Clase CSVRow representa una fila del archivo CSV
class CSVRow {
    private List<String> columns;

    public CSVRow(List<String> columns) {
        this.columns = columns;
    }

    public int getColumnCount() {
        return columns.size();
    }
}

// Clase CSVTable representa la tabla completa del archivo CSV
class CSVTable {
    private List<CSVRow> rows;

    public CSVTable(List<CSVRow> rows) {
        this.rows = rows;
    }

    public CSVRow getRow(int index) {
        return rows.get(index);
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
            String[] columns = line.split(",");
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
