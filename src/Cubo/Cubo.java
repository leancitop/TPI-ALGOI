package Cubo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Tabla.Tabla;
import Tabla.Columna;
import Tabla.ColumnaNumerica;
import Tabla.ColumnaString;
import Tabla.Operador;
import Tabla.Proyeccion;

public class Cubo {
    public String nombre;
    public Map<Dimension, Integer> niveles; 
    public Hechos hechos;
    public int idMedida;
    // sliceProyeccion - objeto que se setea en slice()
    // diceProyeccion - objeto que se setea en dice()

    private Cubo(String nombre, Map<Dimension, Integer> niveles, Hechos hechos){
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
        List<String> ids_dim = new ArrayList<String>();
        int col_fk = 0;
        for (Dimension dim: niveles.keySet()) { // busco los ids de las fechas que cumplen con el filtro
            if(dim.getNombre() == nombreDimension){
                Tabla dimTabla = dim.getTabla();
                List<String> filtros = new ArrayList<String>(); // paso necesario por como es filtro
                filtros.add(valor);
                List<Integer> indexesDim = Operador.filtrar(
                    dimTabla.getColumnas().get(nivelFiltro + 1),
                    filtros, 
                    Operador.TiposFiltros.IGUAL
                );
                ColumnaNumerica columnaIds = (ColumnaNumerica) dimTabla.getColumnas().get(0);
                for (Integer index : indexesDim) {
                    ids_dim.add(columnaIds.getContenidoFila(index).toString());
                }
                col_fk = dim.getClaveForanea();
            }
        }

        Tabla tabla_hechos = this.hechos.getTabla();
        List<Integer> indexesHechos = Operador.filtrar(
            tabla_hechos.getColumnas().get(col_fk), 
            ids_dim, 
            Operador.TiposFiltros.IGUAL
        ); 

        Tabla tablaHechosFiltrada = new Tabla();
        int i = 0;
        for(Columna<?> col : tabla_hechos.getColumnas()){
            i++;
            if(i == col_fk) continue;  //como se elimina la dimension utilizada salteo la columna con la FK de la dim

            String nombreColOriginal = col.getNombre();
            List<?> contenidoColOriginal = col.getDatos();
            //el try-catch es por el parseo de las columnas, si hay un método mejor cambienlo 
            try{
                ColumnaNumerica colNueva = new ColumnaNumerica(nombreColOriginal);
                for(Integer index : indexesHechos){
                    var dato = Double.parseDouble(
                        contenidoColOriginal.get(index).toString()
                        );
                    colNueva.agregarDato(dato);
                }
                tablaHechosFiltrada.agregarColumna(colNueva);
            }catch(Exception e){
                ColumnaString colNueva = new ColumnaString(nombreColOriginal);
                for(Integer index : indexesHechos){
                    var dato = contenidoColOriginal.get(index).toString();
                    colNueva.agregarDato(dato);
                }
                tablaHechosFiltrada.agregarColumna(colNueva);
            }
        }
        Hechos hechosFiltrados = new Hechos(tablaHechosFiltrada);

        HashMap<Dimension, Integer> nivelesNuevos = new HashMap<Dimension, Integer>();
        for (Map.Entry<Dimension, Integer> nivel : this.niveles.entrySet()) {
            if(nivel.getKey().getNombre() != nombreDimension)
                nivelesNuevos.put(nivel.getKey(), nivel.getValue());
        }

        Cubo cuboNuevo = new Cubo("Cubo_" + valor, nivelesNuevos, hechosFiltrados);

        return cuboNuevo;
    }

    public Cubo dice(){
        // es un slice pero con más de un valor. También debería devolver otro cubo
        return null;
    }

    void rollUp(String nombreDimension){
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

    void rollUp(String nombreDimension, Integer numeroRollUps){
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

    void drillDown(String nombreDimension){
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

    void drillDown(String nombreDimension, Integer numeroDrillDowns){
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

    void proyectar(String valorHechos, String medida){ 
        // TODO: poder pasar varios valores de la tabla de hechos y varias medidas. (¿Metodo para elegir valores y medidas?)
        Tabla tablaParseada = Operador.parsear(niveles, hechos, hechos.getTabla().getHeaderIndex(valorHechos));
        List<String> columnas =  new ArrayList<>(Arrays.asList(tablaParseada.getHeaders()));
        columnas.remove(valorHechos);
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada, columnas , medida);
        Proyeccion p = new Proyeccion(tablaAgrupada);
        p.info();
        p.imprimirPrimerasDiezFilas();  
    }
}
