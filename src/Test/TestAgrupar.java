package Test;

import Tabla.Tabla;
import java.util.ArrayList;
import java.util.List;

import Cubo.Hechos;
import Tabla.Operador;
import Tabla.Proyeccion;

public class TestAgrupar {
    public TestAgrupar(){
        Hechos ventas = new Hechos("TPI-ALGOI\\datasets\\ventas.csv");
        List<String> col_agrupacion = new ArrayList<>();

        col_agrupacion.add("id_punto_venta");
        col_agrupacion.add("id_fecha");

        List<String> col_agrupar = new ArrayList<>();
        col_agrupar.add("id_producto");
        col_agrupar.add("cantidad");
        col_agrupar.add("valor_unitario");
        col_agrupar.add("valor_total");
        col_agrupar.add("costo");



        Tabla tablaAgrupada = Operador.agrupar(ventas.getTabla(), col_agrupacion, col_agrupar);

        Proyeccion p = new Proyeccion(tablaAgrupada);
        p.imprimirPrimerasDiezFilas();
        p.info();
    }
}
