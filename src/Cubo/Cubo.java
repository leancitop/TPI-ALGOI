package Cubo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Tabla.Tabla;
import Tabla.Operador;
import Tabla.Proyeccion;

public class Cubo {
    public String nombre;
    public Map<Dimension, Integer> niveles; 
    public Tabla hechos;
    public int idMedida;

    private Cubo(String nombre, Map<Dimension, Integer> niveles, Tabla hechos){
        this.niveles = niveles;
        this.nombre = nombre;
        this.hechos = hechos;
    }

    private Cubo(Config configCubo){
        niveles = configCubo.getDimensiones();
        nombre = configCubo.getNombre();
        hechos = configCubo.getHechos();

    }

    public static Cubo crearCubo(Config configCubo){
        Cubo cubo = new Cubo(configCubo);
        return cubo;
    }

    public Cubo slice(String nombreDimension, int nivelFiltro, String valor){
        for (Dimension dim: niveles.keySet()) { // busco los ids de las fechas que cumplen con el filtro
            if(dim.getNombre() == nombreDimension){
                Tabla hechosFiltrados = Operador.filtrarHechos(
                    this.hechos, dim, valor, nivelFiltro, true
                );
                HashMap<Dimension, Integer> nivelesNuevos = new HashMap<Dimension, Integer>();
                for (Map.Entry<Dimension, Integer> nivel : this.niveles.entrySet()) {
                    if(nivel.getKey().getNombre() != nombreDimension)
                        nivelesNuevos.put(nivel.getKey(), nivel.getValue());
                }
                Cubo cuboNuevo = new Cubo("Cubo_" + valor, nivelesNuevos, hechosFiltrados);
                return cuboNuevo;
            }
        }
        throw new RuntimeException("Dimensión invalida. Ingrese un nombre que coincida con el de algina dimensión existente");
    }

    public Cubo dice(String nombreCubo, ConfigDice dice){
        Tabla hechosDice = this.hechos;
        for(int i=0; i < dice.largo ; i++){
            for(Dimension dim : niveles.keySet() ){
                if(dim.getNombre() == dice.dimensiones.get(i)){
                    hechosDice = Operador.filtrarHechos(
                        hechosDice, 
                        dim, 
                        dice.valoresFiltro.get(i), 
                        dice.niveles.get(i), 
                        false
                    );
                }
            };
        }

        if(hechosDice.getNumeroFilas() == 0)
            throw new RuntimeException("No existen hechos que cumplan con los filtros solicitados");

        Cubo cuboDice = new Cubo(nombreCubo, this.niveles, hechosDice);
        return cuboDice;
    }

    public void rollUp(String nombreDimension){
        niveles.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (dimension.getNumeroNiveles() >= indexNiveles+1){
                    niveles.replace(dimension, indexNiveles+1);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora esta fijada en el nivel "+ ((Integer) indexNiveles+1));
                }
                else {
                    System.out.println("Ya se alcanzó el máximo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    public void rollUp(String nombreDimension, Integer numeroRollUps){
        niveles.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (dimension.getNumeroNiveles() >= indexNiveles+numeroRollUps){
                    niveles.replace(dimension, indexNiveles+numeroRollUps);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora esta fijada en el nivel "+ ((Integer) indexNiveles+numeroRollUps));
                }
                else if (dimension.getNumeroNiveles() - indexNiveles - numeroRollUps <= 0) {
                    Integer numeroRollUpRestantes = Math.abs(dimension.getNumeroNiveles() - indexNiveles - numeroRollUps);
                    niveles.replace(dimension, indexNiveles+numeroRollUpRestantes);
                    System.out.println("Se hicieron " + numeroRollUpRestantes + " de los " + numeroRollUps + " roll ups");
                    System.out.println("La dimension " + dimension.getNombre() + " ahora esta fijada en el nivel "+ ((Integer) indexNiveles+numeroRollUpRestantes));
                } 
                else {
                    System.out.println("Ya se alcanzó el máximo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    public void drillDown(String nombreDimension){
        niveles.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (1 <= indexNiveles-1){
                    niveles.replace(dimension, indexNiveles-1);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora esta fijada en el nivel "+ ((Integer) indexNiveles-1));
                }
                else {
                    System.out.println("Ya se alcanzó el mínimo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    public void drillDown(String nombreDimension, Integer numeroDrillDowns){
        niveles.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (1 <= indexNiveles-numeroDrillDowns){
                    niveles.replace(dimension, indexNiveles-numeroDrillDowns);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora esta fijada en el nivel "+ ((Integer) indexNiveles-numeroDrillDowns));
                }
                else {
                    System.out.println("Ya se alcanzó el mínimo nivel para la dimensión " + dimension.getNombre());
                }
            }
        });
    }

    public void proyectar(String valorHechos, String medida){
        Tabla tablaParseada = Operador.parsear(niveles, hechos, hechos.getHeaderIndex(valorHechos));
        List<String> columnas =  new ArrayList<>(Arrays.asList(tablaParseada.getHeaders()));
        columnas.remove(valorHechos);
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada, columnas , medida);
        Proyeccion p = new Proyeccion(tablaAgrupada);
        // p.info();
        p.imprimirPrimerasDiezFilas();
    }
}
