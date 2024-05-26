import java.util.ArrayList;
import java.util.List;

import Lectores.LectorArchivos;
import Tabla.Tabla;

public class Main {
    public static void main(String[] args) {
        Cubo cubo = new Cubo("cubo");

        String[][] productos_csv = LectorArchivos.leerCSV("datasets/productos.csv");
        Tabla tabla_productos = new Tabla(4);
        tabla_productos.cargarTabla(productos_csv);
        Dimension productos = new Dimension("productos", 0,5, tabla_productos, "id_producto");

        String[][] fechas_csv = LectorArchivos.leerCSV("datasets/fechas.csv");
        Tabla tabla_fechas = new Tabla(6);
        tabla_fechas.cargarTabla(fechas_csv);
        Dimension fechas = new Dimension("fechas", 0,5, tabla_fechas, "id_fecha");

        cubo.agregarDimension(productos);
        cubo.agregarDimension(fechas);

        cubo.setDimensionesProyeccion(null);

        List<List<Object>> lista = new ArrayList<>();
        
        lista.add(List.of("fechas", 1));
        lista.add(List.of("productos", 2));

        cubo.setDimensionesProyeccion(lista);

        Operador.parsear(cubo.listaDimensiones, cubo.dimensionesProyeccion);
    }
}
