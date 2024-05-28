package Cubo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Borrador {
    public static void main(String[] args) {
        // Crear e inicializar el mapa
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        // Usar un iterador para iterar sobre las entradas del mapa
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Clave: " + key + ", Valor: " + value);
            
            // Si deseas eliminar una entrada durante la iteración
            // iterator.remove();
        }
    }
}
