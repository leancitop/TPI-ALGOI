package Tabla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import Cubo.Dimension;

public class Operador {

    /**
     * Parsea una tabla de hechos en función de los niveles especificados en las dimensiones.
     *
     * @param niveles     Un mapa que contiene las dimensiones y sus respectivos niveles.
     * @param hechos      La tabla de hechos que se va a parsear.
     * @param index_medida El índice de la medida que se va a incluir en la nueva tabla.
     * @return Una nueva tabla que contiene las columnas de los niveles especificados y la medida.
     */
    public static Tabla parsear(Map<Dimension, Integer> niveles, Tabla hechos, Integer index_medida) {
        // Crear una nueva tabla para los resultados
        Tabla nuevaTabla = new Tabla();

        // Crear columnas de los niveles en la nueva tabla
        for (Dimension d : niveles.keySet()) {
            for (int i = d.getNumeroNiveles(); i >= niveles.get(d); i--) { // Esto para traer desde el nivel que le pasas hasta el más alto
                Tabla tabla_dimension = d.getTabla();
                Columna<?> columna_nivel = tabla_dimension.getColumnas().get(i);
                ColumnaNumerica columna_fIds = (ColumnaNumerica) hechos.getColumnas().get(d.getClaveForanea());
                if (columna_nivel instanceof ColumnaNumerica) {
                    ColumnaNumerica columnaCruce = new ColumnaNumerica(columna_nivel.getNombre());
                    for (Double dato : columna_fIds.getDatos()) {
                        columnaCruce.agregarDato((Double) tabla_dimension.getById(dato, i));
                    }
                    nuevaTabla.agregarColumna(columnaCruce);
                } else {
                    ColumnaString columnaCruce = new ColumnaString(columna_nivel.getNombre());
                    for (Double dato : columna_fIds.getDatos()) {
                        columnaCruce.agregarDato((String) tabla_dimension.getById(dato, i));
                    }
                    nuevaTabla.agregarColumna(columnaCruce);
                }
            }
        }

        // Cargar la columna de la medida elegida de los hechos
        nuevaTabla.agregarColumna(hechos.getColumnas().get(index_medida));
        nuevaTabla.cargarHeaders(); //headers
        return nuevaTabla;
    }

    /**
     * Agrupa una tabla basada en las columnas especificadas y aplica una operación a las columnas no agrupadas.
     *
     * @param tabla              La tabla que se va a agrupar.
     * @param columnasAAgrupar   Una lista de nombres de las columnas que se van a agrupar.
     * @param operacionARealizar La operación que se va a realizar en las columnas no agrupadas (p.ej., "suma", "promedio").
     * @return Una nueva tabla con los datos agrupados y las operaciones aplicadas.
     */
    public static Tabla agrupar(Tabla tabla, List<String> columnasAAgrupar, String operacionARealizar) {
        Map<List<Object>, List<Integer>> grupos = new HashMap<>();

        // Identificar las columnas a agrupar
        List<Columna<?>> columnas = tabla.getColumnas();
        List<Columna<?>> columnasAgrupacion = new ArrayList<>();
        for (String nombre : columnasAAgrupar) {
            for (Columna<?> col : columnas) {
                if (col.getNombre().equals(nombre)) {
                    columnasAgrupacion.add(col);
                    break;
                }
            }
        }

        // Crear los grupos
        for (int i = 0; i < tabla.getNumeroFilas(); i++) {
            List<Object> claveGrupo = new ArrayList<>();
            for (Columna<?> col : columnasAgrupacion) {
                claveGrupo.add(col.getContenidoFila(i));
            }

            grupos.computeIfAbsent(claveGrupo, k -> new ArrayList<>()).add(i);
        }

        // Crear nueva tabla para los resultados agrupados
        Tabla tablaAgrupada = new Tabla();

        // Crear columnas agrupadas en la nueva tabla
        for (Columna<?> col : columnasAgrupacion) {
            if (col instanceof ColumnaString) {
                tablaAgrupada.agregarColumna(new ColumnaString(col.getNombre()));
            } else if (col instanceof ColumnaNumerica) {
                tablaAgrupada.agregarColumna(new ColumnaNumerica(col.getNombre()));
            }
        }

        // Crear columnas para las operaciones a realizar
        List<Columna<?>> columnasOperacion = new ArrayList<>();
        for (Columna<?> col : columnas) {
            if (!columnasAgrupacion.contains(col)) {
                if (col instanceof ColumnaString) {
                    columnasOperacion.add(new ColumnaString(col.getNombre() + "_" + operacionARealizar));
                } else if (col instanceof ColumnaNumerica) {
                    columnasOperacion.add(new ColumnaNumerica(col.getNombre() + "_" + operacionARealizar));
                }
            }
        }

        // Agregar las columnas de operaciones a la nueva tabla
        for (Columna<?> col : columnasOperacion) {
            tablaAgrupada.agregarColumna(col);
        }

        // Agregar datos a la nueva tabla
        for (Map.Entry<List<Object>, List<Integer>> entry : grupos.entrySet()) {
            List<Object> claveGrupo = entry.getKey();
            List<Integer> indicesFilas = entry.getValue();

            // Agregar claves de grupo a la nueva tabla
            for (int i = 0; i < claveGrupo.size(); i++) {
                Object valor = claveGrupo.get(i);
                Columna<?> col = tablaAgrupada.getColumnas().get(i);
                if (col instanceof ColumnaString) {
                    ((ColumnaString) col).agregarDato((String) valor);
                } else if (col instanceof ColumnaNumerica) {
                    ((ColumnaNumerica) col).agregarDato((Double) valor);
                }
            }

            // Aplicar operación a cada columna no agrupada y agregar el resultado a la nueva tabla
            for (Columna<?> col : columnasOperacion) {
                String nombreOriginal = col.getNombre().replace("_" + operacionARealizar, "");
                Columna<?> columnaOriginal = columnas.stream()
                        .filter(c -> c.getNombre().equals(nombreOriginal))
                        .findFirst()
                        .orElse(null);

                if (columnaOriginal != null) {
                    Object resultado = aplicarOperacion(columnaOriginal, indicesFilas, operacionARealizar);
                    if (col instanceof ColumnaString) {
                        ((ColumnaString) col).agregarDato((String) resultado);
                    } else if (col instanceof ColumnaNumerica) {
                        ((ColumnaNumerica) col).agregarDato((Double) resultado);
                    }
                }
            }
        }

        return tablaAgrupada;
    }

