import java.io.IOException;
import java.util.List;

public class Dimension {
    String nombre;
    CSVTable tabla;
    
    Dimension(String nombreDimension, String rutaArchivoCSV, String columnaId){
        nombre = nombreDimension;
        try {
            tabla = CSVReader.readCSV(rutaArchivoCSV);
            FormatoTabular formatoTabular = new FormatoTabular(tabla);
            formatoTabular.printTabularData();
            System.out.println("Se cargó la dimensión: " + nombre);
            List<String> columnas = tabla.getColumns();
            columnas.remove(columnaId);
            columnas.remove("");
            System.out.println("Elementos: "+columnas);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
} 