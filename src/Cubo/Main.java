package Cubo;
import Tabla.Medidas.Contar;
import Tabla.Medidas.Maximo;
import Tabla.Medidas.Minimo;
import Tabla.Medidas.Promedio;
import Tabla.Medidas.Suma;

public class Main {
    public static void main(String[] args) {

        CronometroAccion cronometroMain = new CronometroAccion("Main"); // comienzo el timer para evaluar cuanto tarda la ejecución
        String path = "datasets/"; // corroborá que ande en tu directorio


        // CONFIGURACION CUBO
        Config cuboConfig = Config.crearConfigCubo("CuboTest");
        
        cuboConfig.agregarDimension("fechas", path + "fechas.csv", 2);
        cuboConfig.agregarDimension("productos", path + "productos.csv", 0);
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
        cubo.proyectar("puntos_venta", "fechas", "costo", "maximo"); // además de suma puede ser min, max, contar, promedio. Los valores de hechos además de costo pueden ser cantidad, valor_unitario, valor_total

        
        //SLICE
        Cubo cubo2018 = cubo.slice("fechas", "2018.0, 3.0");
        cubo2018.drillDown("fechas");
        cubo2018.proyectar("puntos_venta", "fechas", "valor_unitario", "Suma");


        //DICE
        ConfigDice configDice = ConfigDice.crearConfigDice();
        configDice.agregarFiltro("puntos_venta", 4, "Europe");
        configDice.agregarFiltro("fechas", 4, "2018.0");
        Cubo cuboDice = cubo.dice("Cubito", configDice);
        cuboDice.proyectar("fechas", "puntos_venta","valor_unitario", "suma");

        cronometroMain.finalizar(); // finalizo el timer para evaluar cuanto tarda la ejecución
    }
}
