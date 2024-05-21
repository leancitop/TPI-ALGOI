public class App {
    public static void main(String[] args) {

        System.out.println("patience.");

        Tabla fechas_csv = LectorArchivos.leerCSV("/home/tareas/TPI-ALGOI/datasets/fechas.csv");

        Dimension tiempo = new Dimension("tiempo", 0,5);
        tiempo.setTabla(fechas_csv);

        tiempo.cargarMapaDimension();

        System.out.println(tiempo.getValoresId());
    }
}
