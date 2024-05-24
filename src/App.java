import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        System.out.println("patience.");

        // DIMENSIONES
        //Productos
        String[][] productos_csv = LectorArchivos.leerCSV("datasets/productos.csv");
        Tabla tabla_productos = new Tabla(4);
        tabla_productos.cargarTabla(productos_csv);
        Dimension productos = new Dimension("productos", 0,5, tabla_productos, "id_producto");

        System.out.println(productos.getNiveles());

        // Puntos de venta
        String[][] puntos_venta_csv = LectorArchivos.leerCSV("datasets/puntos_venta.csv");
        Tabla tabla_puntos_venta = new Tabla(6);
        tabla_puntos_venta.cargarTabla(puntos_venta_csv);
        Dimension puntos_venta = new Dimension("puntos_venta", 0,5, tabla_puntos_venta, "id_punto_venta");

        System.out.println(puntos_venta.getNiveles());

        // Fechas
        String[][] fechas_csv = LectorArchivos.leerCSV("datasets/fechas.csv");
        Tabla tabla_fechas = new Tabla(6);
        tabla_fechas.cargarTabla(fechas_csv);
        Dimension fechas = new Dimension("fechas", 0,5, tabla_fechas, "id_fecha");

        System.out.println(fechas.getNiveles());
        // TODO: Validar fechas_csv (Tipo String[][])
        // validar(matriz) # cols a validar numericas es config del cubo
        // Crear objeto Tabla a partir de String[][]

        // HECHOS
        Hecho hechos = new Hecho("datasets/ventas.csv", 7);

        // CUBO
        Cubo cubo1 = Cubo.crearCubo("Cubo1");
        List<Dimension> listaDimensiones = List.of(productos, fechas, puntos_venta);
        cubo1.agregarDimensiones(listaDimensiones);
        cubo1.agregarHecho(hechos);

        






        fechas.cargarMapaDimension();

        Map<String, List<String>> hashMap = fechas.getValoresIdD();

        // for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
        //     System.out.println(entry.getKey() + " : ");
        //     List<String> values = entry.getValue();
        //     int count = Math.min(10, values.size());
        //     for (int i = 0; i < count; i++) {
        //         System.out.println("\t" + values.get(i));
        //     }
        // }

        
        

        
    }
}
