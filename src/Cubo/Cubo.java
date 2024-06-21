package Cubo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import Tabla.Tabla;
import Tabla.Medidas.Medida;
import Tabla.Operador;
import Tabla.Proyeccion;

/**
 * Clase que representa un cubo para análisis multidimensional de datos.
 */
public class Cubo {
    private String nombre;
    private Map<Dimension, Integer> dimensiones;
    private Tabla hechos;
    private List<Medida> medidas;

    /**
     * Constructor privado de Cubo.
     * @param nombre Nombre del cubo.
     * @param dimensiones Dimensiones del cubo.
     * @param hechos Tabla de hechos del cubo.
     * @param medidas Medidas disponibles del cubo.
     */
    private Cubo(String nombre, Map<Dimension, Integer> dimensiones, Tabla hechos, List<Medida> medidas){
        this.dimensiones = dimensiones;
        this.nombre = nombre;
        this.hechos = hechos;
        this.medidas = medidas;
    }

    /**
     * Constructor privado que utiliza un objeto Config para crear un nuevo cubo.
     * @param configCubo Configuración del cubo.
     */
    private Cubo(Config configCubo){
        dimensiones = configCubo.getDimensiones();
        nombre = configCubo.getNombre();
        hechos = configCubo.getHechos();
        medidas = configCubo.getMedidas();
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
     * 
     * @param nombreDimension Nombre de la dimensión a filtrar.
     * @param filtros Valores a filtrar. Cantidad acorde al nivle hasta el que se quiera llegar. Orden: desde el nivel más alto hasta el más bajo.
     * @return Nuevo cubo con los datos filtrados.
     * @throws NoSuchElementException si no se encuentra una dimensión con el nombre especificado.
     * @throws IndexOutOfBoundsException si nivelFiltro está fuera de los límites válidos para la dimensión.
     * @throws IllegalStateException si el estado del cubo no es válido para realizar el slice.
     */
    public Cubo slice(String nombreDimension, String filtros) {
        //convierto el string en una lista y saco los espacios que pueda llegar a tener
        List<String> valores = Arrays.stream(filtros.split(",")).map(String::trim).toList();

        // Buscar la dimensión por nombre
        Dimension dimensionSeleccionada = null;
        for (Dimension dim : dimensiones.keySet()) {
            if (dim.getNombre().equals(nombreDimension)) {
                dimensionSeleccionada = dim;
                break;
            }
        }

        // Lanzar excepción si no se encontró la dimensión
        if (dimensionSeleccionada == null) {
            throw new NoSuchElementException("No se encontró una dimensión con el nombre especificado: " + nombreDimension);
        }

        //tabla de hechos para el cubo nuevo
        Tabla hechosFiltrados = this.hechos;
        // Crear un nuevo mapa de niveles para el nuevo cubo
        HashMap<Dimension, Integer> nivelesNuevos = new HashMap<>();
        int nivelFiltro = dimensionSeleccionada.getCantidadNiveles() - 1;
        for(String valor : valores) {
            // Validar el nivel de filtro
            if (
                nivelFiltro < 0
                //nivelFiltro >= dimensionSeleccionada.getCantidadNiveles()
            ) {
                throw new IndexOutOfBoundsException("Nivel de filtro inválido para la dimensión; demasiados valores. Utilizar solo " + dimensionSeleccionada.getCantidadNiveles());
            }
            // Filtrar los hechos según la dimensión y el valor especificado
            hechosFiltrados = Operador.filtrarHechos(hechosFiltrados, dimensionSeleccionada, valor, nivelFiltro, false);
    
            // for (HashMap.Entry<Dimension, Integer> nivel : this.dimensiones.entrySet()) {
            //    if (!nivel.getKey().getNombre().equals(nombreDimension)) {
            //         nivelesNuevos.put(nivel.getKey(), nivel.getValue());
            //     }
            // }-
            nivelFiltro = nivelFiltro - 1;
        }
        // Devolver un nuevo cubo con los datos filtrados
        return new Cubo("Cubo_" + String.join("_", valores), this.dimensiones, hechosFiltrados, medidas);
    }
    /**
     * Realiza un dice (filtrado múltiple) en el cubo según las especificaciones dadas.
     * 
     * @param nombreCubo Nombre del nuevo cubo resultante.
     * @param dice Configuración para el filtrado.
     * @return Nuevo cubo con los datos filtrados.
     * @throws IllegalArgumentException si alguna de las dimensiones especificadas en el filtro no existe en el cubo.
     * @throws RuntimeException si no existen hechos que cumplan con los filtros solicitados.
     */
    public Cubo dice(String nombreCubo, ConfigDice dice) {
        Tabla hechosDice = this.hechos;
        List<Medida> medidasDice = this.medidas;

        for (int i = 0; i < dice.largo; i++) {
            boolean dimensionEncontrada = false;
            for (Dimension dim : dimensiones.keySet()) {
                if (dim.getNombre().equals(dice.dimensiones.get(i))) {
                    hechosDice = Operador.filtrarHechos(
                        hechosDice, 
                        dim, 
                        dice.valoresFiltro.get(i), 
                        dice.niveles.get(i), 
                        false
                    );
                    dimensionEncontrada = true;
                    break;
                }
            }
            if (!dimensionEncontrada) {
                throw new IllegalArgumentException("La dimensión especificada no existe en el cubo: " + dice.dimensiones.get(i));
            }
        }

        if (hechosDice.getNumeroFilas() == 0) {
            throw new RuntimeException("No existen hechos que cumplan con los filtros solicitados");
        }

        return new Cubo(nombreCubo, this.dimensiones, hechosDice, medidasDice);
    }


    /**
     * Realiza un roll up en una dimensión específica. Aumenta el nivel seleccionado una vez en esa dimension
     * @param nombreDimension Nombre de la dimensión para hacer roll up.
     */
    public void rollUp(String nombreDimension){
        dimensiones.forEach((dimension, indexNiveles) -> {
            if (dimension.getNombre() == nombreDimension){
                if (dimension.getCantidadNiveles() >= indexNiveles+1){
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
                if (dimension.getCantidadNiveles() >= indexNiveles+numeroRollUps){
                    dimensiones.replace(dimension, indexNiveles+numeroRollUps);
                    System.out.println("La dimension " + dimension.getNombre() + " ahora está fijada en el nivel "+ ((Integer) indexNiveles+numeroRollUps));
                }
                else if (dimension.getCantidadNiveles() - indexNiveles - numeroRollUps <= 0) {
                    Integer numeroRollUpRestantes = Math.abs(dimension.getCantidadNiveles() - indexNiveles - numeroRollUps);
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
     * Imprime la información del objeto actual en la consola.
     * Utiliza el método `toString` para obtener una representación en cadena del objeto.
     */
    public void informacion(){
        System.out.println(this);
        System.out.println("");
    }

    /**
     * Realiza una proyección sobre el cubo utilizando una o dos dimensiones,
     * un valor de hechos, una medida y un número específico de filas a imprimir.
     * Tambien se pueden proyectar 0 dimensiones.
     *
     * @param dimension1 Primera dimensión a utilizar en la proyección.
     * @param dimension2 Segunda dimensión opcional a utilizar en la proyección.
     * @param valorHechos Valor específico de los hechos sobre los cuales se realiza la proyección.
     * @param medida Medida a aplicar en la proyección.
     * @param numeroFilas Número de filas a imprimir en los resultados de la proyección. (Opcional)
     * @throws IllegalArgumentException Si las dimensiones especificadas no existen en el cubo de datos.
     * @throws IllegalArgumentException Si la medida especificada no está cargada en el cubo de datos.
     */
    public void proyectar(String dimension1, String dimension2, String valorHechos, String medida, int numeroFilas){
        Map<Dimension, Integer> diemensionesElegidas = new HashMap<>();
        for (Map.Entry<Dimension, Integer> entry : dimensiones.entrySet()) {
            if (entry.getKey().getNombre().equals(dimension1)){
                diemensionesElegidas.put(entry.getKey(), entry.getValue());
            }
            if (entry.getKey().getNombre().equals(dimension2)){
                diemensionesElegidas.put(entry.getKey(), entry.getValue());
            }
        }
        if (diemensionesElegidas.size() == 1 && (!dimension2.equals("vacio"))) {
            throw new IllegalArgumentException("Verificar dimensiones elegidas. No se encuentran en el Cubo.");
        }
        if (diemensionesElegidas.size() == 0 && (!dimension1.equals("vacio"))) {
            throw new IllegalArgumentException("Verificar dimensiones elegidas. No se encuentran en el Cubo.");
        }

        List<Medida> medidasCopia = new ArrayList<>(medidas);
        boolean correcto = false;
        for (int i = 0; i < medidas.size(); i++) {
            if ((medidas.get(i).toString().toLowerCase()).equals(medida.toLowerCase()) ){
                medidasCopia.remove(i);
                medidasCopia.add(0, medidas.get(i));
                correcto = true;
            }
        }
        if (!correcto) {
            throw new IllegalArgumentException("La medida [" + medida + "] no esta cargada en el cubo. Medidas disponibles: " + medidas);
        }
        
        Tabla tablaParseada = Operador.parsear(diemensionesElegidas, hechos, hechos.getHeaderIndex(valorHechos));
        List<String> columnas =  new ArrayList<>(Arrays.asList(tablaParseada.getHeaders()));
        columnas.remove(valorHechos);
        Tabla tablaAgrupada = Operador.agrupar(tablaParseada, columnas , medidasCopia.get(0));

        Proyeccion p = new Proyeccion(tablaAgrupada);
        System.out.println("");
        if (dimension2.equals("vacio") && dimension1.equals("vacio")){
            System.out.println("Proyectando 0 dimensiones: [" + nombre + "]");
        } else if(dimension2.equals("vacio")) {
            System.out.println("Proyectando 1 dimensión: [" + dimension1 + "]");
        } else{
            System.out.println("Proyectando 2 dimensiones: [" + dimension1 + ", " + dimension2 + "]"); 
        }
        System.out.println("");
        p.imprimirNfilas(numeroFilas);
        System.out.println("");
        System.out.println("Cantidad de columnas: " + tablaAgrupada.getColumnas().size());
        System.out.println("Cantidad de filas: " + tablaAgrupada.getNumeroFilas());
        System.out.println("");
    }

    public void proyectar(String dimension1, String valorHechos, String medida) {
        proyectar(dimension1, "vacio", valorHechos, medida, 15);
    }

    public void proyectar(String dimension1, String valorHechos, String medida, int numeroFilas) {
        proyectar(dimension1, "vacio", valorHechos, medida, numeroFilas);
    }

    public void proyectar(String valorHechos, String medida) {
        proyectar("vacio", "vacio", valorHechos, medida, 15);
    }

    public void proyectar(String dimension1, String dimension2, String valorHechos, String medida) {
        proyectar(dimension1, dimension2, valorHechos, medida, 15);
    }

    /**
     * Devuelve una representación en cadena del objeto Cubo.
     * 
     * @return Una cadena que representa el objeto Cubo, incluyendo su nombre, dimensiones y sus niveles,
     *         así como los valores de los hechos.
     */
    @Override
    public String toString() {
        String salida = "Cubo: " + nombre;
        for (Map.Entry<Dimension, Integer> entry : dimensiones.entrySet()) {
            salida += "\n" + entry.getKey();
            salida += "\n   - Nivel fijado en: " + "[" + entry.getKey().getTabla().getHeaders()[entry.getValue()] + "]";
        }
        salida += "\n- Hechos: \n   - Valores: [";
        String[] headers = hechos.getHeaders(); 
        for (int i = 1; i <= headers.length - 1; i++) {
            if (i == headers.length - 1){
                salida += headers[i] + "]";
            }else{
                salida += headers[i] + ", ";
            }
        }
        salida += "\n- Medidas: \n     [";
        for (int i = 0; i <= medidas.size() - 1; i++) {
            if (i == medidas.size() - 1){
                salida += medidas.get(i) + "]";
            }else{
                salida += medidas.get(i) + ", ";
            }
        }
        return salida;
    }
}