    /**
     * Aplica una operación a una columna sobre un conjunto de filas.
     *
     * @param columna    La columna a la que se va a aplicar la operación.
     * @param indicesFilas Los índices de las filas sobre las que se va a aplicar la operación.
     * @param operacion  La operación que se va a aplicar (p.ej., "suma", "promedio").
     * @return El resultado de la operación aplicada a la columna.
     */
    private static Object aplicarOperacion(Columna<?> columna, List<Integer> indicesFilas, String operacion) {
        switch (operacion.toLowerCase()) {
            case "suma":
                if (columna instanceof ColumnaNumerica) {
                    Double suma = 0.0;
                    for (int i : indicesFilas) {
                        suma += (Double) columna.getContenidoFila(i);
                    }
                    return suma;
                }
                break;
            case "promedio":
                if (columna instanceof ColumnaNumerica) {
                    double suma = 0;
                    for (int i : indicesFilas) {
                        suma += (Double) columna.getContenidoFila(i);
                    }
                    return suma / indicesFilas.size();
                }
                break;
            case "contar":
                break; //hay que implementar la lógica del conteo y las de abajo
            case "min":
                break;
            case "max":
                break;
        }
        return null;
    }

    /**
     * Tipos de filtros que se pueden aplicar a las columnas.
     */
    public enum TiposFiltros {
        IGUAL,
        MAYOR,
        MENOR,
        NO_IGUAL,
        ENTRE
    }

