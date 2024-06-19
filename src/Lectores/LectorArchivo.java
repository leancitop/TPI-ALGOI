package Lectores;

import java.io.IOException;

/**
 * Clase abstracta de lector de archivos. Sus hijos deberían leer distintos tipos de archivos (csv, xlsx...)
 */

public abstract class LectorArchivo {

    public abstract String[][] leerArchivo(String rutaArchivo) throws IOException;

}



