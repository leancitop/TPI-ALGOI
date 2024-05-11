public class Dimension {
    public String nombre;
    Tabla tabla;
    
    Dimension(String nombreDimension, String rutaArchivoCSV, String columnaId){
        nombre = nombreDimension;
            tabla = LectorArchivos.leerCSV(rutaArchivoCSV);
            System.out.println("Se cargó la dimensión: " + nombre);
    }

    String getNombre(){
        return nombre;
    }

    void imprimirTabla(){
        FormatoTabular.printTabularData(tabla);
    }
} 