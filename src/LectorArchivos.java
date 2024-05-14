import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LectorArchivos {
    public static Tabla leerCSV(String rutaArchivo) {
        try{
        List<FilaTabla> filas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columnas = line.split(";");
            List<String> listaColumnas = new ArrayList<>();
            for (String column : columnas) {
                listaColumnas.add(column);
            }
            filas.add(new FilaTabla(listaColumnas));
            }
        reader.close();
        return new Tabla(filas);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Dimension cargarDimensionDesdeCSV(String nombreDim, String rutaArchivoCSV, int columnaDimension) {
        Dimension dimension = new Dimension(nombreDim);

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivoCSV))) {
            String line;
            // Saltamos la primera línea si es un encabezado
            // Si el archivo tiene encabezado, de lo contrario omite este paso
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] columnas = line.split(",");
                if (columnas.length > columnaDimension) {
                    String valorDimension = columnas[columnaDimension];
                    String idFila = columnas[0]; // Suponiendo que el ID de la fila es la primera columna
                    dimension.agregarValor(valorDimension, idFila);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dimension;
    }
}
