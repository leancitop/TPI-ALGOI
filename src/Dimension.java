import java.io.IOException;
import java.util.List;

public class Dimension {
    String nombre;
    CSVTable tabla;

    Dimension(String nombreDimension, String rutaArchivoCSV, String columnaId){
        nombre = nombreDimension;
        try {
            tabla = CSVReader.readCSV(rutaArchivoCSV);
            FormatoTabular tablaTabular = new FormatoTabular(tabla);
            tablaTabular.printTabularData();
            System.out.println("");
            System.out.println("Se cargó la dimensión: " + nombre);
            List<String> elementos = tabla.getColumns();
            elementos.remove(columnaId);
            elementos.remove("");
            System.out.println("Id: " + columnaId);
            System.out.println("Elementos: " + elementos);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}   
