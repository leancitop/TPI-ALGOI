package Tabla;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabla {
    public Columna[] columnas;
    public String[] headers;

    public Tabla(int numeroColumnas) {
        this.columnas = new Columna[numeroColumnas];
    }

    public void setColumna(int index, Columna columna) {
        columnas[index] = columna;
    }

    public void cargarTabla(String[][] matriz) {
        if (matriz.length == 0) {
            return;
        }

        String[] nombresColumnas = matriz[0];

        this.headers = nombresColumnas;

        for (int i = 0; i < nombresColumnas.length; i++) {
            try {
                Double.parseDouble(matriz[1][i]);
                columnas[i] = new ColumnaNumerica(nombresColumnas[i]);
            } catch (NumberFormatException e) {
                columnas[i] = new ColumnaString(nombresColumnas[i]);
            }
        }

        for (int i = 1; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                columnas[j].agregarDato(matriz[i][j]);
            }
        }
    }

    public String[] getHeaders(){
        return headers;
    }

    public Columna[] getColumnas() {
        return columnas;
    }

    public int getNumeroFilas() {
        if (columnas.length > 0) {
            return columnas[0].getDatos().size();
        }
        return 0;
    }

    public void setColumna(Columna columna, Integer posicionColumna){
        this.columnas[posicionColumna] = columna;
    }
    
    public void visualizar(Integer tabulacion){

        String format="%-"+tabulacion.toString()+"s";
        for (String head: headers){
            System.out.printf(format, head);
        }
        System.out.println();
        for (int i = 0; i <= this.getNumeroFilas()-1; i++){
            for (Columna columna: columnas){
                System.out.printf(format, columna.getDatos().get(i));
            }
            System.out.println();
        }
    }
}
