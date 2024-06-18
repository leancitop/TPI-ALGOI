package Tabla;

import java.util.List;

/**
 * Clase que representa una proyección de una tabla.
 */
public class Proyeccion {
    private Tabla tabla;

    /**
     * Constructor que inicializa una proyección con la tabla especificada.
     * 
     * @param tabla La tabla que se va a proyectar.
     */
    public Proyeccion(Tabla tabla) {
        this.tabla = tabla;
    }

    /**
     * Imprime las primeras n filas de la tabla.
     * 
     * @param nfilas El número de filas a imprimir.
     */
    public void imprimirNfilas(int nfilas) {
        int numeroFilas = Math.min(tabla.getNumeroFilas(), nfilas);

        String[] headers = tabla.getHeaders();
        List<Columna<?>> columnas = tabla.getColumnas();

        int[] anchosMaximos = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            anchosMaximos[i] = headers[i].length();
        }

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < columnas.size(); j++) {
                Object valor = columnas.get(j).getContenidoFila(i);
                int longitud = valor.toString().length();
                if (longitud > anchosMaximos[j]) {
                    anchosMaximos[j] = longitud;
                }
            }
        }   

        for (int i = 0; i < headers.length; i++) {
            System.out.print(String.format("%-" + anchosMaximos[i] + "s", headers[i]) + " ");
        }
        System.out.println();

        for (int ancho : anchosMaximos) {
            System.out.print(String.format("%-" + ancho + "s", "").replace(' ', '-') + " ");
        }
        System.out.println();

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < columnas.size(); j++) {
                Object valor = columnas.get(j).getContenidoFila(i);
                System.out.print(String.format("%-" + anchosMaximos[j] + "s", valor) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Imprime información sobre la tabla, incluyendo los nombres de los headers,
     * la cantidad de columnas y la cantidad de filas.
     * 
     * Principalmente, lo utilizamos para debugear. Por eso está comentado.
     * 
     */
    // public void info() {
    //     System.out.println("Nombre de los headers:");
    //     String[] headers = tabla.getHeaders();
    //     for (String header : headers) {
    //         System.out.println(header);
    //     }

    //     System.out.println("Cantidad de columnas: " + tabla.getColumnas().size());
    //     System.out.println("Cantidad de filas: " + tabla.getNumeroFilas());
    // }
}