package Tabla;

public class Proyeccion {
    private Tabla tabla;

    public Proyeccion(Tabla tabla) {
        this.tabla = tabla;
    }

    public void imprimirPrimerasDiezFilas() {
        // Obtener las primeras diez filas de la tabla
        int numeroFilas = Math.min(tabla.getNumeroFilas(), 10);

        // Imprimir encabezados
        String[] headers = tabla.getHeaders();
        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        // Imprimir datos
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < tabla.getColumnas().size(); j++) {
                System.out.print(tabla.getColumnas().get(j).getContenidoFila(i) + "\t");
            }
            System.out.println();
        }
    }

    public void info() {
        System.out.println("Nombre de los headers:");
        String[] headers = tabla.getHeaders();
        for (String header : headers) {
            System.out.println(header);
        }

        System.out.println("Cantidad de columnas: " + tabla.getColumnas().size());
        System.out.println("Cantidad de filas: " + tabla.getNumeroFilas());
    }
}
