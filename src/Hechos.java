import java.io.IOException;
import java.util.List;

public class Hechos {
    private String nombre;
    private CSVTable tabla;
    
    public Hechos(String n, String path){
        try{
            this.nombre = n;
            this.tabla = CSVReader.readCSV(path);
            FormatoTabular formatoTabular = new FormatoTabular(tabla);
            formatoTabular.printTabularData();
            System.out.println("Se cargaron los hechos: " + nombre);
            List<String> columnas = tabla.getColumns();
            columnas.remove("");
            System.out.println("Columnas: "+columnas);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}
