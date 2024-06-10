package Cubo;

import java.util.ArrayList;
import java.util.List;
// esto esta porq es mas conveniente para q sea cherente el "dice", aparte se crea mas facil q 3 listas.
public class ConfigDice {
    public List<String> dimensiones;
    public List<Integer> niveles;
    public List<String> valoresFiltro;
    public int largo;

    private ConfigDice(List<String> dimensiones, List<Integer> niveles, List<String> valoresFiltro){
        this.dimensiones = dimensiones;
        this.niveles = niveles;
        this.valoresFiltro = valoresFiltro;
        this.largo = valoresFiltro.size(); //podria ser cualquiera de los 3
    }
    
    public static ConfigDice crearConfigDice(List<String> dimensiones, List<Integer> niveles, List<String> valoresFiltro) {
        if (dimensiones.size() == niveles.size() && dimensiones.size() == valoresFiltro.size()) {
            return new ConfigDice(dimensiones, niveles, valoresFiltro);
        } else {
            throw new IllegalArgumentException("La cantidad de argumentos deben ser coherentes");
        }
    }

    public static ConfigDice crearConfigDice() {
        List<String> dims = new ArrayList<>();
        List<Integer> nivs = new ArrayList<>();
        List<String> valores = new ArrayList<>();
        return new ConfigDice(dims, nivs, valores);
    }

    public void agregarFiltro(String dim, Integer nivel, String valor){
        if(this.dimensiones.contains(dim))
        {
            System.err.println("ya la dimension " + dim + " ya está en el filtro");
            return;
        }
        this.dimensiones.add(dim);
        this.niveles.add(nivel);
        this.valoresFiltro.add(valor);
        this.largo++;
    }
}
