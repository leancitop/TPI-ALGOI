import java.io.IOException;
import java.util.List;

public class Dimension {
    public String nombre;
    CSVTable tabla;
    
    Dimension(String nombreDimension, String rutaArchivoCSV, String columnaId){
        nombre = nombreDimension;
        try {
            tabla = CSVReader.readCSV(rutaArchivoCSV);
            System.out.println("Se cargó la dimensión: " + nombre);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getNombre(){
        return nombre;
    }
} 