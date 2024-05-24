import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        System.out.println("patience.");

        String[][] fechas_csv = LectorArchivos.leerCSV("datasets/fechas.csv");

        Tabla tabla_fechas = new Tabla(6);

        tabla_fechas.cargarTabla(fechas_csv);

        // TODO: Validar fechas_csv (Tipo String[][])
        // validar(matriz) # cols a validar numericas es config del cubo
        // Crear objeto Tabla a partir de String[][]

        Dimension tiempo = new Dimension("tiempo", 0,5, tabla_fechas, "id_fecha");

        tiempo.cargarMapaDimension();

        Map<String, List<String>> hashMap = tiempo.getValoresIdD();

        for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " : ");
            List<String> values = entry.getValue();
            int count = Math.min(10, values.size());
            for (int i = 0; i < count; i++) {
                System.out.println("\t" + values.get(i));
            }
        }

        Hechos hechos = new Hechos("datasets/ventas.csv", 7);
        // System.out.println(hechos.getIndexadoColumnas());
        // System.out.println(hechos.getDimensionesMap("id_fecha", "cantidad"));
        // System.out.println(tiempo.getFK());
        
        TablaFinal.imprimirTablaFinal(tiempo, hechos, "cantidad");
    }
}
