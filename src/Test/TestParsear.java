package Test;

import Tabla.Tabla;

import java.util.HashMap;
import java.util.Map;

import Cubo.Dimension;
import Cubo.Hechos;
import Tabla.Operador;
import Tabla.Proyeccion;

public class TestParsear {
    public TestParsear(){
        Dimension fechas = new Dimension("fechas", "TPI-ALGOI\\datasets\\fechas.csv", 2);
        Hechos ventas = new Hechos("TPI-ALGOI\\datasets\\ventas.csv");

        Map<Dimension, Integer> map = new HashMap<>();

        map.put(fechas, 5);

        Tabla tablaParseada = Operador.parsear(map, ventas);

        Proyeccion p = new Proyeccion(tablaParseada);
        Proyeccion p2 = new Proyeccion(fechas.getTabla());
        Proyeccion p3 = new Proyeccion(ventas.getTabla());

        System.out.println("tabla fechas");
        p2.imprimirPrimerasDiezFilas();
        System.out.println("tabla ventas");
        p3.imprimirPrimerasDiezFilas();
        p3.info();
        System.out.println("tabla parseada");
        p.imprimirPrimerasDiezFilas();
        p.info();
    }    
}
