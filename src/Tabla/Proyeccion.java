package Tabla;

import java.util.List;

public class Proyeccion {
    private Tabla tabla;

    public Proyeccion(Tabla tabla) {
        this.tabla = tabla;
    }

public void imprimirPrimerasDiezFilas() {
    // Obtener las primeras diez filas de la tabla
    int numeroFilas = Math.min(tabla.getNumeroFilas(), 10);

    // Obtener encabezados y columnas
    String[] headers = tabla.getHeaders();
    List<Columna<?>> columnas = tabla.getColumnas();

    // Determinar el ancho máximo de cada columna
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

    // Imprimir encabezados
    for (int i = 0; i < headers.length; i++) {
        System.out.print(String.format("%-" + anchosMaximos[i] + "s", headers[i]) + " ");
    }
    System.out.println();

    // Imprimir separadores
    for (int ancho : anchosMaximos) {
        System.out.print(String.format("%-" + ancho + "s", "").replace(' ', '-') + " ");
    }
    System.out.println();

    // Imprimir datos
    for (int i = 0; i < numeroFilas; i++) {
        for (int j = 0; j < columnas.size(); j++) {
            Object valor = columnas.get(j).getContenidoFila(i);
            System.out.print(String.format("%-" + anchosMaximos[j] + "s", valor) + " ");
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
