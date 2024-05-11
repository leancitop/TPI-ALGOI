import java.util.List;

public class Hechos {
    private String nombre;
    private Tabla tabla;
    
    public Hechos(String n, String path){
            this.nombre = n;
            this.tabla = LectorArchivos.leerCSV(path);
            FormatoTabular.printTabularData(tabla);
            System.out.println("Se cargaron los hechos: " + nombre);
            List<String> columnas = tabla.getColumnas();
            columnas.remove("");
            System.out.println("Columnas: "+columnas);
    }
}
