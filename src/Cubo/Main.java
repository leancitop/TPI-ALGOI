package Cubo;

import Tabla.Contar;
import Tabla.Maximo;
import Tabla.Minimo;
import Tabla.Promedio;
import Tabla.Suma;

public class Main {
    public static void main(String[] args) {

        CronometroAccion cronometroMain = new CronometroAccion("Main"); // comienzo el timer para evaluar cuanto tarda la ejecución
        String path = "datasets/"; // corroborá que ande en tu directorio


        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("CuboTest");
        
        cuboConfig.agregarDimension("fechas", path + "fechas.csv", 2);
        // cuboConfig.agregarDimension("productos", path + "productos.csv", 0);
        cuboConfig.agregarDimension("puntos_venta", path + "puntos_venta.csv", 1);
        cuboConfig.agregarHechos(path + "ventas.csv");
        cuboConfig.agregarMedida(new Suma());
        cuboConfig.agregarMedida(new Promedio());
        cuboConfig.agregarMedida(new Contar());
        cuboConfig.agregarMedida(new Minimo());
        cuboConfig.agregarMedida(new Maximo());


        
        

        //--------------------------------------------------------------------------------------------//
        
        
        // CUBO
        Cubo cubo = Cubo.crearCubo(cuboConfig);
        cubo.informacion();
        cubo.drillDown("puntos_venta");
        cubo.drillDown("puntos_venta");
        cubo.drillDown("puntos_venta");
        cubo.rollUp("puntos_venta");
        cubo.proyectar("costo", "maximo"); // además de suma puede ser min, max, contar, promedio. Los valores de hechos además de costo pueden ser cantidad, valor_unitario, valor_total

        
        //SLICE
        Cubo cubo2018 = cubo.slice("fechas", 4, "2018.0");
        cubo2018.proyectar("valor_unitario", "Suma");


        //DICE
        ConfigDice configDice = ConfigDice.crearConfigDice();
        configDice.agregarFiltro("puntos_venta", 4, "Europe");
        configDice.agregarFiltro("fechas", 4, "2018.0");
        Cubo cuboDice = cubo.dice("Cubito", configDice);
        cuboDice.proyectar("valor_unitario", "minimo");

        cronometroMain.finalizar(); // finalizo el timer para evaluar cuanto tarda la ejecución
    }
}
