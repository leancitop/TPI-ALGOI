import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    public static List<String[]> crear_from_csv(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir la línea en campos usando coma como delimitador :D
                String[] fields = line.split(",");
                data.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        String filePath = "archivo.csv";
        List<String[]> data = crear_from_csv(filePath);

        // Imprimir los datos leídos del CSV
        for (String[] row : data) {
            for (String field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
}