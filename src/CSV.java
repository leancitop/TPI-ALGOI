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

    public String getColumn(int index) {
        return columns.get(index);
    }

    public String getColumn(String columnName) {
        // Aquí puedes implementar la lógica para obtener la columna por nombre
        return null;
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

        // Tipos de datos y valores nulos
        // Supongamos que todas las columnas son de tipo String
        System.out.println("Data types: All columns are assumed to be String");

        // Estadísticas básicas
        // Supongamos que solo hay columnas numéricas
        for (int i = 0; i < columnCount; i++) {
            String columnName = "Column " + i;
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            int nullCount = 0;
            for (CSVRow row : rows) {
                String value = row.getColumn(i);
                if (value == null || value.isEmpty()) {
                    nullCount++;
                    continue;
                }
                double numericValue = Double.parseDouble(value);
                sum += numericValue;
                if (numericValue < min) {
                    min = numericValue;
                }
                if (numericValue > max) {
                    max = numericValue;
                }
            }
            double average = sum / (rowCount - nullCount);
            System.out.println("Column: " + columnName);
            System.out.println("  Mean: " + average);
            System.out.println("  Minimum: " + min);
            System.out.println("  Maximum: " + max);
            System.out.println("  Null values: " + nullCount);
        }
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