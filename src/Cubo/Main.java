package Cubo;

import Test.TestParsear;

public class Main {
    public static void main(String[] args) {
        //TestAgrupar test = new TestAgrupar(); // devuelve una tabla, pero no está agrupada correctamente
        // System.out.println("------------------------------");
        // TestParsear test2 = new TestParsear(); // devuelve una tabla vacía, solo están los headers

        
        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("cuboConfig");
        cuboConfig.agregarDimension("fechas", "C:\\Users\\ezeco\\Desktop\\TPI-ALGOI\\datasets\\fechas.csv", 2);
        // cuboConfig.agregarDimension("productos", "C:\\Users\\ezeco\\Desktop\\TPI-ALGOI\\datasets\\productos.csv", 0);
        cuboConfig.agregarDimension("puntos_venta", "C:\\Users\\ezeco\\Desktop\\TPI-ALGOI\\datasets\\puntos_venta.csv", 1);
        cuboConfig.agregarHechos("C:\\Users\\ezeco\\Desktop\\TPI-ALGOI\\datasets\\ventas.csv");
        //--------------------------------------------------------------------------------------------//
        

        // CUBO
        Cubo cubo = Cubo.crearCubo(cuboConfig);
        cubo.proyectar(3, "suma");
    }
}
