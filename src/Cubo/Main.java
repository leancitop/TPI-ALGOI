package Cubo;

import Test.TestParsear;

public class Main {
    public static void main(String[] args) {
        //TestAgrupar test = new TestAgrupar(); // devuelve una tabla, pero no está agrupada correctamente
        // System.out.println("------------------------------");
        // TestParsear test2 = new TestParsear(); // devuelve una tabla vacía, solo están los headers

        
        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("cuboConfig");
        cuboConfig.agregarDimension("fechas", "datasets\\fechas.csv", 2);
        cuboConfig.agregarDimension("productos", "datasets\\productos.csv", 0);
        cuboConfig.agregarDimension("puntos_venta", "datasets\\puntos_venta.csv", 1);
        cuboConfig.agregarHechos("datasets\\ventas.csv");

        // coggi usa paths relativos no absolutos que no me corre sino down, saludos
        //--------------------------------------------------------------------------------------------//
        
        
        // CUBO
        Cubo cubo = Cubo.crearCubo(cuboConfig);
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.rollUp("fechas");
        cubo.rollUp("fechas",2);
        cubo.rollUp("fechas",2);
        //cubo.proyectar(3, "suma");
    }
}
