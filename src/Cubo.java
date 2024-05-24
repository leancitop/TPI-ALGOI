import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cubo {
    private String nombre;
    private List<Dimension> dimensionesCargadas;
    private List<Integer> dimensionesElegidas;
    private Hecho hechosCargados;
    private List<Integer> valores;
    private List<Medida> medidas; 


    private Cubo(String nombre){
        this.nombre = nombre;
        this.dimensionesCargadas = new ArrayList<>();
        this.dimensionesElegidas = new ArrayList<>();
    }

    public static Cubo crearCubo(String nombre){
        Cubo cubo = new Cubo(nombre);
        return cubo;
    }

    public void agregarDimensiones(List<Dimension> dimensiones){
        for (Dimension dimension : dimensiones) {
            this.dimensionesCargadas.add(dimension);
        }
    }

    public void agregarHecho(Hecho hechos) {
        try {
            this.hechosCargados = hechos;
            List<String> lista = hechos.getValores();
            for (Dimension dimension : dimensionesCargadas) {
                if (!lista.contains(dimension.getClaveForanea())) {
                    throw new Exception("Las dimensiones no coinciden con la tabla de hechos.");
                }
            }
            this.verResumen();
        } catch (Exception e) {
            System.err.println("Error al agregar hecho: " + e.getMessage());
        }
    }

    public void elegirDimensiones(List<Integer> dimensiones){ //Se elige dimensiones que vamos a mostrar de todas las cargadas.
        this.dimensionesElegidas = dimensiones;
    }

    public void elegirValores(List<Integer> valores){ //Valores que tenga la tabla de hechos. Se le puede pasar solo un valor
        this.valores = valores;
    } 

    public void elegirMedidas(List<Medida> medidas){ 
        this.medidas = medidas;
    }

    public void rollUp(){

    }

    public void drillDown(){

    }

    public void slice(){

    }

    public void dice(){

    }

    public void verResumen(){
        List<String> nombreDimensiones = new ArrayList<>();
        List<List<String>> nivelesDimensiones = new ArrayList<>();
        List<String> valoresHecho = hechosCargados.getValores();
        for (Dimension dimension : dimensionesCargadas) {
            nombreDimensiones.add(dimension.getNombre());
            nivelesDimensiones.add(dimension.getNiveles());
            valoresHecho.remove(dimension.getClaveForanea());
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

    public void proyectar(){

    }
}
