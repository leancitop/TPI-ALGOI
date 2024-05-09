import java.io.IOException;
public class App {
    public static void main(String[] args) {
        Dimension productos = new Dimension("productos", "datasets/productos.csv","id_producto");

        // try {
        //     CSVTable tablaProductos = CSVReader.readCSV("datasets/productos.csv");
        //     tablaProductos.info();
        //     CSVTable tablaVentas = CSVReader.readCSV("datasets/productos.csv");
        //     tablaVentas.info();
        //     System.out.println("hola");
        //     System.out.println("hola3");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
