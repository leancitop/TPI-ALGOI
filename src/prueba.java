public class prueba {
    public static void main(String[] args) {
        String ruta= "E:\\Escritorio\\Facultad\\Java\\tp_final\\TPI-ALGOI\\TPI-ALGOI\\datasets\\ventas.csv";
        Tabla tabla = LectorArchivos.leerCSV(ruta);
        Tabla tablaFiltrada=  tabla.filtrarTabla(1,Tabla.funciones.MAYOR, "500");
        FormatoTabular.printTabularData(tablaFiltrada);
        



    
}
}


