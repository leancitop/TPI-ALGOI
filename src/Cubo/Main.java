package Cubo;

import Test.TestParsear;

public class Main {
    public static void main(String[] args) {
        //TestAgrupar test = new TestAgrupar(); // devuelve una tabla, pero no está agrupada correctamente
        // System.out.println("------------------------------");
        // TestParsear test2 = new TestParsear(); // devuelve una tabla vacía, solo están los headers

        //agreguen los q le sirven y vamos alternando
        String path = "TPI-ALGOI/datasets/";
        //String path = "datasets\\";

        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("cuboConfig");
        cuboConfig.agregarDimension("fechas", path + "fechas.csv", 2);
        cuboConfig.agregarDimension("productos", path + "productos.csv", 0);
        cuboConfig.agregarDimension("puntos_venta", path + "puntos_venta.csv", 1);
        cuboConfig.agregarHechos(path + "ventas.csv");

        //--------------------------------------------------------------------------------------------//
        
        
        // CUBO
        Cubo cubo = Cubo.crearCubo(cuboConfig);
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.drillDown("productos", 2);
        //cubo.proyectar("valor_unitario", "suma");

        //SLICE
        //Cubo cubo2018 = cubo.slice("fechas", 4, "2018.0"); // TODO: ver tema con los integers
        //cubo2018.proyectar("valor_unitario", "suma");
        //DICE
        ConfigDice configDice = ConfigDice.crearConfigDice();
        configDice.agregarFiltro("fechas", 4, "2018.0");
        configDice.agregarFiltro("puntos_venta", 4, "Europe");
        Cubo cuboDice = cubo.dice("cubito", configDice);
        cuboDice.proyectar("valor_unitario", "suma");
    }
}
