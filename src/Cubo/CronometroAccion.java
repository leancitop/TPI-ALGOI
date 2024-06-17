package Cubo;

import java.time.Duration;
import java.time.Instant;

/**
 * Clase que proporciona funcionalidad para cronometrar la duración de una acción.
 */
public class CronometroAccion {
    private String nombre;
    private Instant inicio;
    private Instant fin;

    /**
     * Constructor de CronometroAccion que inicia el cronómetro al momento de la creación.
     * @param nombre Nombre asociado al cronómetro.
     */
    public CronometroAccion(String nombre){
        this.nombre = nombre;
        this.inicio = Instant.now();
        this.fin = null;
    }

    /**
     * Finaliza la medición del tiempo y muestra la duración transcurrida desde la creación del cronómetro.
     * Imprime el tiempo transcurrido en milisegundos.
     */
    public void finalizar(){
        this.fin = Instant.now();
        Duration duracion = Duration.between(this.inicio, this.fin);
        System.err.println(nombre + ": " + duracion.toMillis() + " ms.");
    }

    /**
     * Reinicia el cronómetro, comenzando una nueva medición desde el momento de la llamada.
     */
    public void reiniciar(){
        this.inicio = Instant.now();
        this.fin = null;
    }

    /**
     * Cambia el nombre asociado al cronómetro.
     * @param nombre Nuevo nombre a asignar al cronómetro.
     */
    public void cambiarNombre(String nombre){
        this.nombre = nombre;
    }
}