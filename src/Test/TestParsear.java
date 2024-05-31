package Test;

import Tabla.Tabla;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import Cubo.Dimension;
import Cubo.Hechos;
import Tabla.Operador;
import Tabla.Proyeccion;

public class TestParsear {
    public TestParsear(){
        Dimension fechas = new Dimension("fechas", "TPI-ALGOI\\datasets\\fechas.csv", 2);
        Dimension productos = new Dimension("productos", "TPI-ALGOI\\datasets\\productos.csv", 0);
        Dimension puntos_venta = new Dimension("puntos_venta", "TPI-ALGOI\\datasets\\puntos_venta.csv", 1);
        Hechos ventas = new Hechos("TPI-ALGOI\\datasets\\ventas.csv");

        Map<Dimension, Integer> map = new HashMap<>(); // mapa {dimension : index_nivel}

        map.put(fechas, 5);
        // map.put(productos, 3);
        map.put(puntos_venta, 5);

        Tabla tablaParseada = Operador.parsear(map, ventas, 3);

        Proyeccion p = new Proyeccion(tablaParseada);

        System.out.println("Tabla Parseada:");
        p.imprimirPrimerasDiezFilas();
        p.info();
        System.out.println("--------------------------------------------------------------");
        
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada,Arrays.asList("region", "anio"), "suma");

        System.out.println("Tabla Agrupada:");
        Proyeccion p2 = new Proyeccion(tablaAgrupada);
        p2.imprimirPrimerasDiezFilas();
        p2.info();

    }    
}
