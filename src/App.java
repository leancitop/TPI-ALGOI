public class App {
    public static void main(String[] args) {

        Cubo cubo = new Cubo("cubo");
        cubo.agregarDimension(new Dimension("productos", "datasets/productos.csv","id_producto"));
        cubo.agregarDimension(new Dimension("fechas", "datasets/fechas.csv","id_fecha"));
        cubo.agregarDimension(new Dimension("puntos_venta", "datasets/puntos_venta.csv","id_punto_venta"));

        ;

        for (Dimension dimension : cubo.getDimensiones()) {
            System.out.println(dimension.getNombre());
            }

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