    /**
     * Filtra una columna basándose en los comparadores y el tipo de filtro especificado.
     *
     * @param columna     La columna que se va a filtrar.
     * @param comparadores Una lista de valores con los que se va a comparar el contenido de la columna.
     * @param tipoFiltro  El tipo de filtro que se va a aplicar (p.ej., IGUAL, MAYOR, MENOR).
     * @return Una lista de índices que cumplen con el filtro.
     */
    public static List<Integer> filtrar(Columna<?> columna, List<String> comparadores, TiposFiltros tipoFiltro) {
        List<Integer> filasOk = new ArrayList<>();
        String comparador = comparadores.get(0);
        Integer n = 0;
        for (Object valRaw : columna.getDatos()) {
            String val = valRaw.toString();
            switch (tipoFiltro) {
                case IGUAL:
                    if (comparadores.contains(val)) {
                        filasOk.add(n);
                    }
                    break;
                case NO_IGUAL:
                    if (!comparadores.contains(val)) {
                        filasOk.add(n);
                    }
                    break;
                case MAYOR:
                    try {
                        Double valN = Double.parseDouble(val);
                        if (valN > Double.parseDouble(comparador)) {
                            filasOk.add(n);
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("La columna debe ser de tipo numérico");
                    }
                    break;
                case MENOR:
                    try {
                        Double valN = Double.parseDouble(val);
                        if (valN < Double.parseDouble(comparador)) {
                            filasOk.add(n);
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("La columna debe ser de tipo numérico");
                    }
                    break;
                case ENTRE:
                    if (comparadores.size() < 2) {
                        throw new IllegalArgumentException("Para filtrar ENTRE se deben tener al menos 2 valores");
                    }
                    try {
                        Double valN = Double.parseDouble(val);
                        if (Double.parseDouble(comparadores.get(0)) < valN &&
                                valN < Double.parseDouble(comparadores.get(1))) {
                            filasOk.add(n);
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("La columna debe ser de tipo numérico");
                    }
                    break;
                default:
                    break;
            }
            n++;
        }
        return filasOk;
    }

    /**
     * Filtra una tabla de hechos basada en una dimensión, un valor y un nivel de filtro.
     *
     * @param hechos      La tabla de hechos que se va a filtrar.
     * @param dim         La dimensión sobre la cual se va a realizar el filtro.
     * @param valor       El valor que se va a utilizar para el filtro.
     * @param nivelFiltro El nivel de la dimensión que se va a utilizar para el filtro.
     * @param borrarCol   Indica si se debe eliminar la columna de clave foránea después de filtrar.
     * @return Una nueva tabla que contiene solo las filas que cumplen con el filtro.
     */
    public static Tabla filtrarHechos(Tabla hechos, Dimension dim, String valor, Integer nivelFiltro, boolean borrarCol) {
        List<String> ids_dim = new ArrayList<>();
        Tabla dimTabla = dim.getTabla();
        List<String> filtros = new ArrayList<>(); // paso necesario por cómo es filtro
        filtros.add(valor);
        List<Integer> indexesDim = Operador.filtrar(
                dimTabla.getColumnas().get(nivelFiltro + 1),
                filtros,
                Operador.TiposFiltros.IGUAL
        );
        if(indexesDim.size() == 0)
            throw new RuntimeException("No se encontraron coincidencias con el valor '"+ valor +"' en la dimension " + dim.getNombre());

        ColumnaNumerica columnaIds = (ColumnaNumerica) dimTabla.getColumnas().get(0);
        Set<String> setValores = new HashSet<>(); // testeo
        for (Integer index : indexesDim) {
            ids_dim.add(columnaIds.getContenidoFila(index).toString());
            setValores.add(dimTabla.getColumnas().get(nivelFiltro + 1).getDatos().get(index).toString());
        }
        int col_fk = dim.getClaveForanea();

        List<Integer> indexesHechos = Operador.filtrar(
                hechos.getColumnas().get(col_fk),
                ids_dim,
                Operador.TiposFiltros.IGUAL
        );

        if(indexesHechos.size() == 0)
            throw new RuntimeException("Las ninguna de siguientes FK se encuentran en la columna " + hechos.getHeaders()[col_fk] + ": " + String.join(", ", ids_dim));

        Set<String> setHechos = new HashSet<>(); // testeo

        Tabla tablaHechosFiltrada = new Tabla();
        int i = 0;
        for (Columna<?> col : hechos.getColumnas()) {
            // Como se elimina la dimensión utilizada, salto la columna con la FK de la dim (solo en el slice)
            if (i == col_fk && borrarCol) {
                i++;
                continue;
            }

            String nombreColOriginal = col.getNombre();
            List<?> contenidoColOriginal = col.getDatos();
            // El try-catch es por el parseo de las columnas, si hay un método mejor cámbienlo
            try {
                ColumnaNumerica colNueva = new ColumnaNumerica(nombreColOriginal);
                for (Integer index : indexesHechos) {
                    var colFK = dimTabla.getColumnas().get(nivelFiltro + 1);
                    if (i == col_fk) {
                        var indexfk = Double.parseDouble(contenidoColOriginal.get(index).toString());
                        var val = colFK.getDatos().get((int) indexfk);
                        setHechos.add(val.toString());
                    }
                    var dato = Double.parseDouble(contenidoColOriginal.get(index).toString());
                    colNueva.agregarDato(dato);
                }
                tablaHechosFiltrada.agregarColumna(colNueva);
            } catch (Exception e) {
                ColumnaString colNueva = new ColumnaString(nombreColOriginal);
                for (Integer index : indexesHechos) {
                    var dato = contenidoColOriginal.get(index).toString();
                    colNueva.agregarDato(dato);
                }
                tablaHechosFiltrada.agregarColumna(colNueva);
            }
            i++;
        }

        return tablaHechosFiltrada;
    }
}