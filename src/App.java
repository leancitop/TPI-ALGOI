import java.io.IOException;
public class App {
    public static void main(String[] args) {
        try {
            CSVTable table = CSVReader.readCSV("datasets/productos.csv");
            table.info();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
