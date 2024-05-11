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
            String[] columnas = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            
            // Explicación paso a paso de la expresión regular:
            // ",": Coincide con una coma en la cadena.
            // "(?=": Inicio de una lookahead assertion (aserción hacia adelante). Indica que la coma solo coincidirá si está seguida por lo que está dentro del grupo de aserción.
            // "(?: ... )": Grupo no capturador. Agrupa el patrón interno sin capturar su coincidencia.
            // "[^\"]*": Coincide con cualquier cantidad de caracteres que no sean comillas dobles.
            // "\"": Coincide con una comilla doble.
            // "[^\"]*": Coincide con cualquier cantidad de caracteres que no sean comillas dobles.
            // "\"": Coincide con otra comilla doble.
            // "*": Este asterisco se aplica al grupo completo, lo que significa que puede repetirse cualquier número de veces.
            // "[^\"]*$": Coincide con cualquier cantidad de caracteres que no sean comillas dobles al final de la cadena.

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
}
