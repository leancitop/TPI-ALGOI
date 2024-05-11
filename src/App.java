import java.util.Map;

public class App {
    public static void main(String[] args) {

        Cubo cubo = new Cubo("cubo");
        cubo.agregarDimension(new Dimension("productos", "datasets/productos.csv","id_producto"));
        cubo.agregarDimension(new Dimension("fechas", "datasets/fechas.csv","id_fecha"));
        cubo.agregarDimension(new Dimension("puntos_venta", "datasets/puntos_venta.csv","id_punto_venta"));

        // for (Dimension dimension : cubo.getDimensiones()) {
        //     System.out.println(dimension.getNombre());
        //     }
        
        for (Map.Entry<String, Dimension> dimension : cubo.getDimensiones().entrySet()) {
            String nombreDimension = dimension.getKey();
            System.out.println("\n"+nombreDimension+"\n");
            dimension.getValue().imprimirTabla();
        }
        

        // Hechos ventas = new Hechos("ventas", "/home/tareas/TPI-ALGOI/datasets/ventas.csv");
    }
}
