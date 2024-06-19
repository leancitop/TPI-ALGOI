package Lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV extends LectorArchivo {
    
    /**
     * Lee un archivo CSV y lo convierte en una matriz de Strings.
     * Cada fila del archivo se convierte en un arreglo de Strings y se almacena en una lista.
     * Luego, la lista se convierte en una matriz bidimensional de Strings.
     *
     * @param rutaArchivo La ruta del archivo CSV a leer.
     * @return Una matriz bidimensional de Strings que representa el contenido del archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public String[][] leerArchivo(String rutaArchivo) throws IOException {
        List<String[]> filas = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columnas = line.split(";");
                filas.add(columnas);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo: " + rutaArchivo, e);
        }

        String[][] matriz = new String[filas.size()][];
        for (int i = 0; i < filas.size(); i++) {
            matriz[i] = filas.get(i);
        }

        return matriz;
    }
}
