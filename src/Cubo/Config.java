package Cubo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private Map<String, List<Dimension>> dimensiones;
    private Hecho hecho;

    public Config() {
        this.dimensiones = new HashMap<>();
    }

    public void agregarDimension() {
    }

    public void setHechos() {
    }

    public Map<String, List<Dimension>> getDimensiones() {
        return dimensiones;
    }

    public Hecho getHechos() {
        return hecho;
    }
}
