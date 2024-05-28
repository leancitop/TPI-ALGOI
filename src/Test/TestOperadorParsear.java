package Test;

import Tabla.Columna;
import Tabla.Operador;

import java.util.ArrayList;
import java.util.List;

import Cubo.Cubo;
import Cubo.Dimension;
import Cubo.Hecho;
import Lectores.LectorArchivos;
import Tabla.Tabla;

public class TestOperadorParsear {
    public static void main(String[] args) {
        Cubo cubo = new Cubo("cubo");

        String[][] productos_csv = LectorArchivos.leerCSV("datasets/productos.csv");
        Tabla tabla_productos = new Tabla(4);
        tabla_productos.cargarTabla(productos_csv);
        Dimension productos = new Dimension("productos", 0,5, tabla_productos, "id_producto");
        cubo.agregarDimension(productos);

        String[][] fechas_csv = LectorArchivos.leerCSV("datasets/fechas.csv");
        Tabla tabla_fechas = new Tabla(6);
        tabla_fechas.cargarTabla(fechas_csv);
        Dimension fechas = new Dimension("fechas", 0,5, tabla_fechas, "id_fecha");
        cubo.agregarDimension(fechas);

        String[][] puntos_venta_csv = LectorArchivos.leerCSV("datasets/puntos_venta.csv");
        Tabla tablaPuntosVenta = new Tabla(6);
        tablaPuntosVenta.cargarTabla(puntos_venta_csv);
        Dimension puntosVenta = new Dimension("puntos de venta", 0,5, tablaPuntosVenta, "id_punto_venta");
        cubo.agregarDimension(puntosVenta);

        cubo.setDimensionesProyeccion(null);

        List<List<Object>> listaDimensionesProyeccion = new ArrayList<>();
        
        listaDimensionesProyeccion.add(List.of("fechas", 1));
        listaDimensionesProyeccion.add(List.of("productos", 2));

        cubo.setDimensionesProyeccion(listaDimensionesProyeccion);

        List<List<Object>> listaHechosDimension = new ArrayList<>();
        
        listaHechosDimension.add(List.of("valor_total", "suma"));
        listaHechosDimension.add(List.of("costo", "suma"));

        cubo.setHechosProyeccion(listaHechosDimension);

        cubo.setHecho(new Hecho("datasets/ventas.csv", 7));

        Operador.parsear(cubo.listaDimensiones, cubo.dimensionesProyeccion, cubo.tablaHecho, cubo.hechosProyeccion);
    }
}
