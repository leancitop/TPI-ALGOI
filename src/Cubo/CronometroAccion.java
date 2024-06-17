package Cubo;

import java.time.Duration;
import java.time.Instant;

public class CronometroAccion {
    private String nombre;
    private Instant inicio;
    private Instant fin;

    public CronometroAccion(String nombre){
        this.nombre = nombre;
        this.inicio = Instant.now();
        this.fin = null;
    }

    public void finalizar(){
        this.fin = Instant.now();
        Duration duracion = Duration.between(this.inicio, this.fin);
        System.err.println(nombre + ": " + duracion.toMillis() + " ms.");
    }

    public void reiniciar(){
        this.inicio = Instant.now();
        this.fin = null;
    }

    public void cambiarNombre(String nombre){
        this.nombre = nombre;
    }
}
 