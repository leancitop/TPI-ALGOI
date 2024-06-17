package Cubo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Tabla.Tabla;
import Tabla.Operador;
import Tabla.Proyeccion;

/**
 * Clase que representa un cubo para análisis multidimensional de datos.
 */
public class Cubo {
    private String nombre;
    private Map<Dimension, Integer> dimensiones;
    private Tabla hechos;

    /**
     * Constructor privado de Cubo.
     * @param nombre Nombre del cubo.
     * @param dimensiones Dimensiones del cubo.
     * @param hechos Tabla de hechos del cubo.
     */
    private Cubo(String nombre, Map<Dimension, Integer> dimensiones, Tabla hechos){
        this.dimensiones = dimensiones;
        this.nombre = nombre;
        this.hechos = hechos;
    }

    /**
     * Constructor privado que utiliza un objeto Config para crear un nuevo cubo.
     * @param configCubo Configuración del cubo.
     */
    private Cubo(Config configCubo){
        dimensiones = configCubo.getDimensiones();
        nombre = configCubo.getNombre();
        hechos = configCubo.getHechos();
    }

    /**
     * Método estático para crear un nuevo cubo a partir de una configuración.
     * @param configCubo Configuración del cubo.
     * @return Nuevo objeto Cubo creado.
     */
    public static Cubo crearCubo(Config configCubo){
        Cubo cubo = new Cubo(configCubo);
        return cubo;
    }

    /**
     * Realiza un slice (filtrado) en el cubo según una dimensión y un valor específico.
     * @param nombreDimension Nombre de la dimensión a filtrar.
     * @param nivelFiltro Nivel de filtro en la dimensión.
     * @param valor Valor a filtrar en el nivel.
     * @return Nuevo cubo con los datos filtrados.
     */
    public Cubo slice(String nombreDimension, int nivelFiltro, String valor){
        for (Dimension dim: dimensiones.keySet()) {
            if(dim.getNombre() == nombreDimension){
                Tabla hechosFiltrados = Operador.filtrarHechos(
                    this.hechos, dim, valor, nivelFiltro, true
                );
                HashMap<Dimension, Integer> nivelesNuevos = new HashMap<Dimension, Integer>();
                for (Map.Entry<Dimension, Integer> nivel : this.dimensiones.entrySet()) {
                    if(nivel.getKey().getNombre() != nombreDimension)
                        nivelesNuevos.put(nivel.getKey(), nivel.getValue());
                }
                Cubo cuboNuevo = new Cubo("Cubo_" + valor, nivelesNuevos, hechosFiltrados);
                return cuboNuevo;
            }
            throw new RuntimeException("Dimensión invalida. Ingrese un nombre que coincida con el de algina dimensión existente");
        }
        System.err.println("No se pudo crear el Cubo, verificar parámetros.");
        return null;
    }

    /**
     * Realiza un dice (filtrado múltiple) en el cubo según las especificaciones dadas.
     * @param nombreCubo Nombre del nuevo cubo resultante.
     * @param dice Configuración para el filtrado.
     * @return Nuevo cubo con los datos filtrados.
     */
    public Cubo dice(String nombreCubo, ConfigDice dice){
        Tabla hechosDice = this.hechos;
        for(int i=0; i < dice.largo ; i++){
            for(Dimension dim : dimensiones.keySet() ){
                if(dim.getNombre() == dice.dimensiones.get(i)){
                    hechosDice = Operador.filtrarHechos(
                        hechosDice, 
                        dim, 
                        dice.valoresFiltro.get(i), 
                        dice.niveles.get(i), 
                        false
                    );
                }
            }
        }
        if(hechosDice.getNumeroFilas() == 0)
            throw new RuntimeException("No existen hechos que cumplan con los filtros solicitados");

        Cubo cuboDice = new Cubo(nombreCubo, this.dimensiones, hechosDice);
        return cuboDice;
    }

    /**
     * Realiza un roll up en una dimensión específica. Aumenta el nivel seleccionado una vez en esa dimension
     * @param nombreDimension Nombre de la dimensión para hacer roll up.
     */
    public void rollUp(String nombreDimension){
        dimensiones.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (dimension.getNumeroNiveles() >= indexNiveles+1){
                    dimensiones.replace(dimension, indexNiveles+1);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles+1));
                }
                else {
                    throw new IllegalStateException("Ya se alcanzó el máximo nivel para la dimensión " + dimension.getNombre()); 
                }
            }
        });
    }

    /**
     * Realiza multiples roll up en una dimensión específica. Aumenta el nivel seleccionado en esa dimension multiples veces.
     * @param nombreDimension Nombre de la dimensión para hacer roll up.
     * @param numeroRollUps Número de roll ups a realizar.
     */
    public void rollUp(String nombreDimension, Integer numeroRollUps){
        dimensiones.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (dimension.getNumeroNiveles() >= indexNiveles+numeroRollUps){
                    dimensiones.replace(dimension, indexNiveles+numeroRollUps);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles+numeroRollUps));
                }
                else if (dimension.getNumeroNiveles() - indexNiveles - numeroRollUps <= 0) {
                    Integer numeroRollUpRestantes = Math.abs(dimension.getNumeroNiveles() - indexNiveles - numeroRollUps);
                    dimensiones.replace(dimension, indexNiveles+numeroRollUpRestantes);
                    System.out.println("Se hicieron " + numeroRollUpRestantes + " de los " + numeroRollUps + " roll ups");
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles+numeroRollUpRestantes));
                } 
                else {
                    throw new IllegalStateException("Ya se alcanzó el máximo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    /**
     * Realiza un drill down en una dimensión específica. Reduce el nivel seleccionado una vez en esa dimension.
     * @param nombreDimension Nombre de la dimensión para hacer drill down.
     */
    public void drillDown(String nombreDimension){
        dimensiones.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (1 <= indexNiveles-1){
                    dimensiones.replace(dimension, indexNiveles-1);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles-1));
                }
                else {
                    throw new IllegalStateException("Ya se alcanzó el mínimo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    /**
     * Realiza multiples drill down en una dimensión específica. Reduce el nivel seleccionado en esa dimension multiples veces.
     * @param nombreDimension Nombre de la dimensión para hacer drill down.
     * @param numeroDrillDowns Número de drill downs a realizar.
     */
    public void drillDown(String nombreDimension, Integer numeroDrillDowns){
        dimensiones.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (1 <= indexNiveles-numeroDrillDowns){
                    dimensiones.replace(dimension, indexNiveles-numeroDrillDowns);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles-numeroDrillDowns));
                }
                else {
                    throw new IllegalStateException("Ya se alcanzó el mínimo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    /**
     * Realiza una proyección de los datos del cubo con los niveles seleccionados, valor y medida elegidos.
     * @param valorHechos Valor de los hechos a utilizar en la proyección.
     * @param medida Medida a aplicar en la proyección.
     */
    public void proyectar(String valorHechos, String medida){
        Tabla tablaParseada = Operador.parsear(dimensiones, hechos, hechos.getHeaderIndex(valorHechos));
        List<String> columnas =  new ArrayList<>(Arrays.asList(tablaParseada.getHeaders()));
        columnas.remove(valorHechos);
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada, columnas , medida);
        Proyeccion p = new Proyeccion(tablaAgrupada);
        // p.info();
        p.imprimirNfilas(15);
        System.out.println("");
    }
}
