package Tabla;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Cubo.Dimension;
import Cubo.Hechos;

public class Operador {

    public static Tabla parsear(Map<Dimension, Integer> niveles, Hechos hechos, Integer index_medida) {
        // Crear una nueva tabla para los resultados
        Tabla tablaHechos = hechos.getTabla();
        Tabla nuevaTabla = new Tabla();
        
        // Crear columnas de los niveles en la nueva tabla
        for (Dimension d : niveles.keySet()) {
            Tabla tabla_dimension = d.getTabla();
            Columna<?> columna_nivel = tabla_dimension.getColumnas().get(niveles.get(d));
            ColumnaNumerica columna_fIds = (ColumnaNumerica) tablaHechos.getColumnas().get(d.getClaveForanea());

            if (columna_nivel instanceof ColumnaNumerica){
                ColumnaNumerica columnaCruce = new ColumnaNumerica(columna_nivel.getNombre());
                for (Double dato : columna_fIds.getDatos()){
                    columnaCruce.agregarDato((Double)columna_nivel.getContenidoFila(dato));
                }
                nuevaTabla.agregarColumna(columnaCruce); 
            } else {
                ColumnaString columnaCruce = new ColumnaString(columna_nivel.getNombre());
                for (Double dato : columna_fIds.getDatos()){
                    columnaCruce.agregarDato((String)columna_nivel.getContenidoFila(dato));
                }
                nuevaTabla.agregarColumna(columnaCruce); 
            }
        }
        nuevaTabla.agregarColumna(tablaHechos.getColumnas().get(index_medida));
        nuevaTabla.cargarHeaders();
        return nuevaTabla;
        }


    }
    
    
    // static Tabla agrupar(){}

    // static Tabla filtrar(){}

    // static Tabla removerDimension(){}

    // static Tabla sumarizar(){}

    // static void visualizar(Tabla tabla){}
