public class FormatoTabular {

    public static void printTabularData(CSVTable table) {
        int[] maxLengths = new int[table.getRowCount()];

        // Calcular la longitud máxima de cada columna
        for (CSVRow row : table.getRows()) {
            for (int i = 0; i < row.getColumnCount(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.getColumnContent().get(i).length());
            }
        }

        // Imprimir los datos en formato tabular
        for (CSVRow row : table.getRows()) {
            for (int i = 0; i < row.getColumnCount(); i++) {
                System.out.print(row.getColumnContent().get(i));
                // Agregar espacios para alinear correctamente las columnas
                for (int j = 0; j < maxLengths[i] - row.getColumnContent().get(i).length() + 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void printTabularData(CSVTable table, int max) {
        int[] maxLengths = new int[table.getRowCount()];

        // Calcular la longitud máxima de cada columna
        for (int e = 0; e < max && e < table.getRowCount(); e++) {
            CSVRow row = table.getRow(e);
            for (int i = 0; i < row.getColumnCount(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.getColumnContent().get(i).length());
            }
        }

        // Imprimir los datos en formato tabular
        for (int e = 0; e < max && e < table.getRowCount(); e++) {
            CSVRow row = table.getRow(e);
            for (int i = 0; i < row.getColumnCount(); i++) {
                System.out.print(row.getColumnContent().get(i));
                // Agregar espacios para alinear correctamente las columnas
                for (int j = 0; j < maxLengths[i] - row.getColumnContent().get(i).length() + 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
