import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cubo {
    private String nombre;
    private List<Dimension> dimensionesCargadas;
    private List<Integer> dimensionesElegidas;
    private Hechos hechosCargados;
    private List<Integer> valores;
    private List<Medida> medidas; 


    private Cubo(String nombre){
        this.nombre = nombre;
        List<Dimension> dimensiones = new ArrayList<>();
        List<Dimension> dimensionesElegidas = new ArrayList<>();
    }

    public void crearCubo(String nombre){
        Cubo cubo = new Cubo(nombre);
    }

    void agregarDimensiones(List<Dimension> dimensiones){
        for (Dimension dimension : dimensiones) {
            this.dimensionesCargadas.add(dimension);
        }
    }

    public void agregarHecho(Hechos hechos){ // Dimensiones debe coincidir con ID en hechos. Todo cargado, mostramos resumen
        this.hechosCargados = hechos;        
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

    public void proyectar(){

    }
}
