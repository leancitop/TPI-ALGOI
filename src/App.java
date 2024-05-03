public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("yoiki tenkai");

        CSV productos = new CSV();
        productos.read_csv("/home/tareas/Desktop/tpf/miProyectoJava/datasets/productos.csv");

        System.out.println(productos.getHeaders());
        System.out.println(productos.getRecords());
    }
}