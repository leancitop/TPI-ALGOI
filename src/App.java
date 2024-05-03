import java.io.IOException;
public class App {
    public static void main(String[] args) {
        try {
            CSVTable table = CSVReader.readCSV("datasets/productos.csv");
            table.info();
            CSVRow row = table.getRow(0);
            System.out.println("First row second column: " + row.getColumn(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
