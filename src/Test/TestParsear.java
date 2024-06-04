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
        Dimension meses = new Dimension("fechas", "TPI-ALGOI\\datasets\\fechas.csv", 2);
        Dimension años = new Dimension("fechas", "TPI-ALGOI\\datasets\\fechas.csv", 2);
        Dimension puntos_venta = new Dimension("puntos_venta", "TPI-ALGOI\\datasets\\puntos_venta.csv", 1);
        Hechos ventas = new Hechos("TPI-ALGOI\\datasets\\ventas.csv");

        Map<Dimension, Integer> map = new HashMap<>(); // mapa {dimension : index_nivel}

        map.put(meses, 3);
        map.put(años, 5);
        map.put(puntos_venta, 5);

        Tabla tablaParseada = Operador.parsear(map, ventas, 3);

        Proyeccion p = new Proyeccion(tablaParseada);

        System.out.println("Tabla Parseada:");
        p.imprimirPrimerasDiezFilas();
        p.info();
        System.out.println("--------------------------------------------------------------");
        
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada,Arrays.asList("anio","mes", "region"), "suma");

        System.out.println("Tabla Agrupada:");
        Proyeccion p2 = new Proyeccion(tablaAgrupada);
        p2.imprimirPrimerasDiezFilas();
        p2.info();

    }    
}
