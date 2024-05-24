public class FormatoTabular {

    public static void printTabularData(Tabla table) {
        int[] maxLengths = new int[table.getConteoFilas()];

        // Calcular la longitud máxima de cada columna
        for (Columna row : table.getFilas()) {
            for (int i = 0; i < row.getConteoColumnas(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.getContenidoColumna().get(i).length());
            }
        }

        // Imprimir los datos en formato tabular
        for (Columna row : table.getFilas()) {
            for (int i = 0; i < row.getConteoColumnas(); i++) {
                System.out.print(row.getContenidoColumna().get(i));
                // Agregar espacios para alinear correctamente las columnas
                for (int j = 0; j < maxLengths[i] - row.getContenidoColumna().get(i).length() + 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void printTabularData(Tabla table, int max) {
        int[] maxLengths = new int[table.getConteoFilas()];

        // Calcular la longitud máxima de cada columna
        for (int e = 0; e < max && e < table.getConteoFilas(); e++) {
            Columna row = table.getFila(e);
            for (int i = 0; i < row.getConteoColumnas(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.getContenidoColumna().get(i).length());
            }
        }

        // Imprimir los datos en formato tabular
        for (int e = 0; e < max && e < table.getConteoFilas(); e++) {
            Columna row = table.getFila(e);
            for (int i = 0; i < row.getConteoColumnas(); i++) {
                // (idea) Agregar una linea "---" que dependa del largo maximo de la tabla
                System.out.print(row.getContenidoColumna().get(i));
                // Agregar espacios para alinear correctamente las columnas
                for (int j = 0; j < maxLengths[i] - row.getContenidoColumna().get(i).length() + 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
