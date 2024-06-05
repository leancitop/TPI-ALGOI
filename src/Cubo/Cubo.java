package Cubo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Tabla.Tabla;
import Tabla.Operador;
import Tabla.Proyeccion;

public class Cubo {
    public String nombre;
    public Map<Dimension, Integer> niveles; 
    public Hechos hechos;
    public int idMedida;
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    private Cubo(Config configCubo){
        niveles = configCubo.getDimensiones();
        nombre = configCubo.getNombre();
        hechos = configCubo.getHechos();

    }

    public static Cubo crearCubo(Config configCubo){
        Cubo cubo = new Cubo(configCubo);
        return cubo;
    }

    public Cubo slice(){
        // filtra el cubo en una dimensión, con SOLO UN VALOR del nivel seleccionado. Debería devolver otro cubo
        return null;
    }

    public Cubo dice(){
        // es un slice pero con más de un valor. También debería devolver otro cubo
        return null;
    }

    void rollUp(Dimension dimension){
        // agarramos la dimension elegida y SUBIMOS su nivel de abstraccion en niveles
        // debería editar las dimensiones del cubo actual
    }

    void drillDown(){
        // agarramos la dimension elegida y BAJAMOS su nivel de abstraccion en niveles
        // debería editar las dimensiones del cubo actual
    }

    void proyectar(int indexValor, String medida){ // TODO: poder pasar varios valores de la tabla de hechos y varias medidas. (¿Metodo para elegir valores y medidas?)
        Tabla tablaParseada = Operador.parsear(niveles, hechos, indexValor);
        List<String> columnas = new ArrayList<>();
        for (Map.Entry<Dimension, Integer> entry : niveles.entrySet()) {
            Dimension dimension = entry.getKey();
            Integer nivel = entry.getValue();
            String columna = dimension.getTabla().getHeaders()[nivel];
            columnas.add(columna);
        }
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada, columnas, medida);
        Proyeccion p = new Proyeccion(tablaAgrupada);
        p.info();
        p.imprimirPrimerasDiezFilas();
    }
}
