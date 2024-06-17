package Cubo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que define la configuración de un filtro para el metodo Dice en un objeto Cubo.
 */
public class ConfigDice {
    public List<String> dimensiones;
    public List<Integer> niveles;
    public List<String> valoresFiltro;
    public int largo;

    /**
     * Constructor privado de ConfigDice.
     * @param dimensiones Lista de dimensiones a filtrar.
     * @param niveles Lista de niveles asociados a cada dimensión.
     * @param valoresFiltro Lista de valores de filtro para cada dimensión.
     */
    private ConfigDice(List<String> dimensiones, List<Integer> niveles, List<String> valoresFiltro){
        this.dimensiones = dimensiones;
        this.niveles = niveles;
        this.valoresFiltro = valoresFiltro;
        this.largo = valoresFiltro.size(); 
    }
    
    /**
     * Crea una instancia de ConfigDice con las listas proporcionadas.
     * @param dimensiones Lista de dimensiones a filtrar.
     * @param niveles Lista de niveles asociados a cada dimensión.
     * @param valoresFiltro Lista de valores de filtro para cada dimensión.
     * @return Instancia de ConfigDice creada.
     * @throws IllegalArgumentException Si las listas no tienen la misma cantidad de elementos.
     */
    public static ConfigDice crearConfigDice(List<String> dimensiones, List<Integer> niveles, List<String> valoresFiltro) {
        if (dimensiones.size() == niveles.size() && dimensiones.size() == valoresFiltro.size()) {
            return new ConfigDice(dimensiones, niveles, valoresFiltro);
        } else {
            throw new IllegalArgumentException("La cantidad de argumentos deben ser coherentes");
        }
    }

    /**
     * Crea una instancia vacía de ConfigDice.
     * @return Instancia de ConfigDice sin filtros configurados.
     */
    public static ConfigDice crearConfigDice() {
        List<String> dims = new ArrayList<>();
        List<Integer> nivs = new ArrayList<>();
        List<String> valores = new ArrayList<>();
        return new ConfigDice(dims, nivs, valores);
    }

    /**
     * Agrega un filtro a la configuración.
     * @param dim Nombre de la dimensión a filtrar.
     * @param nivel Nivel asociado al filtro.
     * @param valor Valor a filtrar en el nivel.
     */
    public void agregarFiltro(String dim, Integer nivel, String valor){
        if(this.dimensiones.contains(dim))
        {
            System.err.println("La dimensión " + dim + " ya está en el filtro");
            return;
        }
        this.dimensiones.add(dim);
        this.niveles.add(nivel);
        this.valoresFiltro.add(valor);
        this.largo++;
    }
}