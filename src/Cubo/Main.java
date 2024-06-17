package Cubo;

public class Main {
    public static void main(String[] args) {


        //String path = "TPI-ALGOI/datasets/";
        //String path = "datasets\\"; 
        String path = "datasets/";


        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("cuboConfig");
        cuboConfig.agregarHechos(path + "ventas.csv");
        cuboConfig.agregarDimension("fechas", path + "fechas.csv", 2);
        cuboConfig.agregarDimension("productos", path + "productos.csv", 0);
        cuboConfig.agregarDimension("puntos_venta", path + "puntos_venta.csv", 1);
        

        //--------------------------------------------------------------------------------------------//
        
        
        // CUBO
        Cubo cubo = Cubo.crearCubo(cuboConfig);
        cubo.drillDown("fechas");
        cubo.drillDown("fechas");
        cubo.drillDown("productos", 2);
        cubo.proyectar("valor_unitario", "suma");

        
        //SLICE
        //Cubo cubo2018 = cubo.slice("fechas", 4, "2018.0"); // TODO: ver tema con los integers
        //cubo2018.proyectar("valor_unitario", "suma");


        //DICE
        ConfigDice configDice = ConfigDice.crearConfigDice();
        configDice.agregarFiltro("puntos_venta", 4, "Euorpe");
        configDice.agregarFiltro("fechas", 4, "2018.0");
        Cubo cuboDice = cubo.dice("cubito", configDice);
        cuboDice.proyectar("valor_unitario", "suma");
    }
}
