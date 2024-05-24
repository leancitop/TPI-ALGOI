import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LectorArchivos {
    public static String[][] leerCSV(String rutaArchivo) {
        List<String[]> filas = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columnas = line.split(";");
                filas.add(columnas);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String[][] matriz = new String[filas.size()][];
        for (int i = 0; i < filas.size(); i++) {
            matriz[i] = filas.get(i);
        }

        return matriz;
    }
}

