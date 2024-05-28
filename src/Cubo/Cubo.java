package Cubo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cubo {
    public String nombre;
    public Map<String, Dimension> listaDimensiones;
    public Hecho tablaHecho;
    public List<List<Object>> dimensionesProyeccion;
    public List<List<Object>> hechosProyeccion;
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    public Cubo(String nombreCubo){
        nombre = nombreCubo;
        listaDimensiones = new HashMap<String, Dimension>();
    }

    public void agregarDimension(Dimension dimension){
        listaDimensiones.put(dimension.getNombre(), dimension);
    }

    public void verResumen(){
        List<String> nombreDimensiones = new ArrayList<>();
        List<List<String>> nivelesDimensiones = new ArrayList<>();
        List<String> valoresHecho = tablaHecho.getValores();
        for (Map.Entry<String, Dimension> dimension : listaDimensiones.entrySet()) {
            Dimension dim = dimension.getValue();
            nombreDimensiones.add(dim.getNombre());
            nivelesDimensiones.add(dim.getNiveles());
            valoresHecho.remove(dim.getClaveForanea());
            }
        // Imprimir tabla
        int maxLongitudNiveles = 0;
        for (List<String> niveles : nivelesDimensiones) {
            maxLongitudNiveles = Math.max(maxLongitudNiveles, niveles.size());
        }

        System.out.printf("\u001B[1m%-20s\u001B[0m", "Dimensión"); // Inicia negrita
        for (int i = 0; i < maxLongitudNiveles; i++) {
            if (i == 0) {
                System.out.printf("\u001B[1m%-20s\u001B[0m", "Niveles"); // Inicia negrita
            } else {
                System.out.printf("%-20s", "");
            }
        }
        System.out.printf("\u001B[1m%-20s%n\u001B[0m", "Valores"); // Inicia negrita

        for (int i = 0; i <= maxLongitudNiveles; i++) {
            if (i == 0) {
                System.out.print("-----------------------------------");
            } else {
                System.out.print("--------------------");
            }
        }
        System.out.println();

        for (int i = 0; i < Math.max(nombreDimensiones.size(), valoresHecho.size()); i++) {
            if (i < nombreDimensiones.size()) {
                System.out.printf("\u001B[1m%-20s\u001B[0m", nombreDimensiones.get(i));
                List<String> niveles = nivelesDimensiones.get(i);
                for (int j = 0; j < maxLongitudNiveles; j++) {
                    String nivel = (j < niveles.size()) ? niveles.get(j) : "";
                    System.out.printf("%-20s", nivel);
                }
            } else {
                for (int j = 0; j < maxLongitudNiveles + 1; j++) {
                    System.out.printf("%-20s", "");
                }
            }
            if (i < valoresHecho.size()) {
                System.out.printf("%-20s", valoresHecho.get(i));
            }
            System.out.println();
        }
    }


    public void setHecho(Hecho hecho) {
        try {
            this.tablaHecho = hecho;
            List<String> lista = hecho.getValores();
            for (Map.Entry<String, Dimension> dimension : listaDimensiones.entrySet()) {
                Dimension dim = dimension.getValue();
                if (!lista.contains(dim.getClaveForanea())) {
                throw new Exception("Las dimensiones no coinciden con la tabla de hechos.");
        }
            }
            this.verResumen();
        } catch (Exception e) {
            System.err.println("Error al agregar hecho: " + e.getMessage());
        }
    }    

    public void setDimensionesProyeccion(List<List<Object>> listaDimensionesProyeccion){
        // seteamos las dimensiones y los niveles en las que queramos visualizar
        dimensionesProyeccion = listaDimensionesProyeccion;
    }
    
    public void setHechosProyeccion(List<List<Object>> listaHechosProyeccion){
        // elegimos los valores y las medidas con las que los queramos visualizar
        hechosProyeccion = listaHechosProyeccion;
    }

    void slice(){
        // seteamos un objeto slice que se va a usar en el metodo proyectar
    }

    void dice(){
        // seteamos un objeto slice que se va a usar en el metodo proyectar
    }

    void rollUp(){
        // agarramos la dimension elegida y modificamos su nivel en dimensionesProyeccion
    }

    void drillDown(){
        // agarramos la dimension elegida y modificamos su nivel en dimensionesProyeccion
    }

    void proyectar(){
        // Tabla tabla = new(Tabla)
        // tabla = tabla.parsear(listaDimensiones, tablaHecho, dimensionesProyeccion, hechosProyeccion)
        // tabla = tabla.agrupar()
        // si diceProyeccion o sliceProyeccion:
        //      por cada filtro:
        //          tabla = tabla.filtrar(filtro)
        // si sliceProyeccion:
        //      tabla = tabla.removerDimension(sliceProyeccion)
        // tabla = tabla.sumarizar()
        // tabla.visualizar()
    }
}
